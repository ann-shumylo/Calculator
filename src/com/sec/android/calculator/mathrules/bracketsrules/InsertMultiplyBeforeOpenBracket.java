package com.sec.android.calculator.mathrules.bracketsrules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class InsertMultiplyBeforeOpenBracket extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (cursorPosition > 1 && isDesiredCharBracket(s, cursorPosition, 1, "(") && isDesiredCharDigit(s, cursorPosition, 2)) {
            s.insert(cursorPosition - 1, "*");
            return true;
        }
        return false;
    }
}