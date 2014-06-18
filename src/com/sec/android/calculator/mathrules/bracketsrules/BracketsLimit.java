package com.sec.android.calculator.mathrules.bracketsrules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class BracketsLimit extends BasicRules {
    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (isBracketsLimitReached(s)) {
            prohibitSymbolInput(s, cursorPosition);
            return true;
        }
        return false;
    }

    private boolean isBracketsLimitReached(Editable s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (String.valueOf(s.charAt(i)).equals("(")) {
                count++;
            } else if (count > 5) {
                return true;
            }
        }
        return false;
    }
}
