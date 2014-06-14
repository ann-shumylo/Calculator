package com.sec.android.calculator;

import com.sec.android.calculator.utils.OperatorPrecedences;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.sec.android.calculator.utils.OperatorPrecedences.OPERATORS;

public class CalculateResults {

    static double reversePolishNotation(List<String> list) {
        Stack<String> tempStack = new Stack<String>();

        for (String token : shuntingYardAlgorithm(list)) {
            if (!isOperator(token)) {
                tempStack.push(token);
            } else {
                Double num2 = Double.valueOf(tempStack.pop());
                Double num1 = null;
                if (!token.equals("s") && !token.equals("c")) {
                    num1 = Double.valueOf(tempStack.pop());
                }

                double result = 0;

                switch (token) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                    case "^":
                        result = Math.pow(num1, num2);
                        break;
                    case "%":
                        result = (num1 * num2) / 100;
                        break;
                    case "s":
                        result = Math.sin(num2);
                        break;
                    case "c":
                        result = Math.cos(num2);
                        break;
                }
                tempStack.push(String.valueOf(result));
            }
        }
        return Double.valueOf(tempStack.pop());
    }

    public static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    public static List<String> shuntingYardAlgorithm(List<String> list) {
        List<String> output = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();

        for (String token : list) {
            if (isOperator(token)) {
                while (!stack.empty() && isOperator(stack.peek())) {
                    if ((isAssociative(token, OperatorPrecedences.LEFT_ASSOCIATIVITY) && cmpPrecedence(token, stack.peek()) <= 0)
                            || isAssociative(token, OperatorPrecedences.RIGHT_ASSOCIATIVITY) && cmpPrecedence(token, stack.peek()) < 0) {
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


    /**
     * Test the associativity of a certain operator token.
     *
     * @param token The token to be tested (needs to operator).
     * @param type  LEFT_ASSOCIATIVITY or RIGHT_ASSOCIATIVITY
     * @return True if the tokenType equals the input parameter type.
     */
    private static boolean isAssociative(String token, int type) {
        if (!isOperator(token)) {
            throw new IllegalArgumentException("Invalid token: " + token);
        }
        return OPERATORS.get(token).associativity == type;
    }

    /**
     * Compare precedence of two operators.
     */
    private static int cmpPrecedence(String token1, String token2) {
        if (!isOperator(token1) || !isOperator(token2)) {
            throw new IllegalArgumentException("Invalid tokens: " + token1
                    + " " + token2);
        }
        return OPERATORS.get(token1).precedence - OPERATORS.get(token2).precedence;
    }
}
