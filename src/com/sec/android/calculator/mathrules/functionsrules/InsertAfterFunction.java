package com.sec.android.calculator.mathrules.functionsrules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class InsertAfterFunction extends BasicRules {
    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (cursorPosition > 3) {
            if (isDesiredCharFunction(s, cursorPosition - 1) &&
                    !isDesiredCharDigit(s, cursorPosition, 1) && !isDesiredCharBracket(s, cursorPosition, 1, "(")) {
                prohibitSymbolInput(s, cursorPosition);
            }
        }
        return false;
    }
}
