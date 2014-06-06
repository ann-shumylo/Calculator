package com.sec.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// TODO Try to use logic like to view with controller
public class MainActivity extends Activity implements View.OnClickListener {
    // TODO Need separate this logic from Activity
    private boolean isPointNeeded = true;
    private TextView showResult;
    private EditText inputString;
    private Button btnOpenBracket;
    private Button btnCloseBracket;
    private Button btnPoint;
    private Button btnEqual;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        showResult = (TextView) findViewById(R.id.show_result);
        inputString = (EditText) findViewById(R.id.input_string);
        btnOpenBracket = (Button) findViewById(R.id.btn_open_bracket);
        btnCloseBracket = (Button) findViewById(R.id.btn_close_bracket);
        btnPoint = (Button) findViewById(R.id.btn_point);
        btnEqual = (Button) findViewById(R.id.btn_equal);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_addition).setOnClickListener(this);
        findViewById(R.id.btn_subtraction).setOnClickListener(this);
        findViewById(R.id.btn_division).setOnClickListener(this);
        findViewById(R.id.btn_multiplication).setOnClickListener(this);
        findViewById(R.id.btn_power).setOnClickListener(this);
        findViewById(R.id.btn_sin).setOnClickListener(this);
        findViewById(R.id.btn_cos).setOnClickListener(this);
        findViewById(R.id.btn_percent).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        btnOpenBracket.setOnClickListener(this);
        btnCloseBracket.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        inputString.setOnClickListener(this);
        inputString.setLongClickable(false);
        inputString.setTextIsSelectable(false);
        setEnabledStateToButtonsForEmptyString();
    }

    @Override
    public void onClick(View view) {
        if (ActionCodesLinks.BUTTON_ID_TO_DIGIT_CODE_LINK.get(view.getId()) != 0) {
            addNumber(ActionCodesLinks.BUTTON_ID_TO_DIGIT_CODE_LINK.get(view.getId()));
        } else if (ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.get(view.getId()) != null) {
            addOperand(ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.get(view.getId()));
        }
        switch (view.getId()) {
            case R.id.input_string:
                hideSoftKeyboard();
                break;
            case R.id.btn_open_bracket:
                inputString.setText(getDisplayedString() + "(");
                btnCloseBracket.setEnabled(false);
                btnPoint.setEnabled(false);
                break;
            case R.id.btn_close_bracket:
                inputString.setText(getDisplayedString() + ")");
                btnOpenBracket.setEnabled(false);
                setEnabledToNumbers(false);
                btnPoint.setEnabled(false);
                setEnabledToOperands(true);
                setEnabledToBrackets();
                break;
            case R.id.btn_point:
                inputString.setText(getDisplayedString() + ".");
                setEnabledToOperands(false);
                btnOpenBracket.setEnabled(false);
                btnCloseBracket.setEnabled(false);
                btnPoint.setEnabled(false);
                isPointNeeded = false;
                break;
            case R.id.btn_equal:
                //TODO Wrong to do this, you should keep state if inputed string by user correct, he can to tap on this button
                setEnabledToBrackets();
                if (isBracketsValid()) {
                    showResult.setText("" + getDisplayedString() + "=" +
                            numFormat(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
                    inputString.setText(numFormat(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
                    setCursorToTheEnd();
                }
                // TODO Why do you need this?
                btnEqual.setEnabled(false);
                break;
            case R.id.btn_clear:
                inputString.setText("");
                showResult.setText(R.string.no_results);
                setEnabledStateToButtonsForEmptyString();
                break;
            default:
                break;
        }
        setCursorToTheEnd();
    }

    private String getDisplayedString() {
        return inputString.getText().toString();
    }

    private void addOperand(String operand) {
        inputString.setText(getDisplayedString() + operand);
        btnPoint.setEnabled(false);
        setEnabledToOperands(false);
        setEnabledToNumbers(true);
        btnOpenBracket.setEnabled(true);
        btnCloseBracket.setEnabled(false);
        findViewById(R.id.btn_backspace).setEnabled(true);
        findViewById(R.id.btn_sin).setEnabled(true);
        findViewById(R.id.btn_sin).setEnabled(true);

        isPointNeeded = true;
    }

    private void addNumber(int number) {
        inputString.setText(getDisplayedString() + number);
        setEnabledToOperands(true);
        setEnabledToBrackets();
        findViewById(R.id.btn_backspace).setEnabled(true);
        setEnabledToPoint();
    }

    private void setCursorToTheEnd() {
        inputString.setSelection(inputString.length());
    }

    private List<String> getListOfNumbersAndSignsFromString() {
        List<String> myList = new ArrayList<>();
        String tempStr = getDisplayedString().replace("sin(", "s");
        StringTokenizer check = new StringTokenizer(tempStr.replace("cos", "c"), "+/-*)(^%sc", true);
        while (check.hasMoreTokens()) {
            myList.add(check.nextToken());
        }
        return myList;
    }


    private String numFormat(float floatNum) {
        // TODO ?????
        if (floatNum == (int) floatNum)
            return String.format("%d", (int) floatNum);
        else
            return String.format("%s", floatNum);
    }

    private void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void setEnabledToOperands(boolean isEnabled) {
        btnEqual.setEnabled(isEnabled);
        findViewById(R.id.btn_addition).setEnabled(isEnabled);
        findViewById(R.id.btn_subtraction).setEnabled(isEnabled);
        findViewById(R.id.btn_division).setEnabled(isEnabled);
        findViewById(R.id.btn_multiplication).setEnabled(isEnabled);
        findViewById(R.id.btn_percent).setEnabled(isEnabled);
        findViewById(R.id.btn_power).setEnabled(isEnabled);
    }

    private void setEnabledToNumbers(boolean isEnabled) {
        findViewById(R.id.btn_one).setEnabled(isEnabled);
        findViewById(R.id.btn_two).setEnabled(isEnabled);
        findViewById(R.id.btn_three).setEnabled(isEnabled);
        findViewById(R.id.btn_four).setEnabled(isEnabled);
        findViewById(R.id.btn_five).setEnabled(isEnabled);
        findViewById(R.id.btn_six).setEnabled(isEnabled);
        findViewById(R.id.btn_seven).setEnabled(isEnabled);
        findViewById(R.id.btn_eight).setEnabled(isEnabled);
        findViewById(R.id.btn_nine).setEnabled(isEnabled);
        findViewById(R.id.btn_zero).setEnabled(isEnabled);
    }

    private boolean isInputStringEmpty() {
        return TextUtils.isEmpty(inputString.getText().toString());
    }

    private void setEnabledStateToButtonsForEmptyString() {
        if (isInputStringEmpty()) {
            btnPoint.setEnabled(false);
            setEnabledToOperands(false);
            setEnabledToNumbers(true);
            btnCloseBracket.setEnabled(false);
            btnOpenBracket.setEnabled(true);
            findViewById(R.id.btn_backspace).setEnabled(false);
            btnEqual.setEnabled(false);
            findViewById(R.id.btn_sin).setEnabled(true);
            findViewById(R.id.btn_cos).setEnabled(true);
            isPointNeeded = true;
        }
    }

    private boolean isBracketsValid() {
        int count = 0;

        for (String temp : getListOfNumbersAndSignsFromString()) {
            if (temp.contains("(")) {
                count++;
            } else if (temp.contains(")")) {
                count--;
            }
        }
        return count == 0;
    }

    private void setEnabledToBrackets() {
        if (isBracketsValid()) {
            btnCloseBracket.setEnabled(false);
        } else {
            btnCloseBracket.setEnabled(true);
            btnEqual.setEnabled(false);
        }
    }

    //TODO Not clear for understanding
    private void setEnabledToPoint() {
        if (isPointNeeded) {
            btnPoint.setEnabled(true);
        } else {
            btnPoint.setEnabled(false);
        }
    }
}
