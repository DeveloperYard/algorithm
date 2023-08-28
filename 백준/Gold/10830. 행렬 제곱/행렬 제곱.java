import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static final int MOD = 1000;
    public static long[][] arr;
    public static int N;
    public static long exp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        exp = Long.parseLong(st.nextToken());
        arr = new long[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                arr[i][j] = Long.parseLong(st.nextToken())%MOD;
            }
        }

        long[][] ans = pow(arr, exp);
        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans.length; j++){
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static long[][] pow(long[][] a, long exp){
        if(exp == 1L) return a;

        long[][] ret = pow(a, exp/2);

        ret = mul(ret, ret);
        if(exp % 2 == 1L){
            ret = mul(ret, arr);
        }

        return ret;
    }

    private static long[][] mul(long[][] a1, long[][] a2){
        long[][] ret = new long[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    ret[i][j] += a1[i][k] * a2[k][j];
                    ret[i][j] %= MOD;
                }
            }
        }

        return ret;
    }
}
