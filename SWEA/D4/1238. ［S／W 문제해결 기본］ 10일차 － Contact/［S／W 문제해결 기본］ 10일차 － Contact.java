import java.io.*;
import java.util.*;

public class Solution {
	
	static HashSet<String> hs;
	static int[][] map;
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t = 1; t <= 10; t++) {
			
			map = new int [101][101];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			hs = new HashSet<>();
			
			int N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int i = 0; i < N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				StringBuilder tsb = new StringBuilder();
				tsb.append(from+" "+to);
				hs.add(tsb.toString());
			}
			
			for(int i = 1; i < 101; i++) {
				for(int j = 1; j < 101; j++) {
					map[i][j] = INF;
				}
			}
			for(String s : hs) {
				st = new StringTokenizer(s, " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				map[from][to] = 1;
			}
			floyd();
			
			int max_idx = -1;
			int max_val = -1;
			for(int i = 1; i < 101; i++) {
				if(map[start][i] != INF && map[start][i] > max_val) {
					max_val = map[start][i];
				}
			}
			
			for(int i = 1; i < 101; i++) {
				if(map[start][i] == max_val) {
					if(i > max_idx) max_idx = i;
				}
			}
			sb.append("#"+t+" "+max_idx).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void floyd() {
		for(int k = 1; k < 101; k++) {
			for(int i = 1; i < 101; i++) {
				for(int j = 1; j < 101; j++) {
					if(map[i][k] != INF && map[k][j] != INF && map[i][j] > map[i][k]+map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
	}
}
