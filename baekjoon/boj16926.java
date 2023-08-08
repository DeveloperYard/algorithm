package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj16926 {

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];

        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 1; column <= M; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        int startRow = 1;
        int startColumn = 1;

        int min = Math.min(N / 2, M / 2);
        while (min >= startRow || min >= startColumn){
            solve(startRow++,startColumn++,N--,M--,R);
        }

        for (int row = 1; row < map.length; row++) {
            for (int column = 1; column < map[0].length; column++) {
                sb.append(map[row][column]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    private static void solve(int startRow, int startColumn , int lastRow, int lastColumn, int R) {

        Queue<Integer> queue = new LinkedList<>();
        
        
        for (int i = startColumn; i < lastColumn; i++) {
            queue.offer(map[startRow][i]);
        }
        for (int i = startRow; i < lastRow; i++) {
            queue.offer(map[i][lastColumn]);
        }
        for (int i = lastColumn; i > startColumn; i--) {
            queue.offer(map[lastRow][i]);
        }
        for (int i = lastRow; i > startRow; i--) {
            queue.offer(map[i][startColumn]);
        }
        int num = 2*(lastRow-startRow+1) + 2*(lastColumn - startColumn+1) - 4;
        R %= num;
        while (R-- > 0) {
            queue.offer(queue.poll());
        }
        


        for (int i = startColumn; i < lastColumn; i++) {
            map[startRow][i] = queue.poll();
        }
        for (int i = startRow; i < lastRow; i++) {
            map[i][lastColumn] = queue.poll();
        }
        for (int i = lastColumn; i > startColumn; i--) {
            map[lastRow][i] = queue.poll();
        }
        for (int i = lastRow; i > startRow; i--) {
            map[i][startColumn] = queue.poll();
        }

    }


}
