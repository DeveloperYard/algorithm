import java.io.*;
import java.util.*;

public class Main {

    static int stuCnt;
    static int jewelCnt;
    static int[] jewels;
    static int ans = -1;
    static int right = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        stuCnt = Integer.parseInt(st.nextToken());
        jewelCnt = Integer.parseInt(st.nextToken());

        jewels = new int[jewelCnt];
        for(int i = 0; i < jewelCnt; i++){
            jewels[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, jewels[i]);
        }
        devideJewels(1, right);

        System.out.println(ans);
    }

    private static void devideJewels(int start, int end){
        while(start <= end){
            int mid = start + (end - start)/2;

            int sum = 0;

            for(int i = 0; i < jewelCnt; i++){
                if(jewels[i] % mid == 0)
                    sum += jewels[i]/mid;
                else
                    sum += jewels[i]/mid + 1;
            }

            if(sum > stuCnt)
                start = mid+1;
            else{
                end = mid-1;
                ans = mid;
            }
        }
    }
}