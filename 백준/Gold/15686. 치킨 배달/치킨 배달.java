import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static int N, M;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static ArrayList<String> chickens = new ArrayList<>();
    static ArrayList<String> homes = new ArrayList<>();
    static int[] selectedChickens;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int sumMin = Integer.MAX_VALUE;
    static HashSet<String> checked = new HashSet<>();
    static int chCnt;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        selectedChickens = new int[M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    homes.add(i + " " + j);
                } else if(map[i][j] == 2){
                    chickens.add(i + " " + j);
                }
            }
        }
        chCnt = chickens.size();
        // 각 치킨집에서의 집까지의 거리 구하기
        for(int i = 0; i < chCnt; i++){
            arr.add(new ArrayList<>());
            // i번째 치킨집
            st = new StringTokenizer(chickens.get(i));
            int cx = Integer.parseInt(st.nextToken());
            int cy = Integer.parseInt(st.nextToken());
            for(int j = 0; j < homes.size(); j++){
                st = new StringTokenizer(homes.get(j));
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr.get(i).add(Math.abs(cx-x) + Math.abs(cy-y));
            }
        }

        comb(0, 0);

        System.out.println(sumMin);
    }

    // 중복을 허용하지 않고 M개 구해야 함!
    public static void comb(int start, int cnt){
        if(cnt == M){
            // 구한 치킨집 중 최소 거리 구하기!
            minimumChickenDistance();
            return;
        }
        // 치킨집 chCnt 중 M개 구하기!
        for(int i = start; i < chCnt; i++){
            selectedChickens[cnt] = i;
            comb(i+1, cnt+1);
        }
    }

    // 구한 치킨집 중에서 최소 거리를 구하자!
    // 집 수만큼 순회하되, 그 속에서 선택된 M개의 치킨집에서 최소의 치킨 거리를 선택
    public static void minimumChickenDistance(){
        // selectedChickens 를 이용할 것!
        int sum = 0;
        for(int i = 0; i < homes.size(); i++){
            int min = Integer.MAX_VALUE;
            // 선택된 치킨집의 수
            for(int j = 0; j < M; j++){
                min = Math.min(min, arr.get(selectedChickens[j]).get(i));
            }
            sum += min;
            if(sum > sumMin)
                return;
        }

        sumMin = Math.min(sum, sumMin);
    }
}
