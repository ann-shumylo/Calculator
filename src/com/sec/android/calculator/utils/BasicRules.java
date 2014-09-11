package com.sec.android.calculator.utils;

import android.text.Editable;
import com.sec.android.calculator.interfaces.MathRule;

/**
 * @author Ganna Pliskovska(g.pliskovska@samsung.com)
 */
public abstract class BasicRules implements MathRule {
    protected int countBrackets(Editable s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (String.valueOf(s.charAt(i)).equals("(")) {
                count++;
            } else if (String.valueOf(s.charAt(i)).equals(")")) {
                count--;
            }
        }
        return count;
    }

    protected void prohibitSymbolInput(Editable s, int cursorPosition) {
        s.delete(cursorPosition - 1, cursorPosition);
    }

    protected void prohibitLiteralSymbolInput(Editable s, int cursorPosition) {
        s.delete(cursorPosition - 3, cursorPosition);
    }

    protected void removeExistedSymbol(Editable s, int cursorPosition) {
        s.delete(cursorPosition - 2, cursorPosition - 1);
    }

    protected boolean isDesiredCharBracket(Editable s, int cursorPosition, int shift, String bracket) {
        return String.valueOf(s.charAt(cursorPosition - shift)).equals(bracket);
    }

    protected boolean isDesiredCharDigit(Editable s, int cursorPosition, int shift) {
        return ActionCodesLinks.isDigit(String.valueOf(s.charAt(cursorPosition - shift)));
    }

    protected boolean isDesiredCharOperator(Editable s, int cursorPosition, int shift) {
        return ActionCodesLinks.isOperator(String.valueOf(s.charAt(cursorPosition - shift)));
    }

    protected boolean isDesiredCharPoint(Editable s, int cursorPosition, int shift) {
        return ActionCodesLinks.isPoint(String.valueOf(s.charAt(cursorPosition - shift)));
    }

    protected boolean isDesiredCharFunction(Editable s, int cursorPosition) {
        return ActionCodesLinks.isFunction(String.valueOf(s.subSequence(cursorPosition - 3, cursorPosition)));
    }

}
