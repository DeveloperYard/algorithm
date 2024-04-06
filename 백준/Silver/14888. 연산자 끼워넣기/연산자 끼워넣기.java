import java.io.*;
import java.util.*;

public class Main {
    static char[] opers;
    static int N;
    static int[] nums;
    static char[] selectedOper;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        opers = new char[N-1];
        selectedOper = new char[N-1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        int idx = 0;
        for(int i = 0; i < 4; i++){
            int val = Integer.parseInt(st.nextToken());
            if(i == 0){
                for(int j = 0; j < val; j++){
                    opers[idx++] = '+';
                }
            } else if(i == 1){
                for(int j = 0; j < val; j++){
                    opers[idx++] = '-';
                }
            } else if(i == 2){
                for(int j = 0; j < val; j++){
                    opers[idx++] = '*';
                }
            } else {
                for(int j = 0; j < val; j++){
                    opers[idx++] = '/';
                }
            }
        }
        boolean[] selected = new boolean[N-1];
        selectOp(0, selected);

        System.out.println(max);
        System.out.println(min);
    }
    public static void selectOp(int n, boolean[] isSelected){
        if(n == N-1){
            calculate();
            return;
        }

        // How to removing duplicate process?
        for(int i = 0; i < N-1; i++){
            if(isSelected[i])
                continue;
            isSelected[i] = true;
            selectedOper[n] = opers[i];
            selectOp(n+1, isSelected);
            isSelected[i] = false;

        }
    }

    public static void calculate(){
        int val = nums[0];
        for(int i = 0; i < N-1; i++){
            switch (selectedOper[i]){
                case '+':
                    val += nums[i+1];
                    break;
                case '-':
                    val -= nums[i+1];
                    break;
                case '*':
                    val *= nums[i+1];
                    break;
                case '/':
                    val /= nums[i+1];
                default:
                    break;
            }
        }
        max = Math.max(max, val);
        min = Math.min(min, val);
    }
}
