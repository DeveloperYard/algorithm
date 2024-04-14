import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, Target;
    static int[] arr;
    static int curCnt;
    static int[] selected;

    static boolean[] visited;

    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Target = Integer.parseInt(st.nextToken());

        arr = new int[N];
        visited = new boolean[N];
        selected = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++){
            curCnt = i;
            combi(0, 0);
        }

        System.out.println(ans);
    }

    public static void combi(int c, int cnt){
        if(cnt == curCnt){
            int sum = 0;
            for(int i = 0; i < curCnt; i++){
                sum += selected[i];
            }
            if(sum == Target){
                ans++;
            }
            return;
        }

        for(int i = c; i < N; i++){
            if(visited[i])
                continue;
            selected[cnt] = arr[i];
            visited[i] = true;
            combi(i+1, cnt+1);
            visited[i] = false;
        }
    }
}
