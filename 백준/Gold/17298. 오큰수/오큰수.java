import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] ans;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ans = new int[N];

        Arrays.fill(ans, -1);

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(stack.isEmpty()){
                stack.push(arr[i]);
            } else {
                int curIdx = i-1;
                while(!stack.isEmpty() && stack.peek() < arr[i]){
                    if(ans[curIdx] > -1){
                        curIdx--;
                        continue;
                    }
                    ans[curIdx--] = arr[i];
                    stack.pop();
                }
                stack.push(arr[i]);
            }
        }
        sb = new StringBuilder();
        for(int e : ans)
            sb.append(e + " ");

        System.out.println(sb.toString());
    }

}
