import java.io.*;
import java.util.*;

public class Main {
    static int[] trees;
    static int N;
    static int minLength;
    static int maxHeight = 0;
    static int curSumCutTreeLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        trees = new int[N];
        minLength = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees);
        findMaxHeight(0, trees[N-1]);

        System.out.println(maxHeight);
    }

    public static void findMaxHeight(int start, int end){
        if(start >= end)
            return;
        int mid = start + (end - start)/2;
        if(cutTrees(mid)){
            if(mid > maxHeight){
                maxHeight = mid;
            }
            curSumCutTreeLength = 0;
            findMaxHeight(mid+1, end);
        } else {
            curSumCutTreeLength = 0;
            findMaxHeight(start, mid);
        }
    }

    public static boolean cutTrees(int n){
        for(int i = 0; i < N; i++){
            if(trees[i] <= n)
                continue;
            if(curSumCutTreeLength >= minLength - (trees[i] - n)){
                return true;
            }
            curSumCutTreeLength += (trees[i]-n);
        }
        return false;
    }
}
