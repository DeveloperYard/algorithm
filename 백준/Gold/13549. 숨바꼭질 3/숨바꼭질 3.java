import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int K;

    static int[] dp = new int[200001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Arrays.fill(dp, -1);

        find();
        
        System.out.println(dp[K]);
    }

    private static void find(){
        Deque<Integer> q = new ArrayDeque<>();
        dp[N] = 0;
        q.offer(N);

        while(!q.isEmpty()){
            int position = q.poll();

            if(position == K) break;

            if(position * 2 <= 200000 && dp[position * 2] == -1){
                dp[position * 2] = dp[position];
                q.addFirst(position * 2);
            }
            if (position - 1 >= 0 && dp[position - 1] == -1){
                dp[position - 1] = dp[position] + 1;
                q.offer(position-1);
            }
            if (position + 1 <= 200000 && dp[position + 1] == -1){
                dp[position + 1] = dp[position] + 1;
                q.offer(position+1);
            }
        }
    }
}
