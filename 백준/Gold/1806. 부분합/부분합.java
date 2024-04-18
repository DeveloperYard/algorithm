import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N;
    static int target;
    static int minLen = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int curSum = arr[start];
        while (end < N) {
            if (curSum >= target) {
                if ((end - start) + 1 < minLen) {
                    minLen = (end - start) + 1;
                }
                if(start >= end){
                    // end++ 해야 함
                    if(end == N-1)
                        break;
                    curSum += arr[++end];
                } else {
                    curSum -= arr[start++];
                }
            } else {
                if(end == N-1){
                    break;
                }
                curSum += arr[++end];
            }
        }
        if(minLen == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(minLen);
    }
}
