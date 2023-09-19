import java.io.*;
import java.util.*;

public class Main {

	static int[] val;
	static int printNum;
	static int[] arr;
	static int size;
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;
	
	public static void main(String args[]) throws IOException {
	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		size = Integer.parseInt(st.nextToken());
		printNum = Integer.parseInt(st.nextToken());
		
		val = new int[size];
		visited = new boolean[size];
		arr = new int[printNum];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < size; i++){
			val[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(val);
		
		
		dupPer(0);
		
		System.out.println(sb.toString());
		
	}
	
	public static void dupPer(int cnt){
	if(cnt == printNum){
		for(int e : arr){
			sb.append(e + " ");
		}
		sb.append("\n");
		return;
	}
	
	for(int i = 0; i < size; i++){
	        if(visited[i]) continue;
	        visited[i] = true;
			arr[cnt] = val[i];
			dupPer(cnt+1);
			visited[i] = false;
		}
	}

}