import java.io.*;
import java.util.*;

public class Main {

    static Deque<Integer> que = new ArrayDeque<>();
    static Deque<Integer> stack = new ArrayDeque<>();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i< N; i++){
            que.add(Integer.parseInt(st.nextToken()));
        }
        int peopleNum = 1;
        while(!que.isEmpty() || !stack.isEmpty()){
            // queue 만 비었을 때
            if(que.isEmpty()){
                if(stack.getLast() == peopleNum){
                    stack.pollLast();
                    peopleNum++;
                } else {
                    break;
                }
                // queue 와 stack 모두 비어있지 않을 때
            } else if(!que.isEmpty() && !stack.isEmpty()){
                if(que.getFirst() == peopleNum){
                    que.pollFirst();
                    peopleNum++;
                } else if(stack.getLast() == peopleNum){
                    stack.pollLast();
                    peopleNum++;
                 }else {
                    if(stack.getLast() < que.getFirst()){
                        break;
                    } else {
                        stack.addLast(que.pollFirst());
                    }
                }
                //  stack 만 비어있을 때
            } else {
                if(que.getFirst() == peopleNum){
                    que.pollFirst();
                    peopleNum++;
                } else {
                    stack.addLast(que.pollFirst());
                }
            }
        }

        if(que.isEmpty() && stack.isEmpty()){
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }
    }
}
