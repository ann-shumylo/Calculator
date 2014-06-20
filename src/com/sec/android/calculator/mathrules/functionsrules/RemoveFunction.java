package com.sec.android.calculator.mathrules.functionsrules;

import android.text.Editable;
import com.sec.android.calculator.utils.BasicRules;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public class RemoveFunction extends BasicRules {

    @Override
    public boolean applyRule(Editable s, int cursorPosition) {
        for (int i = 0; i < s.length() - 2; i++) {
            if (String.valueOf(s.charAt(i)).equals("s")) {
                if (String.valueOf(s.charAt(i + 1)).equals("n")) {
                    s.delete(i, i + 2);
                    return true;
                }
                if (String.valueOf(s.charAt(i + 1)).equals("i") && !String.valueOf(s.charAt(i + 2)).equals("n")) {
                    s.delete(i, i + 2);
                    return true;
                }
            }
            if (String.valueOf(s.charAt(i)).equals("c")) {
                if (String.valueOf(s.charAt(i + 1)).equals("s")) {
                    s.delete(i, i + 2);
                    return true;
                }
                if (String.valueOf(s.charAt(i + 1)).equals("o") && !String.valueOf(s.charAt(i + 2)).equals("s")) {
                    s.delete(i, i + 2);
                    return true;
                }
            }
        }
        return false;
    }
}
