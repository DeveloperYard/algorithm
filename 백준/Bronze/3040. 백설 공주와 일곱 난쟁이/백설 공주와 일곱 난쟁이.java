import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int[] combi;
	static int[] combinationIdx;
	public static void main(String[] args) {
		// 10P7
		Scanner sc = new Scanner(System.in);
		final int N = 10;
		final int R = 7;
		combi = new int[10];
		combinationIdx = new int[10];
		for(int i = 1; i <= 9; i++) {
			combi[i] = sc.nextInt();
		}
		
		int cnt = 0;
		while(++cnt <= R) combinationIdx[N-cnt] = 1; 
		do {
			int sum = 0;
			for(int i = 1; i<=9; i++) {
				if(combinationIdx[i] == 0) continue;
				sum += combi[i];
			}
			if(sum == 100) {
				for(int i = 1; i<=9; i++) {
					if(combinationIdx[i] == 0) continue;
					System.out.println(combi[i]);
				}
				break;
			}
		} while(nextCombination(combinationIdx));
	}
	
	public static boolean nextCombination(int[] p) {
		int N = p.length;
		int i = N-1;
		
		while(i > 1 && p[i-1] >= p[i]) i--;
		if(i == 1) {
			return false;
		}
		
		int j = N-1;
		while(i <= j && p[i-1] >= p[j]) j--;
		swap(p, i-1, j);
		
		
		int k = N-1;
		while(i < k) swap(p, i++, k--);
		
		return true;
	}
	
	public static void swap(int[] c, int i, int j) {
		int tmp = c[i];
		c[i] = c[j];
		c[j] = tmp;
	}
}
