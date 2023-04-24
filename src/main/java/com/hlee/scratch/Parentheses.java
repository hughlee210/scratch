package com.hlee.scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parentheses {

    // '{[()]}', '{}[](())'
    // assume string only contains parenthesis characters
    boolean isValidParentheses(String s) {
        if (s.length() == 0)
            return true;

        Map<Character, Character> parensMap = new HashMap<>(); // define parentheses pair (starting : closing)
        parensMap.put('(', ')');
        parensMap.put('{', '}');
        parensMap.put('[', ']');

        List<Character> stack = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (parensMap.containsKey(s.charAt(i))) {
                stack.add(s.charAt(i));
            } else {
                // current character (s.charAt(i)) is right bracket,
                // so expect stack top has matching left bracket
                char leftBracket = stack.remove(stack.size() - 1);
                char correctRightBracket = parensMap.get(leftBracket);
                if (s.charAt(i) != correctRightBracket)
                    return false;
            }
        }
        return stack.size() == 0;
    }
}
