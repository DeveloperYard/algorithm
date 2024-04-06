import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int curDir;
    static int[][] map;
    static int N;
    static int M;
    static int startX, startY;
    static int cleaningCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());

        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        curDir = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean();

        System.out.println(cleaningCnt);
    }

    public static void clean(){
        int x = startX;
        int y = startY;

        while(true){
            if(map[x][y] == 0){
                cleaningCnt++;
                map[x][y] = -1;
            }
            // 현재 칸의 주변 칸 중 청소되지 않은 빈칸이 있는 경우
            if(checkNear(x, y)){
                // 반시계 방향으로 90도 회전한다.
                changeDir();

                // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                int nx = x + dx[curDir];
                int ny = y + dy[curDir];

                if(isPossible(nx, ny) && map[nx][ny] == 0){
                    x = nx;
                    y = ny;
                }
            } else {
                // 현재 칸의 주변 칸 중 청소되지 않은 빈칸이 없는 경우
                // 바라보는 방향 뒤쪽이 벽이라면 종료
                int nx = x + dx[curDir]*(-1);
                int ny = y + dy[curDir]*(-1);
                if(!isPossible(nx, ny))
                    return;
                // 바라보는 방향 유지하여 후진 후 1번으로 돌아감
                x = nx;
                y = ny;
            }
        }
    }

    public static boolean isPossible(int x, int y){
        if (x >= N || y >= M || x < 0 || y < 0 || map[x][y] == 1)
            return false;
        else
            return true;
    }
    public static boolean checkNear(int x, int y){
        int cnt = 0;
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isPossible(nx, ny) && map[nx][ny] == 0){
                cnt++;
            }
        }
        return cnt > 0;
    }
    public static void changeDir(){
        curDir -= 1;
        if(curDir == -1)
            curDir += 4;
    }
}