import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int TC;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		TC = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < TC; i++) {
			N = Integer.parseInt(br.readLine());
			dp = new int[11];
			dp[1] = 1;
			dp[2] = 1;
			dp[3] = 1;
			dpm(N);
			
			sb.append(dp[N]).append("\n");
		}
		
		System.out.println(sb);
		
		
	} // end of main
	
	private static void dpm(int n) {
		for(int i = 1; i <= n; i++) {
			dp[i] += dp[i-1];
			if(i > 2) {				
				dp[i] += dp[i-2];
			}
			if(i > 3) {
				dp[i] += dp[i-3];
			}
		}
	}
} // end of class
