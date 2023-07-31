package algo;

import java.io.*;
import java.util.*;

class Fibonacci {
	static int[] dp = new int[46];
	
	static {
		dp[0] = 0;
		dp[1] = 1;
		for(int i = 2; i<46; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
	}
}

public class boj2747 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		System.out.println(Fibonacci.dp[Integer.valueOf(st.nextToken())]);
	}
}
