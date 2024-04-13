import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Queue<Shark> storeSharkLocation = new ArrayDeque<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Shark {
        int r, c, s, d, z;
        // r, c -> 좌표
        // s -> 속도
        // d -> 방향
        // z -> 크기
        Shark(int r, int c, int s, int d, int z){
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    '}';
        }
    }

    static int ans;

    static Shark[][] map;
    static int R, C, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R][C];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int r, c, s, d, z;

            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken())-1;
            z = Integer.parseInt(st.nextToken());

            Shark sh = new Shark(r, c, s, d, z);

            map[r][c] = sh;
        }
        // 한 칸씩 이동!
        for(int i = 0; i < C; i++){
//            for(int r = 0; r < R; r++){
//                System.out.println(Arrays.toString(map[r]));
//            }
//            System.out.println("===");
            getShark(i);
            moveShark();
        }

        System.out.println(ans);
    }



    // 상어 잡는 함수
    public static void getShark(int i){
        for(int j = 0; j < R; j++){
            if(map[j][i] != null){
                ans += map[j][i].z;
                map[j][i] = null;
                intoQueue();
                return;

            }
        }

        intoQueue();
    }

    // queue에 이동할 상어 넣는 함수
    public static void intoQueue(){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i][j] != null){
                    storeSharkLocation.offer(map[i][j]);
                }
            }
        }

    }

    // 상어 이동 함수
    public static void moveShark(){
        Queue<Shark> tmpQ = new ArrayDeque<>();

        while (!storeSharkLocation.isEmpty()){
            Shark s = storeSharkLocation.poll();
            for(int i = 0; i < s.s; i++){
                int nx = s.r + dx[s.d];
                int ny = s.c + dy[s.d];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C){
                    // 범위를 넘어간 경우
                    // 방향 전환!
                    if(s.d == 0){
                        s.d = 1;
                    } else if(s.d == 1){
                        s.d = 0;
                    } else if(s.d == 2){
                        s.d = 3;
                    } else if(s.d == 3){
                        s.d = 2;
                    }
                    s.r += dx[s.d];
                    s.c += dy[s.d];
                    continue;
                }
                s.r = nx;
                s.c = ny;
            }
            tmpQ.offer(s);
        }
        storeSharkLocation = tmpQ;
        putShark();
    }

    // 상어 맵에 배치하는 함수
    public static void putShark(){
        map = new Shark[R][C];
        while(!storeSharkLocation.isEmpty()){
            Shark sh = storeSharkLocation.poll();
            if(map[sh.r][sh.c] != null){
//                System.out.println("fight : " + map[sh.r][sh.c]);
//                System.out.println("fight2 : " + sh);
                map[sh.r][sh.c] = (map[sh.r][sh.c].z > sh.z) ? map[sh.r][sh.c] : sh;
            } else {
                map[sh.r][sh.c] = sh;
            }
        }
    }

}
