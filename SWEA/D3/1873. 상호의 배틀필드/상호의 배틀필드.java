/*
 * 
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)

U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.


 * */

import java.io.*;
import java.util.*;

public class Solution {
	
	static class Location {
		public int x, y;
		
		Location(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static Location lc;
	static int[] dx = {-1, 1, 0, 0}; // Nominate direction, 상하좌우!
	static int[] dy = {0, 0, -1, 1};
	static int direction = -1;
	private static char[] commands;
	private static int N;
	private static int M;
	private static char[][] map;
	
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][];
			
			for(int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			
			
			// 전차 위치 찾기, 방향도 찾기!
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == '^') {
						direction = 0;
						lc = new Location(i, j);
						break;
					} else if(map[i][j] == 'v') {
						lc = new Location(i, j);
						direction = 1;
						break;
					} else if(map[i][j] == '<') {
						lc = new Location(i, j);
						direction = 2;
						break;
					} else if(map[i][j] == '>') {
						lc = new Location(i, j);
						direction = 3;
						break;
					}
				}
			}
			
			int numOfCommands = Integer.parseInt(br.readLine());
			
			// 명령어 집합 입력
			commands = br.readLine().toCharArray();
			
			// 명령 수행!
			battleField();
			StringBuilder msb = new StringBuilder();
			for(int i = 0; i<N; i++) {
				for(int j = 0; j < M; j++) {
					msb.append(map[i][j]);
				}
				msb.append("\n");
			}
			
			sb.append("#"+t+" ").append(msb);
		}
		
		System.out.println(sb);
	} // end of main
	public static void battleField() {
		int idx = 0;
		
		/*
		 * caution 
		 * 방향 전환 시 갈 수 있는지를 확인해야 함! 벽 혹은 맵 밖, 물에는 들어갈 수 없음. 즉, '.'인 평지만 가능!
		 * 사격 시 물은 그냥 지나가고, 맵 밖을 나가거나, 강철 벽에 부딪히면 소멸, 벽돌 벽이 맞았을 때만 '.' 평지로 변환! 
		 * */ 
		
		while(idx < commands.length) {
			if(commands[idx] == 'S') {
				int curX = lc.x;
				int curY = lc.y;
				switch (direction) {
				case 0: // U
				{
					while(curX >= 0) {
						if(map[curX][curY] == '*') {
							map[curX][curY] = '.';
							break;
						} else if(map[curX][curY] == '#') break;
						curX--;
					}
					break;
				}
				case 1: // D
				{
					while(curX < N) {
						if(map[curX][curY] == '*') {
							map[curX][curY] = '.';
							break;
						} else if(map[curX][curY] == '#') break;
						curX++;
					}
					break;
				}
				case 2: // L
				{
					while(curY >= 0) {
						if(map[curX][curY] == '*') {
							map[curX][curY] = '.';
							break;
						} else if(map[curX][curY] == '#') break;
						curY--;
					}
					break;
					
				}
				case 3: // R
				{
					while(curY < M) {
						if(map[curX][curY] == '*') {
							map[curX][curY] = '.';
							break;
						} else if(map[curX][curY] == '#') break;
						curY++;
					}
					break;
				}
				}
			} else if(commands[idx] == 'U') {
				direction = 0;
				if(checkPs(lc.x+dx[direction], lc.y+dy[direction])) {
					map[lc.x][lc.y] = '.';
					lc.x += dx[direction];
					lc.y += dy[direction];
				}
				map[lc.x][lc.y] = '^';
				
			} else if(commands[idx] == 'D') {
				direction = 1;
				if(checkPs(lc.x+dx[direction], lc.y+dy[direction])) {
					map[lc.x][lc.y] = '.';
					lc.x += dx[direction];
					lc.y += dy[direction];
				}
				map[lc.x][lc.y] = 'v';
				
			} else if(commands[idx] == 'L') {
				direction = 2;
				if(checkPs(lc.x+dx[direction], lc.y+dy[direction])) {
					map[lc.x][lc.y] = '.';
					lc.x += dx[direction];
					lc.y += dy[direction];
				}
				map[lc.x][lc.y] = '<';
				
			} else if(commands[idx] == 'R') {
				direction = 3;
				if(checkPs(lc.x+dx[direction], lc.y+dy[direction])) {
					map[lc.x][lc.y] = '.';
					lc.x += dx[direction];
					lc.y += dy[direction];
				}
				map[lc.x][lc.y] = '>';
			}
			idx++;
		}	
		return;
	} // end of battleField method
	
	public static boolean checkPs(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= M) {
			return false;
		}
		// 평지에만 들어갈 수 있음!
		if(map[x][y] == '.') return true;
		else return false;
		
	} // end of checkPs method
} // end of class
