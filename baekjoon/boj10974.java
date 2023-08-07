package boj;

import java.util.*;

public class boj10974 {
	
	static int n;
	static boolean[] visited;
	static int[] arr;
	static int[] val;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		visited = new boolean[n+1];
		arr = new int[n+1];
		val = new int[n+1];
		
		for(int i = 1; i<=n; i++) {
			arr[i] = i;
		}
		
		permutation(1, 1);
		
		System.out.println(sb);
	}
	
	private static void permutation(int index, int count) {
		if(count == n+1) {
			for(int i = 1; i<=n; i++) {
				sb.append(val[i]+" ");
			}
			sb.append("\n");
			return;
		}
		for(int i = 1; i <= n; i++) {
			if(!visited[i]) {
				val[count] = i;
				visited[i] = true;
				permutation(i, count+1);
				visited[i] = false;
			}
		}
	}
}

