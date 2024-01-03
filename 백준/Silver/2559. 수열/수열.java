import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int Days;

    static int[] arr;
    static int max = Integer.MIN_VALUE;
    static int curSum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        Days = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int fir = 0;
        int last = Days-1;

        for(int i = 0; i <= last; i++){
            curSum += arr[i];
        }
//        System.out.println("first max : " + max );
        if(curSum > max){
            max = curSum;
        }
        while(last < N-1){

            int cur = curSum - arr[fir++] + arr[++last];
            if(cur > max){
                max = cur;
            }
            curSum = cur;
//            System.out.println("curSum : " + curSum);
        }

        System.out.println(max);
    }
}
