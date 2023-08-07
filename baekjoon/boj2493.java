package boj;

import java.util.*;
import java.io.*;

public class boj2493 {
	static int[] arr;
	static int[] dp;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1];
		dp = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dp[1] = 0;
		sol();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i<=n; i++) {
			sb.append(dp[i]+" ");
		}
		
		System.out.println(sb);
		
		
	}
	
	public static void sol() {
		for(int i = 2; i<=n; i++) {
			if (arr[i-1] > arr[i]) {
				dp[i] = i-1;
			} else if(arr[dp[i-1]] > arr[i]) {
				dp[i] = dp[i-1];
			} else {
				int k = i;
				while (k >= 1) {
					if(arr[k] > arr[i]) {
						dp[i] = k;
						break;
					}
					k-=1;
				}
			}
		}
	}
}
