package com.sec.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        final CalculatorView calculatorView = new CalculatorView(this, rootView);
    }
}