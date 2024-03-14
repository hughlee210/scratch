package com.hlee.scratch.stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Parentheses {

    public static void main(String[] args) {
        String input = "{[()]}";
        testIsValue(input);

        input = "{}[](())";
        testIsValue(input);

        input = "{}[(())";
        testIsValue(input);

        input = "(";
        testIsValue(input);

        input = "((";
        testIsValue(input);

        input = ")";
        testIsValue(input);

        input = "))";
        testIsValue(input);

    }

    static void testIsValue(String input) {
        boolean isValid = isValidParentheses(input);
        System.out.println("input: " + input + ", isValidParenthesis: " + isValid);
    }

    /**
     * '{[()]}', '{}[](())', '', '(', ')'
     * assume string only contains parenthesis characters
     *
     * Time complexity: O(n)
     * Space complexity: O(n) for stack
     */
    static boolean isValidParentheses(String s) {
        if (s.length() == 0)
            return true;

        Map<Character, Character> parensMap = new HashMap<>(); // define parentheses pair (starting : closing)
        parensMap.put('(', ')');
        parensMap.put('{', '}');
        parensMap.put('[', ']');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (parensMap.containsKey(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                // current character (s.charAt(i)) is right bracket,
                // so expect stack top has matching left bracket
                if (stack.isEmpty()) {
                    return false;
                }
                char leftBracket = stack.pop();
                char correctRightBracket = parensMap.get(leftBracket);
                if (s.charAt(i) != correctRightBracket)
                    return false;
            }
        }
        return stack.size() == 0;
    }
}
