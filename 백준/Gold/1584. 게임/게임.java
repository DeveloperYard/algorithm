import java.io.*;
import java.util.*;

public class Main {

    static int N; // 위험한 구역의 수
    static int M; // 죽음의 구역의 수
    static int[][] map = new int[501][501];

    static final int INF = 500 * 500 + 1;

    static final String death = "Death";
    static final String danger = "Danger";

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static boolean[][] visited = new boolean[501][501];
    static int minLostHeart = -1;

    static class Sejun {
        int x, y;
        int heart;

        Sejun(int x, int y, int heart){
            this.x = x;
            this.y = y;
            this.heart = heart;
        }

        public String toString(){
            return "x : " + x + " y : " + y + " heart : " + heart;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            createArea(x1, y1, x2, y2, danger);
        }

        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            createArea(x1, y1, x2, y2, death);
        }

        bfs(new Sejun(0, 0, INF));
        // 감소한 체력의 최소값을 출력해야함
        if(minLostHeart == -1){
            System.out.println(minLostHeart);
        } else {
            System.out.println(INF-minLostHeart);
        }
    }

    private static void bfs(Sejun s) {
        Deque<Sejun> q = new ArrayDeque<>();

        q.offer(s);
        visited[s.x][s.y] = true;

        while(!q.isEmpty()) {
            Sejun p = q.poll();
            if(p.x == 500 && p.y == 500) {
                if(minLostHeart < p.heart) // 만약 현재 남은 생명력이 저장된 값보다 많은 경우, 저장!
                    minLostHeart = p.heart;
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(!checkPos(nx, ny)) continue;
                visited[nx][ny] = true;
                if(map[nx][ny] == 0) {
                    q.addFirst(new Sejun(nx, ny, p.heart));
                }
                if(map[nx][ny] == 1) {
                    q.offer(new Sejun(nx, ny, p.heart-1));
                }
            }
        }


    }

    private static boolean checkPos(int x, int y) {
        return (x < 0 || y < 0 || x > 500 || y > 500 || map[x][y] == 2 || visited[x][y]) ? false : true;
    }

    private static void createArea(int x1, int y1, int x2, int y2, String type) {
        if(x1 > x2){
            int tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if(y1 > y2){
            int tmp = y1;
            y1 = y2;
            y2 = tmp;
        }
        if(type.equals("Death")) {
            for(int i = x1; i <= x2; i++) {
                for(int j = y1; j <= y2; j++) {
                    map[i][j] = 2;
                }
            }
        } else {
            for(int i = x1; i <= x2; i++) {
                for(int j = y1; j <= y2; j++) {
                    map[i][j] = Math.max(1, map[i][j]);
                }
            }
        }
    }
}
