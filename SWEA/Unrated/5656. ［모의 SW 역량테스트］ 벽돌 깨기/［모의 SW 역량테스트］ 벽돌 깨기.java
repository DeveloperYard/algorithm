import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, W, H, min;
	
	static class Point {
		int r, c, cnt; // 벽돌의 위치, 크기
		
		Point(int r, int c, int cnt){
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 구슬 던지는 횟수
			W = Integer.parseInt(st.nextToken()); // 가로
			H = Integer.parseInt(st.nextToken()); // 세로
			int[][] map = new int[H][W];
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // input 2 dimen array map
			
			min = Integer.MAX_VALUE;
			drop(0, map);
			System.out.println("#"+tc+" "+min);
		}
	}
	
	// 구슬 던지기 : 중복 순열
	private static boolean drop(int cnt, int[][] map) { 
		// 구슬 떨어뜨리기, cnt : 직전까지 떨어뜨린 구슬 수
		// map : 직전 상태까지의 map
		// return : 모든 벽돌이 제거되었는지 여부
		
		// 구슬을 던지기 전에 현 상태로 남은 벽돌 수 체크
		int result = getRemain(map);
		
		if(result == 0) {
			min = 0;
			return true;
		}
		
		if(cnt == N) {
			if(min > result)
				min = result;
			return false;
		}
		// 남은 벽돌 수가 0이면 모든 벽돌이 제거된 가장 최적의 상태이므로 최소값 0으로 갱신 후 true를 리턴
		
		// base condition : 모든 구슬을 다 던졌을 경우 남은 벽돌 수로 최소값 갱신 후 return false
		// false를 리턴하는 이유는 모든 벽돌들이 전부 부숴진 것은 아니기 때문이다.
		
		int[][] newMap = new int[H][W];
		for(int c = 0; c < W; c++) {
			// 해당 열에 떨어뜨릴 경우 제거되는 맨 윗 벽돌 찾기
			int r = 0;
			while(r < H && map[r][c] == 0) 
				r++;
			// 벽돌이 존재하지 않는다면(해당 열은 모두 빈칸) 다음 열로 건너 뛰기
			if(r == H)
				continue;
			
			// 벽돌이 존재한다면
				copy(map, newMap);
				// 함께 제거될 인접 벽돌 연쇄 찾기
				boom(newMap, r, c);
				// 제거 처리(벽돌 내리기)
				// 디버깅 출력
				down(newMap);
				// 디버깅 출력
				// 다음 구슬 던지러 가기 : 
				// recursive call ==> 재귀호출의 결과가 true이면 가장 최적해의 상황이므로 true를 리턴
				if(drop(cnt+1, newMap))
					return true;
		}
		
		return false;
	}

	
	// 인접한 제거 벽돌 찾기 : flood fill (4방 BFS)
	
	private static void boom(int[][] map, int r, int c) {
		Queue<Point> queue = new ArrayDeque<>();
		
		if(map[r][c] > 1)
			queue.offer(new Point(r, c, map[r][c]));
		
		map[r][c] = 0; // visited
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = cur.r;
				int ny = cur.c;
				
				for(int i = 1; i < cur.cnt; i++) {
					nx += dx[d];
					ny += dy[d];
					
					if(nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] > 0) {
						if(map[nx][ny] > 1)
							queue.offer(new Point(nx, ny, map[nx][ny]));
						map[nx][ny] = 0; // visited
					}
				}
			}
		}
	} // end of boom

	// 벽돌 내리기1 : 빈 자리 위쪽 벽돌 찾아 내리기
	// 벽돌 내리기2 : 매 열마다 맨 윗행부터 벽돌칸 모두 스택에 넣고 빈칸 만들기 
	private static void down(int[][] map) {
		// 매 열 기준으로 내리기
		for(int c = 0; c < W; c++) {
			int r = H-1, nr = -1;
			while(r > 0) {
				if(map[r][c] == 0) { // 빈칸을 만남, 내릴 벽돌을 찾기!
					nr = r-1; // 바로 윗 행부터 동작
					while(nr > 0 && map[nr][c] == 0) 
						--nr;
					
					map[r][c] = map[nr][c];
					map[nr][c] = 0; // 빈칸 처리
				}
				if(nr == 0)
					break;
				
				--r;
			}
		}
	}

	// 배열 복사하기
	private static void copy(int[][] map, int[][] newMap){
		for(int r = 0; r < H; r++) {
			newMap[r] = Arrays.copyOf(map[r], W);
		}
	} // end of copy
	
	
	// 남은 벽돌 개수 구하기 : 매번 구슬 던지기 전에 사용할 목적
	private static int getRemain(int[][] map) {
		int cnt = 0;
		
		for(int i = 0; i<H; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] > 0)
					cnt++;
			}
		}
		
		return cnt;
	} // end of getRemain
	
	// 디버깅용 : 상태 출력
}
