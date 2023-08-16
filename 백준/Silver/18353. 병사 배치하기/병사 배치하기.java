import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static List<Integer> soldiers = new ArrayList<>();
	static ArrayList<Integer> vc = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; i++) {
			soldiers.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.reverse(soldiers);
		
		for(int i = 0; i < N; i++) {
			if(vc.isEmpty()) {
				vc.add(soldiers.get(i));
			} else {
				if(vc.get(vc.size()-1) < soldiers.get(i)) {
					vc.add(soldiers.get(i));
				} else {
					vc.set(lower_bound(soldiers.get(i)), soldiers.get(i));
				}
			}
		}
		System.out.println(soldiers.size()-vc.size());
	}
	
	public static int lower_bound(int target) {
		int start = 0;
		int end = vc.size()-1;
		int index = 0;
		
		while(start <= end) {
			int mid = (start + end)/2;
			if(vc.get(mid) >= target) {
				end = mid-1;
				index = mid;
			} else {
				start = mid+1;
			}
		}
		
		return index;
	}
}