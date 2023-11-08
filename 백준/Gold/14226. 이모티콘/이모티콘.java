import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int s;
    static class Emoji {
        int len;
        int time;
        int clipBoard;

        Emoji(int len, int clipBoard, int time){
            this.len = len;
            this.clipBoard = clipBoard;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = Integer.parseInt(br.readLine());
        visited = new boolean[1001][1001];
        Queue<Emoji> q = new ArrayDeque<>();

        q.offer(new Emoji(1, 0,0));
        int minTime = 0;

        while(!q.isEmpty()){
            Emoji cur = q.poll();

            int len = cur.len;
            int time = cur.time;
            int clp = cur.clipBoard;

            if(len-1 == s || len == s || len+clp == s){
                minTime = time+1;
                break;
            }

            // 복사하는 경우
            if(clp+len < s && !visited[len][len]){
                visited[len][len] = true;
                q.offer(new Emoji(len, len, time+1));
            }
            // 하나 삭제하는 경우
            if(len > 0 && !visited[len-1][clp]){
                visited[len-1][clp] = true;
                q.offer(new Emoji(len-1, clp, time+1));
            }
            // 클립보드에 있는 것을 붙여넣는 경우
            if(clp > 0 && clp + len < s && !visited[len+clp][clp]){
                visited[len+clp][clp] = true;
                q.offer(new Emoji(len+clp, clp, time+1));
            }
        }

        System.out.println(minTime);
    }

}
