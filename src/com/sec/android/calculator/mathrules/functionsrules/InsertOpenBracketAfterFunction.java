package com.sec.android.calculator.mathrules.functionsrules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class InsertOpenBracketAfterFunction extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (cursorPosition > 2 && isDesiredCharFunction(s, cursorPosition)) {
            s.insert(cursorPosition, "(");
            return true;
        }
        return false;
    }
}
