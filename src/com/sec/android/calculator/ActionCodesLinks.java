package com.sec.android.calculator;

import android.util.SparseArray;
import android.util.SparseIntArray;

class ActionCodesLinks {
    static final SparseArray<String> BUTTON_ID_TO_OPERATION_CODE_LINK = new SparseArray<String>();
    static final SparseArray<String> BUTTON_ID_TO_BRACKETS_CODE_LINK = new SparseArray<String>();
    static final SparseIntArray BUTTON_ID_TO_DIGIT_CODE_LINK = new SparseIntArray();

    static {
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_addition, "+");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_subtraction, "-");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_division, "/");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_multiplication, "*");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_power, "^");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_percent, "%");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_sin, "sin");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_cos, "cos");
        BUTTON_ID_TO_OPERATION_CODE_LINK.put(R.id.btn_point, ".");
    }

    static {
        BUTTON_ID_TO_BRACKETS_CODE_LINK.put(R.id.btn_open_bracket, "(");
        BUTTON_ID_TO_BRACKETS_CODE_LINK.put(R.id.btn_close_bracket, ")");
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
}
