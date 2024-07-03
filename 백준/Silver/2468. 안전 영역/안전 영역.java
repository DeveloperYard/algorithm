import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    //완탐 시마다 비교 진행
    static int max = -1;
    static int maxHeight;
    static int curClose;

    static class Position {
        int x, y;
        Position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        for(int i = 0; i < maxHeight; i++){
            // 하나씩 물의 수위를 올려보며 최대 구역을 찾기!
            visited = new boolean[N][N];
            int curMap = 0;
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    if(!visited[j][k] && map[j][k] > i){
                        jangma(j, k, i);
                        curMap++;
                    }
                }
            }
            max = Math.max(curMap, max);
        }

        System.out.println(max);
    }

    public static void jangma(int x, int y, int height){
        // bfs
        ArrayDeque<Position> q = new ArrayDeque<>();
        q.offer(new Position(x, y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Position p = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(isPossible(nx, ny) && !visited[nx][ny] && map[nx][ny] > height){
                    visited[nx][ny] = true;
                    q.offer(new Position(nx, ny));
                }
            }
        }
    }

    public static boolean isPossible(int x, int y){
        return (x >= 0 && y >= 0 && x < N && y < N);
    }
}
