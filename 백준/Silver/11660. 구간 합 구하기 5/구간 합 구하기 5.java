import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[][] arr;

    static int[][] sumArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        sumArr = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 여기에 합산 배열을 만드는 로직이 존재해야 함!
        sumArr[0][0] = arr[0][0];
        for(int i = 1; i < N; i++){
            sumArr[0][i] = arr[0][i]+sumArr[0][i-1];
            sumArr[i][0] = arr[i][0]+sumArr[i-1][0];
        }


        for(int i = 1; i < N; i++){
            for(int j = 1; j < N; j++){
                sumArr[i][j] = arr[i][j] + sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1];
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;
            int x2 = Integer.parseInt(st.nextToken())-1;
            int y2 = Integer.parseInt(st.nextToken())-1;

            int val = sumArr[x2][y2];
            if(x1 > 0){
                val -= sumArr[x1-1][y2];
            }
            if(y1 > 0){
                val -= sumArr[x2][y1-1];
            }
            if(x1 > 0 && y1 > 0){
                val += sumArr[x1-1][y1-1];
            }

            sb.append(val).append("\n");
        }
        System.out.println(sb);
    }
}
