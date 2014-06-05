package com.sec.android.calculator.old;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.calculator.R;

public class MainActivity2 extends Activity implements View.OnClickListener {
    int num;
    int numTemp;
    char operand = 0;
    private EditText inputNumber;
    private Button equal;
    private Button summation;
    private Button subtraction;
    private Button division;
    private Button multiply;
    private Button clear;
    private TextView showResult;

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

        num = Integer.parseInt(inputNumber.getText().toString());

        switch (view.getId()) {
            case R.id.btn_addition:
                inputNumber.setText("");
                numTemp = num;
                operand = '+';
                break;
            case R.id.btn_equal:
                calculateResults();
                break;
            default:
                break;
        }
    }

    private void calculateResults() {
        if (operand == '+') {
            num = numTemp + num;
        }
        showResult.setText("" + num);
    }

    private void clearData() {
        inputNumber.setText("");
        showResult.setText(R.string.no_results);
        num = 0;
        numTemp = 0;
    }
}
