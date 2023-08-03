package java_swea;

import java.util.*;
import java.io.*;

class KillFly {
	static int M;
	static int[][] sumArr;
	static int[][] flyArr;
	
	
	static void initSumArr(int N) {
		// sumArr[0][i], sumArr[i][0] 구하기
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				sumArr[i][j] = sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1] + flyArr[i][j];
			}
		}
	}
	
	static int maxSum(int n) {
		int max = -1;
		
		for(int i = n; i < sumArr.length; i++) {
			for(int j = n; j<sumArr.length; j++) {
				int sumVal = sumArr[i][j] - sumArr[i-n][j] - sumArr[i][j-n] + sumArr[i-n][j-n];
				if (sumVal > max) {
					max = sumVal;
				}
			}
		}
		
		return max;
	}
}

public class swea2001 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		
		StringBuilder sb = new StringBuilder();
		
		for(int test_case = 1; test_case <= t; test_case ++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			KillFly.flyArr = new int[N+1][N+1];
			KillFly.sumArr = new int[N+1][N+1];
			
			for(int i = 1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j<=N; j++) {
					KillFly.flyArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			KillFly.initSumArr(N);
			
			
			sb.append("#"+test_case + " " + KillFly.maxSum(K) + "\n");
		}
		System.out.println(sb.toString());
		
		
		
		
		
		
	}
}
