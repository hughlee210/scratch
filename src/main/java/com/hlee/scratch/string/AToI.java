package com.hlee.scratch.string;

public class AToI {

    public static void main(String[] args) {
        String str = "12345";
        System.out.printf("str = %s, aToI = %d\n", str, aToI(str));

        str = "-12345";
        System.out.printf("str = %s, aToI = %d\n", str, aToI(str));
    }

    /**
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    static Integer aToI(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        boolean isNeg = false;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char currChar = s.charAt(i);
            if (i == 0 && currChar == '-') {
                isNeg = true;
                continue;
            }
            result = result * 10 + (currChar - '0');
        }
        if (isNeg) {
            result *= -1;
        }
        return result;
    }
}
