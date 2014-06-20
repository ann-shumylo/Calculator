package com.sec.android.calculator.mathrules.functionsrules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class InsertFunction extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (cursorPosition > 3) {
            if (isDesiredCharFunction(s, cursorPosition) &&
                    !isDesiredCharOperator(s, cursorPosition, 4) &&
                    !isDesiredCharBracket(s, cursorPosition, 4, "(")) {
               prohibitLiteralSymbolInput(s, cursorPosition);
            }
        }
        return false;
    }
}