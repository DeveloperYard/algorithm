// boj 1780 - 종이의 개수, 분할정복을 통해 구현하기!

import java.io.*;
import java.util.*;

public class Main {

    // count of each paper
    static int minusPaper;
    static int zeroPaper;
    static int onePaper;

    static int N; // size of paper, 3^k
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        paper = new int[N][N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dividePaper(0, 0, N);
        StringBuilder sb = new StringBuilder();

        sb.append(minusPaper).append("\n")
                .append(zeroPaper).append("\n")
                .append(onePaper).append("\n");

        System.out.println(sb);
    } // end of main

    private static void dividePaper(int i, int j, int size){
        // 최소 단위로 나눠졌을 때
        if(!checkPaper(i, j, size)){
            dividePaper(i, j, size / 3);
            dividePaper(i, j + size / 3, size / 3);
            dividePaper(i, j + (size / 3) * 2, size / 3);
            dividePaper(i + size / 3, j, size / 3);
            dividePaper(i + size / 3, j + size / 3, size / 3);
            dividePaper(i + size / 3, j + (size / 3) * 2, size / 3);
            dividePaper(i + (size / 3) * 2, j, size / 3);
            dividePaper(i + (size / 3) * 2, j + size / 3, size / 3);
            dividePaper(i + (size / 3) * 2, j + (size / 3) * 2, size / 3);
            // 9개로 나눠야 함
        }
    } // end of dividePaper
    private static boolean checkPaper(int x, int y, int size){
        int oneCnt = 0;
        int zeroCnt = 0;
        int minusCnt = 0;

        for(int i = x; i < x+size; i++){
            for(int j = y; j < y+size; j++){
                if(paper[i][j] == 1) oneCnt++;
                if(paper[i][j] == 0) zeroCnt++;
                if(paper[i][j] == -1) minusCnt++;
            }
        }

        if(oneCnt == size*size){
            onePaper++;
            return true;
        } else if(zeroCnt == size*size){
            zeroPaper++;
            return true;
        } else if(minusCnt == size*size){
            minusPaper++;
            return true;
        }
        return false;
    }

} // end of class
