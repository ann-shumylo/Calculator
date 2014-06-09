package com.sec.android.calculator;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CalculatorView extends LinearLayout {
    private EditText inputString;
    private TextView showResult;
    private Activity activity;

    public CalculatorView(Activity activity) {
        super(activity);
        this.activity = activity;
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.display, this);
        inputString = (EditText) findViewById(R.id.input_string);
        showResult = (TextView) findViewById(R.id.show_result);
    }

    public void setInputtedSymbol(String s) {
        inputString.setText(getDisplayedString() + s);
        setCursorToTheEnd();
    }

    public void setEmptyView() {
        showResult.setText(R.string.no_results);
        inputString.setText("");
    }

    public void setResult() {
        showResult.setText("" + getDisplayedString() + "=" +
                formatStringResult(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
        inputString.setText(formatStringResult(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
        setCursorToTheEnd();
    }

    public void setBackspace() {
        int cursorPosition = inputString.getSelectionStart();
        inputString.getText().delete(cursorPosition - 1, cursorPosition);
    }

    private String getDisplayedString() {
        return inputString.getText().toString();
    }

    private void setCursorToTheEnd() {
        inputString.setSelection(inputString.length());
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
//
//    private void hideSoftKeyboard() {
//        if (activity.getCurrentFocus() != null) {
//            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
//            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
//        }
//    }
//
//    private boolean isInputStringEmpty() {
//        return TextUtils.isEmpty(inputString.getText().toString());
//    }
}
