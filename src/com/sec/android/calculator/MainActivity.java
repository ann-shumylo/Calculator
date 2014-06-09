package com.sec.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final CalculatorModel calculatorModelModel = new CalculatorModel();
        final CalculatorView calculatorView = new CalculatorView(this);
        CalculatorController calculatorController = new CalculatorController(this, calculatorModelModel, calculatorView);
        ViewGroup container = (ViewGroup) findViewById(R.id.mainLayout);
        container.addView(calculatorView);
        container.addView(calculatorController);
    }
}