// DNA 비밀번호 - Sliding Window

import java.io.*;
import java.util.*;

public class Main {
	
	static HashMap<Character, Integer> countDNA = new HashMap<>();
	static int count = 0;
	static HashMap<Character, Integer> standard = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		char[] c = new char[Integer.parseInt(st.nextToken())];
		int subLen = Integer.parseInt(st.nextToken());
		
		// 'A', 'C', 'G', 'T'의 최소 개수를 세어야 함
		
		c = br.readLine().toCharArray();
		
		countDNA.put('A', 0);
		countDNA.put('C', 0);
		countDNA.put('G', 0);
		countDNA.put('T', 0);
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 4; i++) {
			if(i == 0) {
				standard.put('A', Integer.parseInt(st.nextToken()));
			}
			if(i == 1) {
				standard.put('C', Integer.parseInt(st.nextToken()));
			}
			if(i == 2) {
				standard.put('G', Integer.parseInt(st.nextToken()));
			}
			if(i == 3) {
				standard.put('T', Integer.parseInt(st.nextToken()));
			}
		}
		sliding(c, subLen);
		
		System.out.println(count);
	}
	
	private static void sliding(char[] c, int subLen) {
		for(int i = 0; i < subLen; i++) {
			if(countDNA.containsKey(c[i])) {
				countDNA.put(c[i], countDNA.get(c[i])+1);
			}
		}
		
		checkSure();
		
		for(int i = 1; i < c.length - subLen + 1; i++) {
			
			if(countDNA.containsKey(c[i-1])) {
				countDNA.put(c[i-1], countDNA.get(c[i-1])-1);
			}
			if(countDNA.containsKey(c[i+subLen-1])) {
				countDNA.put(c[i+subLen-1], countDNA.get(c[i+subLen-1])+1);
			}
			checkSure();
		}
		
		
	}
	
	private static void checkSure() {
		for(Map.Entry<Character, Integer> entry : countDNA.entrySet()) {
			if(entry.getValue() < standard.get(entry.getKey())) return;
		}
		
		count++;
	}
}
