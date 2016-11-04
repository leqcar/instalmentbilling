package com.leqcar.sandbox;

public class Test {

	public static void main(String[] args) {

		int x = 22;
		System.out.println(isPlan(x));

	}

	private static boolean isPlan(int key) {
		switch (key) {
		case(1): 
		case(2): return true;
		case(0):
		case(3):
		default:
			return false;
		}		
	}
}
