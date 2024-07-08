import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // Test case의 개수가 주어지지 않음!
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static char[][] map;
    static int ans;
    static int wholeCnt;
    static boolean[][] visitMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String input = "";
        int tc = 1;

        while((input = br.readLine()) != null && !input.isEmpty()){
            ans = Integer.MAX_VALUE;
            st = new StringTokenizer(input, " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            wholeCnt = 0;

            map = new char[N][M];

            visitMap = new boolean[N][M];

            for(int i = 0; i < N; i++){
                String s = br.readLine();
                for(int j = 0; j < M; j++){
                    map[i][j] = s.charAt(j);
                    if(s.charAt(j) == '.'){
                        wholeCnt++;
                    }
                }
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    for(int k = 0; k < 4; k++){
                        if(map[i][j] == '.' && isPossible(i + dx[k], j + dy[k])){
                            findPath(i, j, k, 1, 1);
                        } else if(map[i][j] == '.' && !isPossible(i + dx[k], j + dy[k])){
                            findPath(i, j, k, 1, 0);
                        }
                    }
                }
            }
            if(ans == Integer.MAX_VALUE)
                ans = -1;
            System.out.println("Case "+tc++ +": " + ans);
        }
        System.out.println(sb);
    }

    public static void findPath(int x, int y, int dir, int cnt, int step){
        if(cnt == wholeCnt)
            ans = Math.min(ans, step);
        visitMap[x][y] = true;
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 갈 수 있는 경우
        if(isPossible(nx, ny)){
            findPath(nx, ny, dir, cnt+1, step);
            // 갈 수 없는 경우
        } else {
            for(int i = 0; i < 4; i++){
                if(dir == i)
                    continue;
                nx = x + dx[i];
                ny = y + dy[i];
                if(isPossible(nx, ny)){
                    findPath(nx, ny, i, cnt+1, step+1);
                }
            }
        }
        visitMap[x][y] = false;
    }

    public static boolean isPossible(int x, int y){
        return !(x < 0 || y < 0 || x >= N || y >= M || visitMap[x][y] || map[x][y] == '*');
    }
}
