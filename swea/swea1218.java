package com.ssafy.corona.app;

// parenthesis
import java.util.*;
import java.io.*;

public class swea1218 {
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int t = 1; t<=10; t++) {
			int n = Integer.parseInt(br.readLine());
			char[] c = br.readLine().toCharArray();
			Deque<Character> dq = new LinkedList<>();
			boolean boolFlag = false;
			
			for(int i = 0; i<n; i++) {
				if(c[i] == ')') {
					if(dq.isEmpty()) {
						boolFlag = true;
						break;
					}
					else {
						if(dq.pollLast() == '(') {
							continue;
						} else {
							boolFlag = true;
							break;
						}
					}
				} else if(c[i] == ']') {
					if(dq.isEmpty()) {
						boolFlag = true;
						break;
					}
					else {
						if(dq.pollLast() == '[') {
							continue;
						} else {
							boolFlag = true;
							break;
						}
					}
					
				} else if(c[i] == '}') {
					if(dq.isEmpty()) {
						boolFlag = true;
						break;
					}
					else {
						if(dq.pollLast() == '{') {
							continue;
						} else {
							boolFlag = true;
							break;
						}
					}
					
				} else if(c[i] == '>') {
					if(dq.isEmpty()) {
						boolFlag = true;
						break;
					}
					else {
						if(dq.pollLast() == '<') {
							continue;
						} else {
							boolFlag = true;
							break;
						}
					}
				} else {
					dq.add(c[i]);
				}
			}
			if (boolFlag || !dq.isEmpty()) {
				sb.append("#"+t+" "+"0"+"\n");
			} else {
				sb.append("#"+t+" "+"1"+"\n");
			}
			// 검증 후 deque에 남아있다면 0, 아니면 1
			
		}
		
		System.out.println(sb.toString());
	}
}
