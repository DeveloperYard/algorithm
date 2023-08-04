package com.ssafy.corona.app;

import java.util.*;

public class Main_백준_2164_카드2_김승우_180ms {
	
	static LinkedList<Integer> arr = new LinkedList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		for(int i = 0; i<n; i++) {
			arr.add(i+1); // O(n)
		}

		card_method();

		System.out.println(arr.get(0));
	}
	
	private static void card_method() { // O(n)
		while(arr.size() > 1) {
			arr.remove();
			arr.add(arr.peek());
			arr.remove();
		}
	}
}
