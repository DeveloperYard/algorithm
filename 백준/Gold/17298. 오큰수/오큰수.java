import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[] ans;
    private static int k;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ans = new int[N];

        Arrays.fill(ans, -1);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
//        int j = 1000000;
//        for(int i = 0; i < 1000000; i++){
//            arr[i] = j--;
//        }
//        for(int i = 0; i < 1000000; i++){
//            arr[i] = i+1;
//        }
        calc();

        for(int e : ans)
            sb.append(e + " ");

        System.out.println(sb.toString());
    }

    private static void calc(){
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < N; i++){
            if(st.isEmpty()){
                st.push(arr[i]);
            } else {
                int curIdx = i-1;
                while(!st.isEmpty() && st.peek() < arr[i]){
                    if(ans[curIdx] > -1){
                        curIdx--;
                        continue;
                    }
                    ans[curIdx--] = arr[i];
                    st.pop();
                }
                st.push(arr[i]);
            }
        }
    }
}
