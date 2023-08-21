import java.util.*;

public class Main {
	
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		dp = new int[1001];
		
		tiling(n);
		
		System.out.println(dp[n]);
	}
	
	private static void tiling(int n) {
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1]+dp[i-2])%10007;
		}
	}
}
