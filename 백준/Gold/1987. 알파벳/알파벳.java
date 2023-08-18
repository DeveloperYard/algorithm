import java.util.*;
import java.io.*;

public class Main {
	static int R, C;
	
	static int count = -1;
	
	static char[][] map;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int[] visited = new int[26];
		visited[map[0][0]-'A'] = 1;
		
		dfs(0, 0, 0, visited);
		
		
		System.out.println(count+1);
		
	} // end of main
	
	private static void dfs(int x, int y, int cnt, int[] arr) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			int[] visitedArr = Arrays.copyOf(arr, arr.length);
			if(!checkPs(nx, ny) || visitedArr[map[nx][ny]-'A'] > 0) {
				if(cnt > count) count = cnt;
				continue;
			}
		
			visitedArr[map[nx][ny]-'A'] += 1;
			dfs(nx, ny, cnt+1, visitedArr);
		}
	}
	
	private static boolean checkPs(int x, int y) {
		if(x >= R || y >= C || x < 0 || y < 0) {
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	
} // end of class
