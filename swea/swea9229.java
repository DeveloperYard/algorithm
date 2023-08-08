package swea;

import java.io.*;
import java.util.*;

/*
 * two-pointer 를 사용해서 구현
 * */

public class swea9229 {
	
	static int[] arr;
	static int start_pointer;
	static int end_pointer;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test_case = Integer.parseInt(br.readLine());
		
		for(int i = 1; i<=test_case; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j<n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			int max = -1;
			int ans = -1;
			
			start_pointer = 0;
			end_pointer = n-1;
			
			while (start_pointer < end_pointer) { // 두개를 따로 구매해야 하므로 인덱스가 겹치게 되면 break
				
				int sum_val = arr[start_pointer] + arr[end_pointer];
				
				if(sum_val > max && sum_val <= m) {
					max = sum_val;
					ans = max;
				}
				
				if (sum_val >= m) { // 1) 만약 합친 값이 m보다 크거나 같다면 end pointer를 하나 내림
					end_pointer--;
				} else if(sum_val < m) { // 2) 만약 합친 값이 m보다 작다면 start pointer를 하나 올림
					start_pointer++;
				}
			}


			sb.append("#"+i+" "+ans+"\n");
		}
		System.out.println(sb.toString());
	}
}