import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[3][N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++){
            arr[0][i] = Integer.parseInt(st.nextToken());
        }
        arr[1][0] = 1;
        arr[2][N-1] = 1;
        minProcess();
        maxProcess();
        for(int i = 0; i < N; i++){
            ans = Math.max(ans, arr[1][i] + arr[2][i] - 1);
        }

//        System.out.println("arr : " + Arrays.toString(arr[0]));
//        System.out.println("minArr : " + Arrays.toString(arr[1]));
//        System.out.println("maxArr : " + Arrays.toString(arr[2]));
//        System.out.println("ans : " + ans);
        System.out.println(ans);
    }

    public static void minProcess(){
        // 작은 수에서 큰 수로 커지는 과정, 왼 -> 오
        for(int i = 1; i < N; i++){
            int max = 1;
            for(int j = i-1; j >= 0; j--){
                if(arr[0][j] < arr[0][i]){
                    if(max < arr[1][j]+1){
                        max = arr[1][j]+1;
                    }
                }
            }
            arr[1][i] = max;
        }
    }

    public static void maxProcess(){
        for(int i = N-1; i >= 0; i--){
            int max = 1;
            for(int j = i+1; j < N; j++){
                if(arr[0][j] < arr[0][i]){
                    if(max < arr[2][j]+1){
                        max = arr[2][j]+1;
                    }
                }
            }
            arr[2][i] = max;
        }
    }
}
