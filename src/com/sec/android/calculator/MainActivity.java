package com.sec.android.calculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.calculator.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends Activity implements View.OnClickListener {
    private boolean isPointNeeded = true;
    private EditText inputString;
    private TextView showResult;
    private Button btnOpenBracket;
    private Button btnOloseBracket;
    private Button btnPoint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        inputString = (EditText) findViewById(R.id.first_number);
        showResult = (TextView) findViewById(R.id.show_result);
        btnOpenBracket = (Button) findViewById(R.id.btn_open_bracket);
        btnOloseBracket = (Button) findViewById(R.id.btn_close_bracket);
        btnPoint = (Button) findViewById(R.id.btn_point);

        inputString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
            }
        });
        btnOpenBracket.setOnClickListener(this);
        btnOloseBracket.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
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
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_addition).setOnClickListener(this);
        findViewById(R.id.btn_subtraction).setOnClickListener(this);
        findViewById(R.id.btn_division).setOnClickListener(this);
        findViewById(R.id.btn_multiplication).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputString.setText("");
                showResult.setText(R.string.no_results);
                isInputStringEmpty();
            }
        });
        findViewById(R.id.btn_backspace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cursorPosition = inputString.getSelectionStart();
                inputString.getText().delete(cursorPosition - 1, cursorPosition);
                checkBrackets();
                setEnabledToPoint();
                isInputStringEmpty();
            }
        });
        isInputStringEmpty();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                addNumber(1);
                break;
            case R.id.btn_two:
                addNumber(2);
                break;
            case R.id.btn_three:
                addNumber(3);
                break;
            case R.id.btn_four:
                addNumber(4);
                break;
            case R.id.btn_five:
                addNumber(5);
                break;
            case R.id.btn_six:
                addNumber(6);
                break;
            case R.id.btn_seven:
                addNumber(7);
                break;
            case R.id.btn_eight:
                addNumber(8);
                break;
            case R.id.btn_nine:
                addNumber(9);
                break;
            case R.id.btn_zero:
                addNumber(0);
                break;
            case R.id.btn_point:
                inputString.setText(getDisplayedString() + ".");
                setEnabledToOperands(false);
                btnOpenBracket.setEnabled(false);
                btnOloseBracket.setEnabled(false);
                btnPoint.setEnabled(false);
                isPointNeeded = false;
                break;
            case R.id.btn_open_bracket:
                inputString.setText(getDisplayedString() + "(");
                btnOloseBracket.setEnabled(false);
                btnPoint.setEnabled(false);
                break;
            case R.id.btn_close_bracket:
                inputString.setText(getDisplayedString() + ")");
                btnOpenBracket.setEnabled(false);
                setEnabledToNumbers(false);
                btnPoint.setEnabled(false);
                setEnabledToOperands(true);
                checkBrackets();
                break;
            case R.id.btn_addition:
                addOperand("+");
                break;
            case R.id.btn_subtraction:
                addOperand("-");
                break;
            case R.id.btn_division:
                addOperand("/");
                break;
            case R.id.btn_multiplication:
                addOperand("*");
                break;
            case R.id.btn_equal:
                checkBrackets();
                showResult.setText("" + getDisplayedString() + "=" +
                        numFormat(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
                inputString.setText(numFormat(CalculateResults.reversePolishNotation(getListOfNumbersAndSignsFromString())));
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
        btnOloseBracket.setEnabled(false);
        findViewById(R.id.btn_backspace).setEnabled(true);

        isPointNeeded = true;
    }

    private void addNumber(int number) {
        inputString.setText(getDisplayedString() + number);
        setEnabledToOperands(true);
        btnOpenBracket.setEnabled(false);
        checkBrackets();
        findViewById(R.id.btn_backspace).setEnabled(true);

        setEnabledToPoint();
    }

    private void setCursorToTheEnd() {
        inputString.setSelection(inputString.length());
    }

    private List<String> getListOfNumbersAndSignsFromString() {
        List<String> myList = new ArrayList<>();
        StringTokenizer check = new StringTokenizer(getDisplayedString(), "+/-*)(", true);
        while (check.hasMoreTokens()) {
            myList.add(check.nextToken());
        }
        return myList;
    }


    private String numFormat(float floatNum) {
        if (floatNum == (int) floatNum)
            return String.format("%d", (int) floatNum);
        else
            return String.format("%s", floatNum);
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void setEnabledToOperands(boolean isEnabled) {
        findViewById(R.id.btn_equal).setEnabled(isEnabled);
        findViewById(R.id.btn_addition).setEnabled(isEnabled);
        findViewById(R.id.btn_subtraction).setEnabled(isEnabled);
        findViewById(R.id.btn_division).setEnabled(isEnabled);
        findViewById(R.id.btn_multiplication).setEnabled(isEnabled);
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

    private void isInputStringEmpty() {
        if (TextUtils.isEmpty(inputString.getText().toString())) {
            btnPoint.setEnabled(false);
            setEnabledToOperands(false);
            setEnabledToNumbers(true);
            btnOloseBracket.setEnabled(false);
            btnOpenBracket.setEnabled(true);
            findViewById(R.id.btn_backspace).setEnabled(false);
            findViewById(R.id.btn_equal).setEnabled(false);
            isPointNeeded = true;
        }
    }

    private void checkBrackets() {
        int countOpen = 0;
        int countClose = 0;

        for (String temp : getListOfNumbersAndSignsFromString()) {
            if (temp.contains("(")) {
                countOpen++;
            } else if (temp.contains(")")) {
                countClose++;
            }
        }

        if (countOpen > 0) {
            if (countOpen > countClose) {
                btnOloseBracket.setEnabled(true);
                findViewById(R.id.btn_equal).setEnabled(false);
            }
            if (countOpen == countClose) {
                btnOloseBracket.setEnabled(false);
            }
        }
    }

    private void setEnabledToPoint() {
        if(isPointNeeded) {
            btnPoint.setEnabled(true);
        } else {
            btnPoint.setEnabled(false);
        }
    }

    private void showToastPopup() {
        Context context = getApplicationContext();
        CharSequence text = "Enter value!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}