import java.io.*;
import java.util.*;

public class Main {
	
	static int R, N;
	static StringBuilder sb = new StringBuilder();
	static char[] alpArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		alpArr = new char[N];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			alpArr[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alpArr);
		
		int[] mask = new int[N];
		
		for(int i = 0; i < R; i++) {
			mask[i] = 1;
		}
		//System.out.println(Arrays.toString(mask));
		do {
			StringBuilder tmpSb = new StringBuilder();
			//System.out.println(Arrays.toString(mask));
			for(int i = 0; i < N; i++) {
				if(mask[i] == 1) {
					
					tmpSb.append(alpArr[i]);
				}	
			}
			if(checkString(tmpSb.toString())) {
				sb.append(tmpSb.toString()).append("\n");
			}
			
		} while(np(mask));
		
		System.out.println(sb.toString());
	} // end of main
	
	private static boolean checkString(String s) {
		for(int i = 0; i < s.length()-1; i++) { // 사전 순 검사
			if(s.charAt(i) > s.charAt(i+1)) {
				return false;
			}
		}
		
		int vowelCnt = 0;
		int consonantCnt = 0;
		
		for(int i = 0; i < s.length(); i++) {	
			switch (s.charAt(i)) {
				case 'a':
					vowelCnt++;
					break;
				case 'e':
					vowelCnt++;
					break;
				case 'i':
					vowelCnt++;
					break;
				case 'o':
					vowelCnt++;
					break;
				case 'u':
					vowelCnt++;
					break;
				default:
					consonantCnt++;
					break;
				}
		}
		if(vowelCnt < 1 || consonantCnt < 2) return false;
		return true;
	}
	
	private static boolean np(int[] p) {
		
		int N = p.length-1;
		int i = N;
		while(i > 0 && p[i-1] <= p[i]) i--;
		if(i == 0) return false;
		
		int j = N;
		while(j >= i && p[i-1] <= p[j]) j--;
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
} // end of class
