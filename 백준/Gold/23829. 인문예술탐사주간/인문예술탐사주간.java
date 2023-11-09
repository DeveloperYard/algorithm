import java.io.*;
import java.util.*;

public class Main {

    static int N, Q; // 나무의 개수와 찍은 사진의 개수
    static long[] tree;
    static int curMinIdx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        tree = new long[N];

        st = new StringTokenizer(br.readLine(), " ");
        long[] sum = new long[N+1];
        for(int i = 0; i < N; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(tree);

        for(int i = 1; i <= N; i++){
            sum[i] = sum[i-1] + tree[i-1];
        }

        for(int i = 0; i < Q; i++){
            long curLoc = Long.parseLong(br.readLine());
            curMinIdx = N;

            biSearch(0, N-1, curLoc);
            long val = 0;
            // right
            val += sum[N] - sum[curMinIdx] - curLoc*(N-curMinIdx);
//            System.out.println("right : " + (sum[N] - sum[curMinIdx] - curLoc*(N-curMinIdx)));
            // left
            val += curLoc*(curMinIdx) - sum[curMinIdx];
//            System.out.println("left : " + (curLoc*(curMinIdx) - sum[curMinIdx]));
            sb.append(val).append("\n");
        }

        System.out.println(sb);
    }

    private static void biSearch(int start, int end, long curLoc){
        if(start > end)
            return;

        int mid = (end - start)/2 + start;

        if(tree[mid] >= curLoc){
            curMinIdx = mid;
            biSearch(start, mid-1, curLoc);
        } else {
            biSearch(mid+1, end, curLoc);
        }
    }
}