import java.io.*;
import java.util.*;

public class Main {

    static int N, d, k, c;
    static int[] sushi;
    static HashMap<Integer, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sushi = new int[N+k];

        for(int i = 0; i < N; i++){
            sushi[i] = Integer.parseInt(br.readLine());
        }
        for(int i = N; i < N+k; i++){
            sushi[i] = sushi[i%N];
        }

        int[] arr = new int[d+1];

        for(int i = 0; i < k; i++){
            arr[sushi[i]]++;
        }

        arr[c]++;
        int max = -1;
//        long cnt = Arrays.stream(arr)
//                .filter(i -> i > 0)
//                .count();
        int cnt = 0;
        for(int i = 0; i <= d; i++){
            if(arr[i] > 0) cnt++;
        }

        for(int i = 1; i < N; i++){
            if(max < cnt) max = cnt;
            arr[sushi[i-1]]--;
            if(arr[sushi[i-1]] == 0) cnt--;
            arr[sushi[i+k-1]]++;
            if(arr[sushi[i+k-1]] == 1) cnt++;
        }
        if(max < cnt) max = cnt;

        System.out.println(max);
    }
}
