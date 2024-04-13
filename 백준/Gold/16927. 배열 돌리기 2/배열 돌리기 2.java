import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 회전시킬 큐
    static Queue<Integer> q = new ArrayDeque<>();
    static int N, M, R;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int min = Math.min(N/2, M/2);

        for(int i = 0; i < min; i++){
            // N/2번째 행까지만 돌리면 됨!
            rotate(i);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // Queue에 넣고 돌리는 함수
    public static void rotate(int n){
        // n번째 껍질 돌리기
        for(int i = n; i < M-n; i++){
            q.offer(arr[n][i]);
        }

        for(int i = n+1; i < N-n; i++){
            q.offer(arr[i][M-n-1]);
        }

        for(int i = M-n-2; i >= n; i--){
            q.offer(arr[N-n-1][i]);
        }

        for(int i = N-n-2; i >= n+1; i--){
            q.offer(arr[i][n]);
        }

        int rotCnt = (R > q.size()) ? R % q.size() : R;

        for(int i = 0; i < rotCnt; i++){
            q.offer(q.poll());
        }
        intoArr(n);
    }
    // 배열에 다시 넣는 함수
    public static void intoArr(int n){
        // n번째 껍질을 다시 배열에 넣기
        for(int i = n; i < M-n; i++){
            arr[n][i] = q.poll();
        }

        for(int i = n+1; i < N-n; i++){
            arr[i][M-n-1] = q.poll();
        }

        for(int i = M-n-2; i >= n; i--){
            arr[N-n-1][i] = q.poll();
        }

        for(int i = N-n-2; i >= n+1; i--){
            arr[i][n] = q.poll();
        }
    }
}
