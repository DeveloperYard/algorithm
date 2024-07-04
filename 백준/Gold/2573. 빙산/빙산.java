import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][] processingMap;
    static int ans;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int curGroupArcsCnt = 1;
    static boolean[][] visited;

    static class Arcs {
        int x, y;

        Arcs(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // processingMap 은 턴이 지날 때마다 새로 생성해서 map에 붙이기!
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int turn = 0;
        loop : do {
            turn++;
            int curArcs = 0;
            visited = new boolean[N][M];
            processingMap = new int[N][M];
            // 주변 바다 수 만큼 녹이기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0) {
                        int processed = map[i][j] - findZero(i, j);
                        if (processed <= 0) {
                            processed = 0;
                        } else {
                            curArcs++;
                            processingMap[i][j] = processed;
                        }
                    }
                }
            }
            // 복사된 맵에 저장, 현재 빙산 개수 저장, 현재 빙산 개수가 0이라면 종료(다 같이 녹은 경우)
            if (curArcs == 0)
                break;
            for (int i = 0; i < N; i++) {
                map[i] = Arrays.copyOf(processingMap[i], M);
            }
            // BFS로 무리를 찾는데, 만약 빙산 개수 > 무리 속 빙산 개수일 시 ans 에 i를 대입 후 종료
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        curGroupArcsCnt = 1;
                        visited[i][j] = true;
                        findGroups(i, j);
                        if (curArcs > curGroupArcsCnt) {
                            ans = turn;
                            break loop;
                        }
                    }
                }
            }
        } while (true);

        System.out.println(ans);
    }

    public static void findGroups(int x, int y) {
        ArrayDeque<Arcs> q = new ArrayDeque<>();
        q.offer(new Arcs(x, y));

        while (!q.isEmpty()) {
            Arcs cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!isPossible(nx, ny) || visited[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }
                visited[nx][ny] = true;
                curGroupArcsCnt++;
                q.offer(new Arcs(nx, ny));
            }
        }
    }

    public static int findZero(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isPossible(nx, ny)) {
                if (map[nx][ny] == 0)
                    cnt++;
            }
        }

        return cnt;
    }

    public static boolean isPossible(int x, int y) {
        return (x >= 0 && y >= 0 && x < N && y < M);
    }
}
