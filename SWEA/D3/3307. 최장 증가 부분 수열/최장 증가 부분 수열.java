import java.io.*;
import java.util.*;

public class Solution {
    static int TC;
    static int[] lisArr;
    static int minIdx;
    static int lisIdx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= TC; tc++){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            lisArr = new int[N];
            int[] compArr = new int[N];
            for(int i = 0; i < N; i++){
                compArr[i] = Integer.parseInt(st.nextToken());
            }
            lisArr[0] = compArr[0];
            lisIdx = 0;

            for(int i = 1; i < N; i++){
                minIdx = N+1;
                if(lisArr[lisIdx] < compArr[i]){
                    lisArr[++lisIdx] = compArr[i];
                } else {
                    binarySearch(0, lisIdx, compArr[i]);
                    lisArr[minIdx] = compArr[i];
                }
            }

            sb.append("#" + tc + " ").append(lisIdx+1).append("\n");
        }

        System.out.println(sb.toString());
    } // end of main

    private static void binarySearch(int start, int end, int target){
        if(start > end)
            return;

        int mid = start + (end - start)/2;
        if(lisArr[mid] > target){
            if(mid < minIdx){
                minIdx = mid;
            }
            binarySearch(start, mid-1, target);
        } else {
            binarySearch(mid+1, end, target);
        }
    }

} // end of class
