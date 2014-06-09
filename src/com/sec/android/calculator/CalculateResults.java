package com.sec.android.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.sec.android.calculator.OperatorPrecedences.OPERATORS;

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

    private static boolean isOperator(String token) {
        return OPERATORS.containsKey(token);
    }

    private static List<String> shuntingYardAlgorithm(List<String> list) {
        List<String> output = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();

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
