import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	
	static ArrayList<HashSet<Integer>> setList = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			setList.add(new HashSet<>());
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph.get(start-1).add(end-1);
		}
		
		for(int i = 0; i < N; i++) {
			bfs1(i);
			bfs2(i);
		}
		
		for(int i = 0; i < N; i++) {
			if(setList.get(i).size() == N-1) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	public static void bfs1(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		q.add(start);
		
		boolean[] visited = new boolean[N];
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int x : graph.get(cur)) {
				if(visited[x])
					continue;
				visited[x] = true;
				setList.get(x).add(start);
//				System.out.println("start : " + start + " x : " + x);
				q.offer(x);
			}
		}
	}
	
	public static void bfs2(int start) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		q.add(start);
		
		boolean[] visited = new boolean[N];
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int x : graph.get(cur)) {
				if(visited[x])
					continue;
				visited[x] = true;
				setList.get(start).add(x);
//				System.out.println("start : " + start + " x : " + x);
				q.offer(x);
			}
		}
	}
}
