// boj15652 - N과 M(4)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

        dup(1, 1);
        System.out.println(sb);
    }

    private static void dup(int n, int depth){
        if(depth > M){
            for(int i = 1; i <= M; i++){
                sb.append(printArr[i] +" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = n; i <= N; i++){
            printArr[depth] = i;
            dup(i,depth+1);
        }
    }
}
