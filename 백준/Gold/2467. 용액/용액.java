import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int lp, rp;
	static int[] arr;
	static int min = Integer.MAX_VALUE;
	static int ls, rs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		lp = 0;
		rp = N-1;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
//			System.out.println(arr[i]);
		}
		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
		findMin();
		
		
		System.out.println(ls + " " + rs);
	}
	
	private static void findMin() {
		while(lp < rp) {
			int calVal = Math.abs(arr[lp]+arr[rp]);
			if(arr[lp]+arr[rp] >= 0) { // 차이가 0보다 큰 경우
				if(calVal <= min) {
//					System.out.println(calVal);
					ls = arr[lp];
					rs = arr[rp];
//					System.out.println("lp : " + lp + " rp : " + rp);
					min = calVal; 
				} 
				rp--;
			} else { // 차이가 0보다 작은 경우
				if(calVal <= min) {
//					System.out.println(calVal);
					ls = arr[lp];
					rs = arr[rp];
			
					min = calVal;
				}
				lp++;
			}
//			System.out.println("lp : " + lp + " rp : " + rp);
		}
	}
}
