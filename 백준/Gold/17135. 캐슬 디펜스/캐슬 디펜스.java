import java.io.*;
import java.util.*;

public class Main {

    static int maxKillCnt;
    static int killCnt;
    static int[] dx = {0, -1, 0};
    static int[] dy = {-1, 0, 1};
    static int[][] map;
    static int N, M, D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N+1][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] ansMap = new int[N][];

        for(int i = 0; i < N; i++) {
            ansMap[i] = Arrays.copyOf(map[i], M);
        }
        int[] mask = new int[M];
        for(int i = M-1; i > M-4; i--) {
            mask[i] = 1;
        }
        do {
            for(int i = 0; i < M; i++) {
                if(mask[i] == 1) {
                    map[N][i] = 2;
                }
            }
            game();
            if(killCnt > maxKillCnt) {
                maxKillCnt = killCnt;
            }
            killCnt = 0;
            for(int i = 0; i < N; i++) {
                map[i] = Arrays.copyOf(ansMap[i], M);
            }

//			for(int[] e : map) {
//				System.out.println(Arrays.toString(e));
//			}
//			System.out.println();
//
            for(int i = 0; i<M; i++) {
                map[N][i] = 0;
            }
        } while(np(mask));

        System.out.println(maxKillCnt);
    }

    private static void game() {
        // 한 칸씩 내려오면서 죽이면 됨!
        int fir = -1;
        int sec = -1;
        int thr = -1;

        for(int i = 0; i < M; i++) {
            if(map[N][i] == 2 && fir == -1) {
                fir = i;
            } else if(map[N][i] == 2 && sec == -1) {
                sec = i;
            } else if(map[N][i] == 2 && thr == -1) {
                thr = i;
            }
        }

        while(!endWar()) {
            HashSet<String> hs = new HashSet<>();
            bowBFS(N, fir, 0, hs);
            bowBFS(N, sec, 0, hs);
            bowBFS(N, thr, 0, hs);

            for(String s : hs){
                StringTokenizer st = new StringTokenizer(s, " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[x][y] = 0;
                killCnt++;
            }
            moveMap();
        }

    }

    private static boolean endWar() {
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return (cnt == N*M) ? true : false;
    }

    private static void moveMap() {
        for(int i = 0; i < M; i++) {
            map[N-1][i] = 0;
        }
        for(int i = N-1; i >= 1; i--) {
            for(int j = 0; j < M; j++) {
                map[i][j] = map[i-1][j];
            }
        }

        for(int i = 0; i < M; i++) {
            map[0][i] = 0;
        }
    }

    private static void bowBFS(int x, int y, int cur, HashSet<String> hs) {
        // 궁수들이 쏘는 화살의 거리를 계산하고, 적들을 없애는 메서드
        // D = |r2-r1| + |c2-c1|
        // 왼쪽부터 죽인다 했으므로 왼쪽부터 탐색!

        int[][] hmap = new int[N+1][];
        for(int i = 0; i <= N; i++){
            hmap[i] = Arrays.copyOf(map[i], M);
        }


        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        q.offer(new int[]{x, y, cur});

        while(!q.isEmpty()) {
            int[] location = q.poll();
            int lx = location[0];
            int ly = location[1];
            int curCnt = location[2];
            if(curCnt >= D) return;

            for(int i = 0; i < 3; i++) {
                int nx = lx + dx[i];
                int ny = ly + dy[i];
                //System.out.println(nx + " " + ny);
                if(!checkPos(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(hmap[nx][ny] == 1) { // 병사를 만난 경우
                    hmap[nx][ny] = 0;
                    hs.add(nx + " " + ny);
                    return;
                }
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny, curCnt+1});
            }
        }
    }

    private static boolean checkPos(int x, int y) {
        return (x >= N || y >= M || x < 0 || y < 0) ? false : true;
    }

    // next combination
    private static boolean np(int[] p) {
        int N = p.length-1;
        int i = N;

        while(i > 0 && p[i-1] >= p[i]) i--;
        if(i == 0) return false;

        int j = N;

        while(j >= i && p[i-1] >= p[j]) j--;
        swap(p, i-1, j);

        j = N;
        while(i <= j) swap(p, i++, j--);

        return true;
    }

    private static void swap(int[] p, int i, int j) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }
}