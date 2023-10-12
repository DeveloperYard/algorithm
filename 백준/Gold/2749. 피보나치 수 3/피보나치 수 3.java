import java.io.*;
import java.util.*;
 
public class Main{
 
    static final long MOD = 1_000_000;
 
    static long[][] origin = new long[][]{{1, 1}, {1, 0}};
 
 
    public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
 
        long exp = Long.parseLong(sc.nextLine());
 
        long[][] arr = new long[][]{{1 , 1}, {1, 0}};
 
        System.out.println(pow(arr, exp-1)[0][0]);
    }
 
    private static long[][]pow(long[][] a, long exp){
        if (exp==1 || exp==0){
            return a;
        }
 
        long[][] ret = pow(a, exp/2);
 
        ret = mul(ret, ret);
 
        if (exp%2 == 1){
            ret = mul(ret, origin);
        }
 
        return ret;
    }
 
    private static long[][] mul(long [][] a1, long[][] a2){
        long[][] ret = new long[2][2];
 
        ret[0][0] = (a1[0][0] * a2[0][0] + a1[0][1] * a2[1][0])%MOD;
 
        ret[0][1] = (a1[0][0] * a2[0][1] + a1[0][1] * a2[1][1])%MOD;
 
        ret[1][0] = (a1[1][0] * a2[0][0] + a1[1][1] * a2[1][0])%MOD;
 
        ret[1][1] = (a1[1][0] * a2[0][1] + a1[1][1] * a2[1][1])%MOD;
 
        return ret;
    }
}