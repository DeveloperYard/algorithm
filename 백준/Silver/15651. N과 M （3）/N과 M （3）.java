// boj15651 - N과 M(3)

// 내 생각에는 재귀로 한 번 접근을 해 봐야 할듯?

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[] printArr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        printArr = new int[M+1];

//        long start = System.currentTimeMillis();
        dup(1);
//        long end = System.currentTimeMillis();
        System.out.println(sb);
//        System.out.println("run time : " + (end-start)+"ms");
    }

    private static void dup(int depth){
        // 조금 더 가지치기할 수 있는 방법이 없을까?
        // 유망하지 않은 곳은 가지 않는게 백트래킹인데..
        if(depth > M){
            for(int i = 1; i <= M; i++){
                sb.append(printArr[i] +" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++){
            printArr[depth] = i;
            dup(depth+1);
        }
    }
}
