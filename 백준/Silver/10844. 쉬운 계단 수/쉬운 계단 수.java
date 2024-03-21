import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N+1][10];

        arr[1] = new int[]{0, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        for(int i = 2; i <= N; i++){
            for(int j = 1; j < 9; j++){
                arr[i][j] = (arr[i-1][j-1] + arr[i-1][j+1])%1000000000;
            }
            arr[i][0] = arr[i-1][1];
            arr[i][9] = arr[i-1][8];
         }

        int sum = 0;
        for(int i = 0; i < 10; i++){
            sum += arr[N][i];
            sum %= 1000000000;
        }

//        for(int i = 0; i < N; i++){
//            System.out.println(Arrays.toString(arr[i]));
//        }

        System.out.println(sum);
    }
}
