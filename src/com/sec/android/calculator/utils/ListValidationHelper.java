package com.sec.android.calculator.utils;

import android.content.Context;
import android.widget.Toast;
import com.sec.android.calculator.CalculateResults;

import java.util.List;

public class ListValidationHelper {

    public static boolean isListValid(Context context, List<String> list) {
        return isBracketsCountValid(context, list) && isInputListCorrect(list);
    }

    private static boolean isInputListCorrect(List<String> list) {
        int count = 0;
        for (String token : CalculateResults.shuntingYardAlgorithm(list)) {
            if (!CalculateResults.isOperator(token)) {
                count++;
            } else {
                count--;
            }
        }
        return count == 1;
    }

    private static boolean isBracketsCountValid(Context context, List<String> list) {
        int count = 0;

        for (String temp : list) {
            if (temp.contains("(")) {
                count++;
            } else if (temp.contains(")")) {
                count--;
            }
        }
        if (count != 0) {
            Toast.makeText(context, "Invalid count of brackets", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}
