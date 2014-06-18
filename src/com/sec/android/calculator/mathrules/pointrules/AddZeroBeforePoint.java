package com.sec.android.calculator.mathrules.pointrules;

import android.text.Editable;
import com.sec.android.calculator.utils.ActionCodesLinks;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class AddZeroBeforePoint extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (cursorPosition == 1) {
            //add zero before point at the start of string
            if (ActionCodesLinks.isPoint(String.valueOf(s.charAt(0)))) {
                s.insert(cursorPosition - 1, "0");
                return true;
            }
        } else if (cursorPosition > 2) {
            //add zero before point after [+-*/^%]
            if (isDesiredCharPoint(s, cursorPosition, 1) && isDesiredCharOperator(s, cursorPosition, 2)) {
                s.insert(cursorPosition - 1, "0");
                return true;
            }
        }
        return false;
    }
}
