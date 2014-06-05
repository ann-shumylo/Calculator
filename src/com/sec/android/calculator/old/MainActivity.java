package com.sec.android.calculator.old;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.calculator.R;

import java.text.DecimalFormat;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText firstNumber;
    EditText secondNumber;
    Button summation;
    Button subtraction;
    Button division;
    Button multiply;
    Button clear;
    TextView showResult;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initElements();
        initButtonsListeners();

    }

    private void initElements() {
        firstNumber = (EditText) findViewById(R.id.first_number);
//        secondNumber = (EditText) findViewById(R.id.second_number);
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
                firstNumber.setText("");
                secondNumber.setText("");
                showResult.setText(R.string.no_results);
            }
        });

    }

    @Override
    public void onClick(View view) {
        float num1;
        float num2;
        float result = 0;
        char operand = 0;

        if (TextUtils.isEmpty(firstNumber.getText().toString())
                || TextUtils.isEmpty(secondNumber.getText().toString())) {
            return;
        }

        num1 = Float.parseFloat(firstNumber.getText().toString());
        num2 = Float.parseFloat(secondNumber.getText().toString());

        DecimalFormat df = new DecimalFormat();
        df.setDecimalSeparatorAlwaysShown(true);
        df.setMaximumIntegerDigits(4);


        switch (view.getId()) {
            case R.id.btn_addition:
                operand = '+';
                result = num1 + num2;
                break;
            case R.id.btn_subtraction:
                operand = '-';
                result = num1 - num2;
                break;
            case R.id.btn_division:
                operand = '/';
                result = num1 / num2;
                break;
            case R.id.btn_multiplication:
                operand = '*';
                result = num1 * num2;
                break;
            default:
                break;
        }
        showResult.setText(num1 + " " + operand + " " + num2 + " = " + result);
    }
}
