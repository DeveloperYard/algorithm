import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] arr;
    static int[] maxVal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        maxVal = new int[N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

            if(arr[i][0] + i > N){
                arr[i][1] = 0;
            }
        }
        maxVal[0] = arr[0][1];
        for(int i = 0; i < N; i++){
            maxVal[i] = arr[i][1];
            for(int j = 0; j < i; j++){
                if(arr[j][0] + j > i){
                    maxVal[i] = Math.max(maxVal[i], arr[i][1]);
                } else {
                    maxVal[i] = Math.max(maxVal[i], arr[i][1] + maxVal[j]);
                }
            }
//            System.out.println(Arrays.toString(maxVal));
        }

        int max = -1;
        for(int i = 0; i < N; i++){
            if(maxVal[i] > max){
                max = maxVal[i];
            }
        }
//        System.out.println(Arrays.toString(maxVal));
        System.out.println(max);

    }
}
