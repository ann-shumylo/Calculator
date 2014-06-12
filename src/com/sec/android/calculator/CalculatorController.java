package com.sec.android.calculator;

class CalculatorController {
    private final CalculatorView calculatorView;

    public CalculatorController(final CalculatorView calculatorView) {
        this.calculatorView = calculatorView;
    }

    public void onClearBtnClicked() {
        calculatorView.setEmptyView();
    }

    public void onBackspaceBtnClicked() {
        calculatorView.setBackspace();
    }

    public void onEqualBtnClicked() {
        calculatorView.setResult();
    }

    public void onDigitBtnClicked(int digitSymbol) {
        calculatorView.setInputtedDigitSymbol(digitSymbol);
    }

    public void onOperationBtnClicked(String operationSymbol) {
        calculatorView.setInputtedSymbol(operationSymbol);
    }

}


