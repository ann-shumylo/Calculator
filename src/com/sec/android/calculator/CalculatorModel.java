package com.sec.android.calculator;

public class CalculatorModel {
    private String inputSymbol;

    public void setOperand(String value) {
        inputSymbol = value;
    }

    public void setDigit(int digit) {
        inputSymbol = String.valueOf(digit);
    }

    public String getModel() {
        return inputSymbol;
    }
}
