package com.leqcar.sandbox;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("joe", "mon", "tess");
		
		String concatNames = "";
		for (String t : names) {
			concatNames = concatNames + ", " + t;
		}
		System.out.println(concatNames);
		
		String a = names.stream()
			.collect(Collectors.joining(","));
		System.out.println(a);
	}

}
