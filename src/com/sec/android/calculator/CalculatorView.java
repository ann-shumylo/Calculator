package com.sec.android.calculator;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CalculatorView implements View.OnClickListener {
    private final View mParentView;
    private final Context mContext;
    private EditText editText;
    private TextView showResult;
    private CalculatorController controller;


    public CalculatorView(Context context, View parentView) {
        mContext = context;
        mParentView = parentView;
        initUIComponents();
        hideSoftKeyboard();
    }

    public void setListener(CalculatorController controller) {
        this.controller = controller;
    }

    public void setInputtedSymbol(String s) {
        editText.setText(getDisplayedString() + s);
        setCursorToTheEnd();
    }

    public void setInputtedDigitSymbol(int digitSymbol) {
        editText.setText(getDisplayedString() + digitSymbol);
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
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void initUIComponents() {
        editText = (EditText) mParentView.findViewById(R.id.input_string);
        showResult = (TextView) mParentView.findViewById(R.id.show_result);
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
            }
        });
        editText.setLongClickable(false);

        /*Numbers*/
        mParentView.findViewById(R.id.btn_one).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_two).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_three).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_four).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_five).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_six).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_seven).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_eight).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_nine).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_zero).setOnClickListener(this);
        /*Operations*/
        mParentView.findViewById(R.id.btn_addition).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_subtraction).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_division).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_multiplication).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_power).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_sin).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_cos).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_percent).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_open_bracket).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_close_bracket).setOnClickListener(this);
        mParentView.findViewById(R.id.btn_point).setOnClickListener(this);
        /*Equal, Clear, Backspace*/
        Button btnClear = (Button) mParentView.findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                controller.onClearBtnClicked();

            }
        });
        Button btnEqual = (Button) mParentView.findViewById(R.id.btn_equal);
        btnEqual.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                controller.onEqualBtnClicked();
            }
        });
        Button btnBackspace = (Button) mParentView.findViewById(R.id.btn_backspace);
        btnBackspace.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                controller.onBackspaceBtnClicked();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.get(v.getId()) != null) {
            controller.onOperationBtnClicked(ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.get(v.getId()));
        } else {
            controller.onDigitBtnClicked(ActionCodesLinks.BUTTON_ID_TO_DIGIT_CODE_LINK.get(v.getId()));
        }

    }
}
