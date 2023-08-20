import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long[] arr = new long[3];

        for(int i = 0; i < 3; i++){
            arr[i] = sc.nextLong();
        }

        long sumVal = Arrays.stream(arr).sum();
        System.out.println(sumVal);
    }
}
