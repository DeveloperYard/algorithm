// 동전
// dp를 이용한 풀이

import java.io.*;
import java.util.*;

public class Main {

    static int[] dp;
    static int TC; // test case
    static int N; // variable of coin
    static int M; // target
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < TC; t++){
            N = Integer.parseInt(br.readLine());
            coins = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int i = 0; i < N; i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());
            dp = new int[M+1];

            makeCoin();
            
            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);
    } // end of main

    private static void makeCoin(){
        dp[0] = 1; // 동전을 아무것도 사용하지 않는 경우
        for(int i = 0; i < N; i++){
            for(int j = coins[i]; j <= M; j++){
                dp[j] += dp[j-coins[i]];
            }
        }
    } // end of makeCoin

} // end of class

