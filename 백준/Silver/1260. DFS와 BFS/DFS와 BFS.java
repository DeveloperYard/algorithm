import java.io.*;
import java.util.*;

/*
 * boj 1260 DFS와 BFS
 * 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 
 * 간선의 개수 M(1 ≤ M ≤ 10,000), 
 * 탐색을 시작할 정점의 번호 V가 주어진다. 
 * 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 
 * 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 
 * 입력으로 주어지는 간선은 양방향이다.
 * */

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N, M, V;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map[from][to] = 1;
			map[to][from] = 1;
		}
		boolean[] visited = new boolean[N+1];
		dfs(V, visited);
		sb.append("\n");
		bfs();
		System.out.println(sb.toString());
	}
	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		q.offer(V);
		visited[V] = true;
		
		while(!q.isEmpty()) {
			int vx = q.poll();
			sb.append(vx+" ");
			for(int i = 1; i <= N; i++) {
				if(!visited[i] && map[vx][i] == 1) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		
	}
	
	private static void dfs(int start, boolean[] visited) {
		Stack<Integer> s = new Stack<>();
		s.push(start);
		visited[start] = true;
		sb.append(start+" ");
		
		while(!s.isEmpty()) {
			int vx = s.peek();
			for(int i = 1; i <= N; i++) {
				if(!visited[i] && map[vx][i] == 1) {
					s.push(i);
					dfs(i, visited);
					s.pop();
				}
			}
			s.pop();
		}
	}
}
