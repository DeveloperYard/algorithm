import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		if(np(arr)) {
			for(int i = 0; i < N; i++) {
				System.out.print(arr[i]+" ");
			}
		} else {
			System.out.println("-1");
		}
		
		
	}
	
	private static boolean np(int[] p) {
		int N = p.length-1;
		int i = N;
		
		while(i > 0 && p[i-1] <= p[i]) i--;
		if(i == 0) return false;
		
		int j = N;
		while (j >= i && p[i-1] <= p[j]) j--;
		swap(p, i-1, j);
		
		j = N;
		while(i < j) swap(p, i++, j--);
		
		return true;
		
	}
	
	private static void swap(int[] p, int i, int j) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}
}
