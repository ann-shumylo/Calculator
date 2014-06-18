package com.sec.android.calculator.utils;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.sec.android.calculator.R;

public class ActionCodesLinks {
    public static final SparseArray<String> BUTTON_ID_TO_OPERATION_CODE_LINK = new SparseArray<String>();
    public static final SparseArray<String> BUTTON_ID_TO_LITERAL_OPERATION_CODE_LINK = new SparseArray<String>();
    public static final SparseArray<String> BUTTON_ID_TO_BRACKET_CODE_LINK = new SparseArray<String>();
    public static final SparseArray<String> BUTTON_ID_TO_POINT_CODE_LINK = new SparseArray<String>();
    public static final SparseIntArray BUTTON_ID_TO_DIGIT_CODE_LINK = new SparseIntArray();

    static {
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_addition, "+");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_subtraction, "-");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_division, "/");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_multiplication, "*");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_power, "^");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_percent, "%");
    }

    static {
        BUTTON_ID_TO_LITERAL_OPERATION_CODE_LINK.put(R.id.btn_sin, "sin(");
        BUTTON_ID_TO_LITERAL_OPERATION_CODE_LINK.put(R.id.btn_cos, "cos(");
    }

    static {
        BUTTON_ID_TO_POINT_CODE_LINK.put(R.id.btn_point, ".");
    }

    static {
        BUTTON_ID_TO_BRACKET_CODE_LINK.put(R.id.btn_open_bracket, "(");
        BUTTON_ID_TO_BRACKET_CODE_LINK.put(R.id.btn_close_bracket, ")");
    }

    static {
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_one, 1);
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_two, 2);
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_three, 3);
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_four, 4);
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_five, 5);
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_six, 6);
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_seven, 7);
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_eight, 8);
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_nine, 9);
        BUTTON_ID_TO_DIGIT_CODE_LINK.put(R.id.btn_zero, 0);
    }

    public static boolean isOperator(String str) {
        for (int i = 0; i < ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.size(); i++) {
            if (ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.valueAt(i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLiteralOperator(String str) {
        for (int i = 0; i < ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.size(); i++) {
            if (ActionCodesLinks.BUTTON_ID_TO_OPERATION_CODE_LINK.valueAt(i).equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPoint(String str) {
        return ActionCodesLinks.BUTTON_ID_TO_POINT_CODE_LINK.valueAt(0).equals(str);
    }

    public static  boolean isDigit(String str) {
        for (int i = 0; i < ActionCodesLinks.BUTTON_ID_TO_DIGIT_CODE_LINK.size(); i++) {
            if (String.valueOf(ActionCodesLinks.BUTTON_ID_TO_DIGIT_CODE_LINK.valueAt(i)).equals(str)) {
                return true;
            }
        }
        return false;
    }
}
