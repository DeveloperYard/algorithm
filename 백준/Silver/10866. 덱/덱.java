import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static ArrayDeque<Integer> q = new ArrayDeque<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()){
                case "push_back":
                    q.offerLast(Integer.parseInt(st.nextToken()));
                    break;
                case "push_front":
                    q.offerFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "front":
                    if(q.isEmpty()){
                        System.out.println("-1");
                    } else {
                        System.out.println(q.peekFirst());
                    }
                    break;
                case "back":
                    if(q.isEmpty()){
                        System.out.println("-1");
                    } else {
                        System.out.println(q.peekLast());
                    }
                    break;
                case "pop_front":
                    if(q.isEmpty()){
                        System.out.println("-1");
                    } else {
                        System.out.println(q.pollFirst());
                    }
                    break;
                case "pop_back":
                    if(q.isEmpty()){
                        System.out.println("-1");
                    } else {
                        System.out.println(q.pollLast());
                    }
                    break;
                case "size":
                    System.out.println(q.size());
                    break;
                case "empty":
                    int val = (q.isEmpty()) ? 1 : 0;
                    System.out.println(val);
                    break;
                default:
                    break;
            }
        }
    }
}
