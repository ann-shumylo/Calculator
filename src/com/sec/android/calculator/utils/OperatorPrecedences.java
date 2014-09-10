package com.sec.android.calculator.utils;

import java.util.HashMap;
import java.util.Map;

public class OperatorPrecedences {
    public static final int LEFT_ASSOCIATIVITY = 0;
    public static final int RIGHT_ASSOCIATIVITY = 1;
    public static final Map<String, DetailedInfo> OPERATORS = new HashMap<>();

    public static class DetailedInfo {
        public final int precedence;
        public final int associativity;

        public DetailedInfo(int precedence, int associativity) {
            this.precedence = precedence;
            this.associativity = associativity;
        }
    }

    static {
        // Map<"token", []{precedence, associativity}>
        OPERATORS.put("+", new DetailedInfo(1, LEFT_ASSOCIATIVITY));
        OPERATORS.put("-", new DetailedInfo(1, LEFT_ASSOCIATIVITY));
        OPERATORS.put("*", new DetailedInfo(2, LEFT_ASSOCIATIVITY));
        OPERATORS.put("/", new DetailedInfo(2, LEFT_ASSOCIATIVITY));
        OPERATORS.put("^", new DetailedInfo(3, RIGHT_ASSOCIATIVITY));
        OPERATORS.put("%", new DetailedInfo(4, RIGHT_ASSOCIATIVITY));
        OPERATORS.put("sin", new DetailedInfo(5, LEFT_ASSOCIATIVITY));
        OPERATORS.put("cos", new DetailedInfo(5, LEFT_ASSOCIATIVITY));
    }
}
