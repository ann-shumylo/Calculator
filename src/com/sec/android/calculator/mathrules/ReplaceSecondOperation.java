package com.sec.android.calculator.mathrules;

import android.text.Editable;
import com.sec.android.calculator.utils.ActionCodesLinks;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class ReplaceSecondOperation extends BasicRules {
    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (cursorPosition > 1 && ActionCodesLinks.isOperator(String.valueOf(s.charAt(cursorPosition - 1))) &&
                ActionCodesLinks.isOperator(String.valueOf(s.charAt(cursorPosition - 2)))) {
            removeExistedSymbol(s, cursorPosition);
            return true;
        }
        return false;
    }
}
