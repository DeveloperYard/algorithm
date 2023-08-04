package java_boj;

import java.util.*;
import java.io.*;

public class boj11723 {
	public static void main(String[] args) throws IOException{
		HashSet<String> set = new HashSet<>();
		int[] arr = new int[21];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(st.nextToken());
		for(int ts = 1; ts<=t; ts++) {
			int num = 0;
			st = new StringTokenizer(br.readLine(), " ");
			String method = st.nextToken();

			if (method.equals("all")) {
				for(int i = 1; i <= 20; i++) {
					arr[i] = 1;
				}
				continue;
			}
			if (st.hasMoreTokens()) {
				num = Integer.parseInt(st.nextToken());
			}
			
			switch(method){
			case "add" :{
				arr[num] = 1;
				break;
			}
			case "remove" : {
				arr[num] = 0;
				break;
			}
			case "check" : {
				if(arr[num] == 1) {
					sb.append("1"+"\n");
				} else {
					sb.append("0"+"\n");
				}
				break;
			}
			case "toggle" : { // O(n)의 시간 복잡도를 가짐
				if(arr[num] == 0) {
					arr[num] = 1;
					break;
				}
				else {
					arr[num] = 0;
				}
				break;
			}
			case "empty": { 
				arr = new int[21];
			}
			}
		}
		
		System.out.println(sb.toString());
	}
}
