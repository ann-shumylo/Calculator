package com.sec.android.calculator.mathrules.bracketsrules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class InputAfterOpenBracket extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        //prohibit input symbol after "(" if it's not [0...9] or [(] or [-]
        if (cursorPosition > 1 &&
                isDesiredCharBracket(s, cursorPosition, 2, "(") && !isDesiredCharBracket(s, cursorPosition, 1, "(") &&
                !isDesiredCharDigit(s, cursorPosition, 1) &&
                !String.valueOf(s.charAt(cursorPosition - 1)).equals("-")) {
            prohibitSymbolInput(s, cursorPosition);
            return true;
        }

        return false;
    }
}
