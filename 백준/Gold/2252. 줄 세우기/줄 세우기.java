import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int E;
	
	static int[] inDegree;
	
	static ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		inDegree = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			a.add(new ArrayList<>());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			a.get(from).add(to);
			inDegree[to]++;
		}
		
		topologySort();
	} // end of main
	
	private static void topologySort() {
		Queue<Integer> q = new ArrayDeque<>();
		int[] result = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			if(inDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		for(int i = 1; i <= N; i++) {
			if(q.isEmpty()) {
				return;
			}
			
			int x = q.poll();
			
			result[i] = x;
			
			for(int j = 0; j < a.get(x).size(); j++) {
				int y = a.get(x).get(j);
				if(--inDegree[y] == 0) {
					q.offer(y);
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			System.out.print(result[i] + " ");
		}
	}
	
} // end of class