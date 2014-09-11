package com.sec.android.calculator.interfaces;

import java.math.BigDecimal;

/**
 * @author Anna Pliskovska(anna.pliskovskaya@gmail.com)
 */
public interface OnCalculationListener {
    int ERROR_TYPE_DIVIDE_BY_ZERO = 1;

    void onResult(BigDecimal result);
    void onCalculationError(int errorType);
}
