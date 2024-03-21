import java.io.*;
import java.util.*;


public class Main {
    static int[] arr;
    static int N;
    static int target;

    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        target = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        twoPointer();
        System.out.println(ans);
    }

    public static void twoPointer(){
        int pre = 0;
        int next = N-1;
        while(pre < next){
            int cal = arr[pre] + arr[next];
            if(cal == target){
                ans += 1;
                pre += 1;
                next -= 1;
            } else if(cal > target) {
                next -= 1;
            } else {
                pre += 1;
            }
        }
    }
}