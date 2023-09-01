import java.io.*;
import java.util.*;

public class Main {
	
	static int A, B;
	static int dest;
	static int LIMIT = 100_000;
	static int[] dp = new int[LIMIT+1];
	static int[] dx;
	static class Location {
		int x;
		int moveCnt;
		
		Location(int x, int moveCnt) {
			this.x = x;
			this.moveCnt = moveCnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
		dx = new int[] {-1, 1, A, -A, B, -B, A, B};
		
		bfs(start);

		
		
		System.out.println(dp[dest]-1);
	}
	
	public static void bfs(int n) {
		Deque<Location> q = new ArrayDeque<>();
		
		q.offer(new Location(n, 1));
		dp[n] = 1;
		while(!q.isEmpty()) {
			Location cur = q.poll();
			
			if(cur.x == dest) {
				return;
			}
			
			for(int i = 0; i < 8; i++) {
				int nx = (i < 6) ? cur.x + dx[i] : cur.x*dx[i];
				
				if(!checkPos(nx)) continue;
				dp[nx] = dp[cur.x]+1;
				q.offer(new Location(nx, cur.moveCnt+1));
			}
		}
	}
	
	private static boolean checkPos(int x) {
		return (x > LIMIT || x < 0 || dp[x] > 0) ? false : true;
	}
}
