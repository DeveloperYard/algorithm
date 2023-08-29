import java.io.*;
import java.util.*;

public class Main {
    static int[][] rgbVal;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        rgbVal = new int[N][3];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j++){
                rgbVal[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(rgbDistance());
    }
    private static int rgbDistance(){
        int min = 10_000_000;
        int dp[] = new int[3];
        dp[0] = rgbVal[0][0];
        dp[1] = rgbVal[0][1];
        dp[2] = rgbVal[0][2];
        for(int i = 1;i < N; i++){
            int tmp1 = dp[0];
            int tmp2 = dp[1];
            int tmp3 = dp[2];
            for(int j = 0; j < 3; j++) {

                if(j == 0){
                    dp[0] = rgbVal[i][0] + Math.min(tmp2, tmp3);
                } else if(j == 1){
                    dp[1] = rgbVal[i][1] + Math.min(tmp1, tmp3);
                } else {
                    dp[2] = rgbVal[i][2] + Math.min(tmp1, tmp2);
                }
            }
        }
        for(int i = 0; i < 3; i++){
            if(min > dp[i]){
                min = dp[i];
            }
        }

        return min;
    }

}
