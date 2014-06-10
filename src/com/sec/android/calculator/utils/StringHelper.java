package com.sec.android.calculator.utils;

import java.util.List;

public class StringHelper {

    public static boolean isBracketsCountValid(List<String> list) {
        int count = 0;

        for (String temp : list) {
            if (temp.contains("(")) {
                count++;
            } else if (temp.contains(")")) {
                count--;
            }
        }
        return count == 0;
    }

    public static String formatStringResult(double doubleNum) {
        if (doubleNum % 1 == 0) {
            return String.format("%d", (int) doubleNum);
        } else {
            return String.format("%s", doubleNum);
        }
    }
}
