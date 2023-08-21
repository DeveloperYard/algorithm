import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dp;
	private static int start;
	private static int dest;
	private static int[] dx = {-1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		start = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
		
		dp = new int[100001];
		
		
		bfs(start);
		
		System.out.println(dp[dest]-1);
	}
	
	private static void bfs(int x) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(x);
		dp[x] = 1;
		while (!q.isEmpty()) {
			int cur = q.poll();
			if(cur*2 <= 100001 && dp[cur*2] == 0) {
				dp[cur*2] = dp[cur]+1;
				q.offer(cur*2);
			}
			
			for(int i = 0; i < 2; i++) {
				int nx = cur + dx[i];
				if(!checkPos(nx)) continue;
				if(dp[nx] > 0) continue;
				
				dp[nx] = dp[cur]+1;
				q.offer(nx);
			}
			
		}
	}
	
	private static boolean checkPos(int x) {
		if(start > dest) {
			return (x < 0 || x >= start) ? false : true;
		} else {
			return (x < 0 || x > 100000) ? false : true;
		}
	}
}
