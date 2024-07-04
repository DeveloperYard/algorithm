import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N; // counts of people
    static int M; // counts of relation
    static int rel1;
    static int rel2;


    // arrays for calculating chon number
    static int[][] map;
    static boolean[] visited;
    static int ans = -1;
    static class Target {
        int x;
        int curRelCnt;

        Target(int x, int curRelCnt){
            this.x = x;
            this.curRelCnt = curRelCnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine(), " ");

        rel1 = Integer.parseInt(st.nextToken())-1;
        rel2 = Integer.parseInt(st.nextToken())-1;

        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;

            map[x][y] = 1;
            map[y][x] = 1;
        }

        findRelation(rel1, rel2);

        System.out.println(ans);
    }
    // find by Breadth First Search
    public static void findRelation(int from, int to){
        ArrayDeque<Target> q = new ArrayDeque<>();

        q.offer(new Target(from , 0));
        visited[from] = true;
        while(!q.isEmpty()){
            Target cur = q.poll();

            for(int i = 0; i < N; i++){
                if(visited[i] || map[cur.x][i] == 0){
                    continue;
                }
                if(i == to){
                    ans = cur.curRelCnt + 1;
                    return;
                }
                visited[i] = true;
                q.offer(new Target(i, cur.curRelCnt+1));
            }
        }
    }
}
