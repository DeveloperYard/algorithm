import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static int[][] team;
    static int N;
    static int min = Integer.MAX_VALUE;
    static int[] start;
    static int[] link;

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        team = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                team[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //  N/2번씩 선택해야 함!

        start = new int[N/2];
        link = new int[N/2];
        visited = new boolean[N];
        selectTeam(0, 0);
        System.out.println(min);
    }

    public static void selectTeam(int c, int n){
        if(n == N/2){
            calculate();
            return;
        }

        for(int i = c; i < N; i++){
            if(visited[i])
                continue;
            visited[i] = true;
            start[n] = i;
            selectTeam(i+1, n+1);
            visited[i] = false;
        }
    }

    public static void calculate(){
        int startComp = 0;
        int linkComp = 0;

        for(int i = 0; i < N; i++){
            if(!visited[i])
                continue;
            for(int j = i+1; j < N; j++){
                if(!visited[j])
                    continue;
                startComp += team[i][j];
                startComp += team[j][i];
            }
        }
        for(int i = 0; i < N; i++){
            if(visited[i])
                continue;
            for(int j = i+1; j < N; j++){
                if(visited[j])
                    continue;
                linkComp += team[i][j];
                linkComp += team[j][i];
            }
        }
        min = Math.min(min, Math.abs(startComp - linkComp));
    }
}
