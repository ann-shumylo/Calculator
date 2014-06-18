package com.sec.android.calculator.mathrules.pointrules;

import android.text.Editable;
import com.sec.android.calculator.utils.ActionCodesLinks;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class ValidatePointInNumber extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (cursorPosition > 0 && isDesiredCharPoint(s, cursorPosition, 1)) {
            if (cursorPosition < s.length()) {
                for (int j = cursorPosition; j < s.length() - 1; j++) {
                    if (ActionCodesLinks.isOperator(String.valueOf(s.charAt(cursorPosition + 1)))) {
                        break;
                    }
                    if (String.valueOf(s.charAt(j)).equals(".")) {
                        prohibitSymbolInput(s, cursorPosition);
                        return true;
                    }
                }
            }
            for (int i = cursorPosition - 2; i > 0; i--) {
                if (isDesiredCharOperator(s, i, 1)) {
                    break;
                }
                if (String.valueOf(s.charAt(i)).equals(".")) {
                    prohibitSymbolInput(s, cursorPosition);
                    return true;
                }
            }
        }
        return false;
    }
}
