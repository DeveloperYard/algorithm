import java.io.*;

public class Main {

    static int ans;
    static String from;
    static String to;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        from = br.readLine();
        to = br.readLine();

        bfs(to);

        if(ans > 0)
            System.out.println(1);
        else
            System.out.println(0);
    }

    // 모든 방법을 탐색!
    public static void bfs(String tmp){
        // System.out.println("tmp : " + tmp);
        if(ans > 0)
            return;
        if(tmp.length() == from.length()){
            if(tmp.equals(from)){
                ans++;
            }
            return;
        }

        if(tmp.charAt(0) == 'B'){
            String bMinus = swap(tmp);
            bfs(bMinus);
        }

        if(tmp.charAt(tmp.length()-1) == 'A'){
            String aMinus = minusA(tmp);
            bfs(aMinus);
        }


    }
    // A 빼는 메서드
    public static String minusA(String s){
        String minus = "";

        for(int i = 0; i < s.length()-1; i++)
            minus += s.charAt(i);

        return minus;
    }

    // 문자열 교환 메서드
    public static String swap(String s){
        String reverse = "";

        for(int i = s.length()-1; i >= 1; i--){
            reverse += s.charAt(i);
        }

        return reverse;
    }
}
