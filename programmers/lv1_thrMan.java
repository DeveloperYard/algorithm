import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main{
  public static void main(String args[]) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), ", ");
    int[] arr;
    ArrayList<Integer> myArr = new ArrayList<Integer>();
    while (st.hasMoreTokens()){
      int s = Integer.parseInt(st.nextToken());
      myArr.add(s);
    }

    arr = new int[myArr.size()];
    int idx = 0;
    for (Integer i : myArr){
      arr[idx] = i;
      idx+=1;
    }

    Solution ans = new Solution();
    System.out.println(ans.solution((arr)));
  }
}

class Solution {
    public int solution(int[] number) {
        int answer = 0;

        for(int i = 0; i < number.length; i++){
          for (int j = i+1; j < number.length; j++){
            for(int k = j+1; k < number.length; k++){
              if (number[i]+number[j]+number[k] == 0){
                answer+=1;
              }
            }
          }
        }

        return answer;
    }
}