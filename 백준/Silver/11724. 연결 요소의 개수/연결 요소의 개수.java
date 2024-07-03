import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 첫번째 줄에 정점의 개수와 간선의 개수
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // create vertex
        map = new int[N][N];
        visited = new boolean[N];

        // connect edges
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            map[start-1][end-1] = 1;
            map[end-1][start-1] = 1;
        }

        for(int i = 0; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                ans++;
                dfs(i);
            }
        }

        System.out.println(ans);
    }

    public static void dfs(int s){
        for(int i = 0; i < N; i++){
            if(map[s][i] == 1 && !visited[i]){
                visited[i] = true;
                dfs(i);
            }
        }
    }
}
