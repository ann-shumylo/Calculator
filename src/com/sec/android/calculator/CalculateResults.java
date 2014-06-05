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
                Float num2 = Float.valueOf(tempStack.pop());
                Float num1 = Float.valueOf(tempStack.pop());

                Float result = null;

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
                }
                tempStack.push(String.valueOf(result));
            }
        }
        return Float.valueOf(tempStack.pop());
    }
}
