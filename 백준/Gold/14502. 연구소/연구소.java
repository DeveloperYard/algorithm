import java.io.*;
import java.util.*;

public class Main {

    static int[][] map; //
    static int[][] copiedMap; // 완전 탐색 시 원래 맵을 복사해 놓을 곳
    static int curMaxSafeZoneCnt; // 현재 최대 안전 영역의 수
    static Queue<int[]> virus = new ArrayDeque<>(); // 바이러스가 있는 곳
    static Queue<int[]> copiedVirus; // 완전 탐색 시 바이러스가 있는 곳을 복사해 놓을 곳
    static int N, M; // 연구소의 크기

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int runCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    // 현재 칸에 바이러스가 존재하면 넣음
                    virus.offer(new int[]{i, j});
                }
            }
        }

        putWalls();
        System.out.println(curMaxSafeZoneCnt);
    }

    public static void putWalls(){
        copiedMap = new int[N][M];

        for(int row1 = 0; row1 < N; row1++){
            for(int col1 = 0; col1 < M; col1++){
                if(map[row1][col1] > 0){
                    continue;
                }
                map[row1][col1] = 1;
                for(int row2 = 0; row2 < N; row2++){
                    for(int col2 = 0; col2 < M; col2++){
                        if(map[row2][col2] > 0){
                            continue;
                        }
                        if(row1 == row2 && col1 == col2)
                            continue;
                        map[row2][col2] = 1;
                        for(int row3 = 0; row3 < N; row3++){
                            for(int col3 = 0; col3 < M; col3++){
                                if(map[row3][col3] > 0){
                                    continue;
                                }
                                if((row1 == row3 && col1 == col3) || (row2 == row3 && col2 == col3))
                                    continue;
                                map[row3][col3] = 1;
                                // 세 번째 벽까지 놨으면 이제 함수 수행하기!
                                setMap();
                                calculateSafeZoneCount();
                                map[row3][col3] = 0;
                            }
                        }
                        map[row2][col2] = 0;
                    }
                }
                map[row1][col1] = 0;
            }
        }
    }
    public static void calculateSafeZoneCount(){
        runCnt++;
        boolean[][] visited = new boolean[N][M];
        copiedVirus = new ArrayDeque<>(virus);
        int[] first = copiedVirus.peek();
        if(first != null)
            visited[first[0]][first[1]] = true;
        while(!copiedVirus.isEmpty()){
            int[] vir = copiedVirus.poll();
            int x = vir[0];
            int y = vir[1];
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 바이러스가 침투할 수 있는 경우
                if(isPossible(nx, ny) && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    copiedMap[nx][ny] = 2;
                    copiedVirus.offer(new int[]{nx, ny});
                }
            }
        }

        countSafeZone();
    }

    public static boolean isPossible(int x, int y){
        return x >= 0 && y >= 0 && x < N && y < M && copiedMap[x][y] == 0;
    }

    public static void countSafeZone(){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                if(copiedMap[i][j] == 0)
                    cnt++;
            }
        }
        curMaxSafeZoneCnt = Math.max(curMaxSafeZoneCnt, cnt);
    }

    public static void setMap() {
        for (int i = 0; i < N; i++) {
            if (M >= 0) System.arraycopy(map[i], 0, copiedMap[i], 0, M);
        }
    }
}
