package com.sec.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

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