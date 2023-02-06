import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  
  public void solution() throws Exception {
    ArrayList<Integer> myItem = new ArrayList<Integer>();
    myItem.add(1);
    myItem.add(1);
    myItem.add(2);
    myItem.add(2);
    myItem.add(2);
    myItem.add(8);

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Integer> answerList = new ArrayList<Integer>();
    String line = br.readLine();
    StringTokenizer st = new StringTokenizer(line, " ");
    
    int idx = 0;
    String answerString = "";
    while (idx < myItem.size()){
      answerList.add(myItem.get(idx) - Integer.valueOf(st.nextToken()));
      idx+=1;
    }
    for (Integer i : answerList){
      answerString += i.toString() + " ";
    }
    System.out.println(answerString);
  }
  public static void main(String args[]) throws Exception{
    new Main().solution();
  }
}