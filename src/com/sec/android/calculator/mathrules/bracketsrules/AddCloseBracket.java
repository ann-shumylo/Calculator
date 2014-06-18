package com.sec.android.calculator.mathrules.bracketsrules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class AddCloseBracket extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        //prohibit input ")" if previous symbol not [0...9] or [)]
        if (cursorPosition > 1 && isDesiredCharBracket(s, cursorPosition, 1, ")") &&
                !isDesiredCharBracket(s, cursorPosition, 2, ")") &&
                !isDesiredCharDigit(s, cursorPosition, 2)) {
            prohibitSymbolInput(s, cursorPosition);
            return true;
        }
        return false;
    }
}
