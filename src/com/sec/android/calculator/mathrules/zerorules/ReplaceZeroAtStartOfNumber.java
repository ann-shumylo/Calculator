package com.sec.android.calculator.mathrules.zerorules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class ReplaceZeroAtStartOfNumber extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        if (cursorPosition == 2) {
            //delete zero in the start of number at (start of string)
            if (String.valueOf(s.charAt(cursorPosition - 2)).equals("0") &&
                    isDesiredCharDigit(s, cursorPosition, 1)) {
                s.delete(cursorPosition - 2, cursorPosition - 1);
                return true;
            }
        }
        //delete zero in the start of number
         else if (cursorPosition > 2 &&
                String.valueOf(s.charAt(cursorPosition - 2)).equals("0") &&
                !isDesiredCharDigit(s, cursorPosition, 3) && !isDesiredCharPoint(s, cursorPosition, 3) &&
                isDesiredCharDigit(s, cursorPosition, 1)) {
            removeExistedSymbol(s, cursorPosition);
            return true;
        }
        return false;
    }
}
