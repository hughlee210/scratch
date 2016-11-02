package com.hlee.scratch;

public class NumberString {

	public static void main(String[] args) {

		String str = "1234567890";
		System.out.println(str + " is a valid number? " + isNumber(str));
		str = "-123";
		System.out.println(str + " is a valid number? " + isNumber(str));
		str = "-0123";
		System.out.println(str + " is a valid number? " + isNumber(str));
		str = "1";
		System.out.println(str + " is a valid number? " + isNumber(str));
		str = "-12.123";
		System.out.println(str + " is a valid number? " + isNumber(str));

		// using regex
		str = "1234567890";
		System.out.println(str + " is a valid number? " + isNumberUsingRegex(str));
		str = "-123";
		System.out.println(str + " is a valid number? " + isNumberUsingRegex(str));
		str = "-0123";
		System.out.println(str + " is a valid number? " + isNumberUsingRegex(str));
		str = "1";
		System.out.println(str + " is a valid number? " + isNumberUsingRegex(str));
		str = "-12.123";
		System.out.println(str + " is a valid number? " + isNumberUsingRegex(str));
		
	}

	// time: O(n), space: O(1)
	public static boolean isNumber(String str) {
		if (str == null || str.length() == 0)
			return false;
		char[] chars = str.toCharArray();
		boolean signed = false;
		for (int i = 0; i < chars.length; i++) {
			if (i == 0) {
				if (chars[i] == '-' || chars[i] == '+') {
					signed = true;
					continue;
				}
				else if (chars[i] - '0' == 0) {
					return false;
				}
			} else if (i == 1 && signed) {
				if (chars[i] - '0' == 0)
					return false;
			}
			int diff = chars[i] - '0';
			if (diff < 0 || diff > 9)
				return false;
		}
		return true;
	}

	public static boolean isNumberUsingRegex(String str) {
		return str.matches("-?[1-9]\\d*(\\.\\d)?");
	}

}
