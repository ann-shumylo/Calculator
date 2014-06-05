package com.sec.android.calculator;

import java.util.HashMap;
import java.util.Map;

public class OperatorPrecedences {
    static final int LEFT_ASSOCIATIVITY = 0;
    static final int RIGHT_ASSOCIATIVITY = 1;
    static final Map<String, DetailedInfo> OPERATORS = new HashMap<>();

    public static class DetailedInfo {
        int precedence;
        int associativity;

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
    }
}
