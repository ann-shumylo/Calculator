package com.sec.android.calculator.old;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.calculator.R;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity3 extends Activity implements View.OnClickListener {
    int result = 0;
    String displayedStr;
    LinkedList<String> listNumbers = new LinkedList<>();
    LinkedList<String> listSigns = new LinkedList<>();

    private EditText inputNumber;
    private Button equal;
    private Button summation;
    private Button subtraction;
    private Button division;
    private Button multiply;
    private Button clear;
    private TextView showResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initElements();
        initButtonsListeners();
    }

    private void initElements() {
        inputNumber = (EditText) findViewById(R.id.first_number);
        equal = (Button) findViewById(R.id.btn_equal);
        summation = (Button) findViewById(R.id.btn_addition);
        subtraction = (Button) findViewById(R.id.btn_subtraction);
        division = (Button) findViewById(R.id.btn_division);
        multiply = (Button) findViewById(R.id.btn_multiplication);
        clear = (Button) findViewById(R.id.btn_clear);
        showResult = (TextView) findViewById(R.id.show_result);
    }

    private void initButtonsListeners() {
        summation.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        division.setOnClickListener(this);
        multiply.setOnClickListener(this);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });
        equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(inputNumber.getText().toString())) {
            return;
        }
        displayedStr = inputNumber.getText().toString();

        char lastChar = displayedStr.charAt(displayedStr.length() - 1);
        if (!Character.isDigit(lastChar)) {
            return;
        }

        switch (view.getId()) {
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
                calculateResults();
                break;
            default:
                break;
        }
        setCursorToTheEnd();
    }

    private void calculateResults() {
        extractDigits();
        extractNonDigits();

        for (int i = 0; i < listSigns.size(); i++) {
            if (listSigns.get(i).equals("*") || listSigns.get(i).equals("/")) {
                int temp;
                if (listSigns.get(i).equals("*")) {
                    temp = Integer.parseInt(listNumbers.get(i)) * Integer.parseInt(listNumbers.get(i + 1));
                } else {
                    temp = Integer.parseInt(listNumbers.get(i)) / Integer.parseInt(listNumbers.get(i + 1));
                }
                listNumbers.remove(i);
                listNumbers.add(i, String.valueOf(temp));
                listNumbers.remove(i + 1);
                listSigns.remove(i);
                i -= 1;
            }
        }
        result = Integer.parseInt(listNumbers.getFirst());
        for (int i = 0; i < listSigns.size(); i++) {
            if (listSigns.get(i).equals("+")) {
                result += Integer.parseInt(listNumbers.get(i + 1));
            } else if (listSigns.get(i).equals("-")) {
                result -= Integer.parseInt(listNumbers.get(i + 1));
            }
        }
        showResult.setText("" + result);
    }

    private void clearData() {
        inputNumber.setText("");
        listNumbers.clear();
        listSigns.clear();
        result = 0;
        showResult.setText(R.string.no_results);
    }

    private void addOperand(String operand) {
        inputNumber.setText(displayedStr + operand);
    }

    private void extractDigits() {
        listNumbers.clear();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(displayedStr);
        while (m.find()) {
            listNumbers.add(m.group());
        }
    }

    private void extractNonDigits() {
        listSigns.clear();
        Pattern p = Pattern.compile("\\D+");
        Matcher m = p.matcher(displayedStr);
        while (m.find()) {
            listSigns.add(m.group());
        }
    }

    private void setCursorToTheEnd() {
        inputNumber.setSelection(inputNumber.length());
    }
}