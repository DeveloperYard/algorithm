import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int ans;

    static int[] dx = {1, 0, 1}; // 세로, 가로, 수직
    static int[] dy = {0, 1, 1};
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[33][33];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new long[33][33][3];

        long ans = dp();
        System.out.println(ans);
    }

    private static long dp(){
        dp[0][1][0] = 1;
        // 0 가로, 1 세로, 2 대각선

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == 0 && j == 0)
                    continue;
                if(map[i][j] == 1)
                    continue;

                if(map[i][j+1] == 0)
                    dp[i][j+1][0] = dp[i][j][0] + dp[i][j][2];
                if(map[i+1][j] == 0)
                    dp[i+1][j][1] = dp[i][j][2] + dp[i][j][1];
                if(i+1 < N && j+1 < N)
                    if(map[i+1][j] == 0 && map[i][j+1] == 0 && map[i+1][j+1] == 0)
                        dp[i+1][j+1][2] = dp[i][j][0] + dp[i][j][1] + dp[i][j][2];

            }
        }

        return dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2];
    }
}
