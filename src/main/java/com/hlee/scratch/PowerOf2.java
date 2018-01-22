package com.hlee.scratch;

public class PowerOf2 {

	public static void main(String[] args) {
		
		//System.out.println("number: " + Math.pow(2, 16));

		int x = 65536;
		System.out.println(x + " is power of 2? " + isPowerOfTwo(x));
        x = 65537;
        System.out.println(x + " is power of 2? " + isPowerOfTwo(x));

	}
	
	static boolean isPowerOfTwo(int x) {
		return (x & (x-1)) == 0;
	}

}
