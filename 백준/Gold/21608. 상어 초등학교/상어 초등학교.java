import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Position {
        int x, y;
        int favorAdj;
        int blankAdj;

        Position(int x,int y, int favorAdj, int blankAdj){
            this.x = x;
            this.y = y;
            this.favorAdj = favorAdj;
            this.blankAdj = blankAdj;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    ", favorAdj=" + favorAdj +
                    ", blankAdj=" + blankAdj +
                    '}';
        }
    }
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int N;
    static int stuCnt;
    static int[][] room;
    static HashMap<Integer, ArrayList<Integer>> stu = new HashMap<>();
    static ArrayList<Integer> seq = new ArrayList<>();
    static Queue<Position> q;

    static int wholeSatis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        room = new int[N][N];
        stuCnt = N*N;
        for(int i = 0; i < stuCnt; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            seq.add(num);
            stu.put(num, new ArrayList<>());
            for(int j = 0; j < 4; j++){
                stu.get(num).add(Integer.parseInt(st.nextToken()));
            }
        }
        for(int n : seq){
            checkSeat(n);
        }

        lastSatis();
        System.out.println(wholeSatis);
    }
    public static void lastSatis(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                // 해당 자리에서 주변을 검사
                int[] c = checkAdjacentFavoriteFriend(room[i][j], i, j);
                switch (c[0]){
                    case 0:
                        break;
                    case 1:
                        wholeSatis += 1;
                        break;
                    case 2:
                        wholeSatis += 10;
                        break;
                    case 3:
                        wholeSatis += 100;
                        break;
                    case 4:
                        wholeSatis += 1000;

                }
            }
        }
    }
    public static void qInit() {
        q = new PriorityQueue<>(new Comparator<Position>() {
            @Override
            public int compare(Position o1, Position o2) {
                // 먼저, favorAdj를 기준으로 비교합니다.
                if (o1.favorAdj != o2.favorAdj) {
                    return Integer.compare(o2.favorAdj, o1.favorAdj); // 내림차순
                }

                // favorAdj가 같을 경우, blankAdj를 기준으로 비교합니다.
                if (o1.blankAdj != o2.blankAdj) {
                    return Integer.compare(o2.blankAdj, o1.blankAdj); // 내림차순
                }

                // favorAdj와 blankAdj가 같을 경우, 좌표를 기준으로 비교합니다.
                if (o1.x != o2.x) {
                    return Integer.compare(o1.x, o2.x); // 오름차순
                }

                // x 좌표가 같을 경우, y 좌표를 기준으로 비교합니다.
                return Integer.compare(o1.y, o2.y); // 오름차순
            }
        });
    }

    // 자리를 찾는 함수, 좋아하는 학생 수와 빈칸의 수를 모두 집어 넣음
    public static void checkSeat(int student){
        // queue 초기화
        qInit();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(room[i][j] > 0)
                    continue;
                int[] calc = checkAdjacentFavoriteFriend(student, i, j);
                // 들어갈 수 있는 위치를 모두 우선순위 큐에 넣음
                q.offer(new Position(i, j, calc[0], calc[1]));
            }
        }
        Position p = q.poll();
        room[p.x][p.y] = student;
    }


    // 만족도 계산 함수
    public static int[] checkAdjacentFavoriteFriend(int me, int x, int y){
        int cnt = 0;
        int blk = 0;
        // x, y -> 검사할 곳의 좌표
        // me -> 내가 좋아하는 학생이 있는 경우를 구하기 위해!
        // 주변 네 방향 검사
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 검사할 수 있는 경우에만 진행
            if(isPossible(nx, ny)){
                // 만약 비어있는 칸이라면 빈칸의 개수 ++
                if(room[nx][ny] == 0){
                    blk++;
                    continue;
                }
                // 비어있지 않을 때 내가 좋아하는 학생인지
                for(int j = 0; j < 4; j++){
                    if(stu.get(me).get(j) == room[nx][ny]){
                        // 좋아하는 학생이 있는지 확인!
                        cnt++;
                        break;
                    }
                }
            }
        }

        return new int[]{cnt, blk};
    }

    public static boolean isPossible(int x, int y){
        return (x >= 0 && y >= 0 && x < N && y < N);
    }
}
