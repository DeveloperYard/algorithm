import java.util.*;

public class Main {
	
	static int count;
	static boolean[] isSelected;
	static int size;
	static int arr[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.nextLine();
		isSelected = new boolean[s.charAt(0)-'0' + 1];
		size = Integer.parseInt(s.split(" ")[1]);
		arr = new int[size];
		nAM(1, 0);
	}
	
	private static void nAM(int idx, int count) {
		if (count == size) {
			for(int i : arr) {
				System.out.print(i+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i = idx; i<isSelected.length; i++) {
//			System.out.println("i : " + i);
			if (!isSelected[i]) {
				arr[count] = i;
				isSelected[i] = true;
//				System.out.println("idx : " + idx);
				nAM(i+1, count+1);
				isSelected[i] = false;
			}
		}
	}
}
