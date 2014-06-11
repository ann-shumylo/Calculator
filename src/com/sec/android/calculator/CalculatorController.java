package com.sec.android.calculator;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

class CalculatorController extends LinearLayout implements View.OnClickListener {
    private final CalculatorView calculatorView;


    public CalculatorController(Activity activity, final CalculatorView calculatorView) {
        super(activity);
        this.calculatorView = calculatorView;
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.keyboard, this);
        /*Numbers*/
        view.findViewById(R.id.btn_one).setOnClickListener(this);
        view.findViewById(R.id.btn_two).setOnClickListener(this);
        view.findViewById(R.id.btn_three).setOnClickListener(this);
        view.findViewById(R.id.btn_four).setOnClickListener(this);
        view.findViewById(R.id.btn_five).setOnClickListener(this);
        view.findViewById(R.id.btn_six).setOnClickListener(this);
        view.findViewById(R.id.btn_seven).setOnClickListener(this);
        view.findViewById(R.id.btn_eight).setOnClickListener(this);
        view.findViewById(R.id.btn_nine).setOnClickListener(this);
        view.findViewById(R.id.btn_zero).setOnClickListener(this);
        /*Operations*/
        view.findViewById(R.id.btn_addition).setOnClickListener(this);
        view.findViewById(R.id.btn_subtraction).setOnClickListener(this);
        view.findViewById(R.id.btn_division).setOnClickListener(this);
        view.findViewById(R.id.btn_multiplication).setOnClickListener(this);
        view.findViewById(R.id.btn_power).setOnClickListener(this);
        view.findViewById(R.id.btn_sin).setOnClickListener(this);
        view.findViewById(R.id.btn_cos).setOnClickListener(this);
        view.findViewById(R.id.btn_percent).setOnClickListener(this);
        view.findViewById(R.id.btn_open_bracket).setOnClickListener(this);
        view.findViewById(R.id.btn_close_bracket).setOnClickListener(this);
        view.findViewById(R.id.btn_point).setOnClickListener(this);
        /*Equal, Clear, Backspace*/
        Button btnClear = (Button) view.findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                calculatorView.setEmptyView();
            }
        });
        Button btnEqual = (Button) view.findViewById(R.id.btn_equal);
        btnEqual.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                calculatorView.setResult();
            }
        });
        Button btnBackspace = (Button) view.findViewById(R.id.btn_backspace);
        btnBackspace.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                calculatorView.setBackspace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String inputSymbol;

        if (ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.get(v.getId()) != null) {
            inputSymbol = ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.get(v.getId());
            calculatorView.setInputtedSymbol(inputSymbol);
        } else {
            inputSymbol = String.valueOf(ActionCodesLinks.BUTTON_ID_TO_DIGIT_CODE_LINK.get(v.getId()));
            calculatorView.setInputtedSymbol(inputSymbol);
        }
    }
}


