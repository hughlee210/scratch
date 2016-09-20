package com.hlee.scratch;

public class NumberString {

	public static void main(String[] args) {

		String str = "1234567890";
		boolean validNum = isNumber(str);
		System.out.println(str + " is a valid number? " + validNum);

	}

	public static boolean isNumber(String str) {
		if (str == null || str.length() == 0)
			return false;
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (i == 0) {
				if (chars[i] == '-' || chars[i] == '+')
					continue;
			}

			int diff = chars[i] - '0';
			if (diff < 0 || diff > 9)
				return false;
		}
		return true;
	}
}
