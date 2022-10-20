import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String args[]) throws Exception{
    int[] arr = new int[3];
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), ", ");

    int idx = 0;
    while(st.hasMoreTokens()){
      int n = Integer.parseInt(st.nextToken());
      arr[idx] = n;
      idx+=1;
    }
    
    Solution ans = new Solution();
    System.out.println(ans.solution(arr[0], arr[1], arr[2]));
    
  }
}

class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        int rest = 0;
        int take = 0;

        while (n >= a){
          take = (int)(n/a) * b;
          rest = n%a;
          n = rest+take;
          answer+=take;
        }

        return answer;
    }
}