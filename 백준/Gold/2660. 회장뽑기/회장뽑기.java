import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int INF = 50;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        for(int i = 1; i <= N; i++){
            Arrays.fill(map[i], INF);
        }
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == j)
                    map[i][j] = 0;
            }
        }
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(start == -1 || end == -1)
                break;

            map[start][end] = 1;
            map[end][start] = 1;
        }
        floyd();
        ArrayList<Integer> candidate = new ArrayList<>();
        int curMin = INF+1;
        for(int i = 1; i <= N; i++){
            int min = -1;
            for(int j = 1; j <= N; j++){
                if(min < map[i][j]){
                    min = map[i][j];
                }
            }
            if(curMin > min){
                candidate = new ArrayList<>();
                curMin = min;
                candidate.add(i);
            } else if(curMin == min){
                candidate.add(i);
            }
        }

        System.out.println(curMin + " " + candidate.size());
        for(int e : candidate){
            System.out.print(e + " ");
        }

//        for(int[] e : map){
//            System.out.println(Arrays.toString(e));
//        }

    } // end of main

    private static void floyd(){
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(map[i][k] != INF && map[k][j] != INF){
                        if(map[i][j] > map[i][k] + map[k][j]){
                            map[i][j] = map[i][k] + map[k][j];
                        }
                    }
                }
            }
        }
    }
} // end of class
