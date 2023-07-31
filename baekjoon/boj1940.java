package Algo;
 
import java.util.*;
import java.io.*;

class Jumong {
	static int start_idx = 0;
	static int end_idx = 0;
	static int count = 0;
	static int[] optionArr;
	
	static int printCount(int comp) {
//		System.out.println(Arrays.toString(optionArr));
		while (start_idx < end_idx) {
			if(comp > optionArr[start_idx] + optionArr[end_idx]) {
				start_idx++;
			} else if (comp < optionArr[start_idx] + optionArr[end_idx]) {
				end_idx--;
			} else {
//				System.out.println("start_idx : " + start_idx + " end_idx : " + end_idx);
				count++;
				start_idx++;
				end_idx--;
			}	
		}
		return count;
	}
}

public class boj1940{
	public static void main(String[] args) throws IOException{
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 
		 int N = Integer.valueOf(st.nextToken());
		 
		 
		 st = new StringTokenizer(br.readLine());
		 int m = Integer.valueOf(st.nextToken());
		 
		 st = new StringTokenizer(br.readLine());
		 int[] intArr = new int[N];
		 
		 for(int i = 0; i<N; i++) {
			 intArr[i] = Integer.parseInt(st.nextToken());
		 }
		 Jumong.optionArr = intArr;
		 Jumong.end_idx = N-1;
		 Arrays.sort(Jumong.optionArr);
		 
		 System.out.println(Jumong.printCount(m));
		 
		 
	}
}
