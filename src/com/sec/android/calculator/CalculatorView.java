package com.sec.android.calculator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CalculatorView extends LinearLayout {
    private EditText editText;
    private TextView showResult;

    public CalculatorView(Activity activity) {
        super(activity);
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.display, this);
        editText = (EditText) findViewById(R.id.input_string);
        showResult = (TextView) findViewById(R.id.show_result);

        hideSoftKeyboard();

        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
            }
        });
        editText.setLongClickable(false);
    }

    public void setInputtedSymbol(String s) {
        editText.setText(getDisplayedString() + s);
        setCursorToTheEnd();
    }

    public void setEmptyView() {
        showResult.setText(R.string.no_results);
        editText.setText("");
    }

    public void setResult() {
        showResult.setText("" + getDisplayedString() + "=" +
                formatStringResult(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
        editText.setText(formatStringResult(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
        setCursorToTheEnd();
    }

    public void setBackspace() {
        int cursorPosition = editText.getSelectionStart();
        editText.getText().delete(cursorPosition - 1, cursorPosition);
    }

    private String getDisplayedString() {
        return editText.getText().toString();
    }

    private void setCursorToTheEnd() {
        editText.setSelection(editText.length());
    }

    private String formatStringResult(double doubleNum) {
        if (doubleNum % 1 == 0) {
            return String.format("%d", (int) doubleNum);
        } else {
            return String.format("%s", doubleNum);
        }
    }

    private List<String> getListOfNumbersAndSignsFromString() {
        List<String> myList = new ArrayList<String>();
        String tempStr = getDisplayedString().replace("sin(", "s");
        StringTokenizer check = new StringTokenizer(tempStr.replace("cos", "c"), "+/-*)(^%sc", true);
        while (check.hasMoreTokens()) {
            myList.add(check.nextToken());
        }
        return myList;
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
