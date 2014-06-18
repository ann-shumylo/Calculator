package com.sec.android.calculator.mathrules.bracketsrules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class CheckCountOfBrackets extends BasicRules{

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        //prohibit input ")" if count of closed brackets > open brackets
        if (cursorPosition > 1 && isDesiredCharBracket(s, cursorPosition, 1, ")") && countBrackets(s) < 0) {
            prohibitSymbolInput(s, cursorPosition);
            return true;
        }
        return false;
    }
}
