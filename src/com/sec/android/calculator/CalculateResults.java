package com.sec.android.calculator;

import java.util.List;
import java.util.Stack;

public class CalculateResults {
    //reverse polish notation
    static float reversePolishNotation(List<String> list) {
        Stack<String> tempStack = new Stack<>();

        for (String token : ParseMathString.shuntingYardAlgorithm(list)) {
            if (!ParseMathString.isOperator(token)) {
                tempStack.push(token);
            } else {
                Double num2 = Double.valueOf(tempStack.pop());
                Double num1 = Double.valueOf(tempStack.pop());

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
                }
                tempStack.push(String.valueOf(result));
            }
        }
        return Float.valueOf(tempStack.pop());
    }
}
