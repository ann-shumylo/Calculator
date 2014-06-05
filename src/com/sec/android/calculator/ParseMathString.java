package com.sec.android.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.sec.android.calculator.OperatorPrecedences.OPERATORS;

public class ParseMathString {
    static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    static List<String> shuntingYardAlgorithm(List<String> list) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : list) {
            if (isOperator(token)) {
                while (!stack.empty() && isOperator(stack.peek())) {
                    if ((OPERATORS.get(token).associativity == OperatorPrecedences.LEFT_ASSOCIATIVITY &&
                            OPERATORS.get(token).precedence <= OPERATORS.get(stack.peek()).precedence) ||
                            (OPERATORS.get(token).associativity == OperatorPrecedences.RIGHT_ASSOCIATIVITY &&
                            OPERATORS.get(token).precedence < OPERATORS.get(stack.peek()).precedence)) {
                        output.add(stack.pop());
                        continue;
                    }
                    break;
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.empty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                stack.pop();
            } else if (!isOperator(token)) {
                output.add(token);
            }

        }
        while (!stack.empty()) {
            output.add(stack.pop());
        }
        return output;
    }
}
