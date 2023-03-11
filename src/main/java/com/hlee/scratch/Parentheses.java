package com.hlee.scratch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parentheses {

    // '{[()]}', '{}[](())'
    boolean isValidParentheses(String s) {
        if (s.length() == 0)
            return true;

        Map<Character, Character> parens = new HashMap<>();
        parens.put('(', ')');
        parens.put('{', '}');
        parens.put('[', ']');

        List<Character> stack = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (parens.containsKey(s.charAt(i))) {
                stack.add(s.charAt(i));
            } else {
                char leftBracket = stack.remove(stack.size() - 1);
                char correctRightBracket = parens.get(leftBracket);
                if (s.charAt(i) != correctRightBracket)
                    return false;
            }
        }
        return stack.size() == 0;
    }
}
