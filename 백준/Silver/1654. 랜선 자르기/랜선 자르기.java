import java.util.Scanner;

public class Main {
    static int N; // cnt of cable
    static int M; // target cnt of cable
    static int[] cableArr;
    static long maxCableLength = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        cableArr = new int[N];
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++){
            int cable = sc.nextInt();

            if(cable > max) max = cable;

            cableArr[i] = cable;
        }

        binarySearch(1, max);

        System.out.println(maxCableLength);
    }

    private static void binarySearch(long start, long end){
        if(start > end) return;

        long mid = start + (end - start)/2;
        int cnt = 0;
        for(int i = 0; i < N; i++){
            cnt += cableArr[i]/mid;
        }

        if(cnt >= M){
            if(maxCableLength < mid) maxCableLength = mid;
            binarySearch(mid+1, end);
        } else binarySearch(start, mid-1);
    }
}
