import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[][] map;
    static int no;
    static int yes;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Position {
        int x, y;
        Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j] = s.charAt(j);
            }
        }

        visited = new boolean[N][N];
        // 적록색약이 아닌 경우
        for(int i = 0; i < N;i ++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    bfs(i, j, map[i][j]);
                    no++;
                }
            }
        }

        // 적록색약인 경우 -> G를 R로 변환 후 처리함
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 'G')
                    map[i][j] = 'R';
            }
        }
        for(int i = 0; i < N;i ++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    bfs(i, j, map[i][j]);
                    yes++;
                }
            }
        }

        System.out.println(no + " " + yes);
    }

    public static void bfs(int x, int y, char color){
        ArrayDeque<Position> q = new ArrayDeque<>();
        visited[x][y] = true;
        q.offer(new Position(x, y));
        while(!q.isEmpty()){
            Position p = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(isPossible(nx, ny) && !visited[nx][ny] && map[nx][ny] == color){
                    q.offer(new Position(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public static boolean isPossible(int x, int y){
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
