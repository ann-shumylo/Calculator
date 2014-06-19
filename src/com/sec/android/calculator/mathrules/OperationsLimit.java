package com.sec.android.calculator.mathrules;

import android.text.Editable;
import com.sec.android.calculator.utils.ActionCodesLinks;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class OperationsLimit extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (isOperationsLimitReached(s)) {
            prohibitSymbolInput(s, cursorPosition);
            return true;
        }
        return false;
    }

    private boolean isOperationsLimitReached(Editable s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (ActionCodesLinks.isOperator(String.valueOf(s.charAt(i)))) {
                count++;
            } else if (count > 20) {
                return true;
            }
        }
        return false;
    }
}
