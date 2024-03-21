import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        calc();
        System.out.println(sum);
    }

    public static void calc(){
        int prev = 0;
        int next = N-1;

        while(prev <= next){
            if(prev == next){
                sum += arr[prev];
                return;
            }
            sum += arr[next]*2;
            prev += 1;
            next -= 1;
        }
    }
}
