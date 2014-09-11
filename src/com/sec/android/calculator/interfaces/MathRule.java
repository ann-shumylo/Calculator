package com.sec.android.calculator.interfaces;

import android.text.Editable;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public interface MathRule {
    public boolean applyRule(Editable s, int cursorPosition);
}
