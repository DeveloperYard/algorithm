import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] spaces;
	static int white, blue;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		spaces = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j <N; j++) {
				spaces[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeColorSpace(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
	}
	
	private static void makeColorSpace(int rs, int rc, int size) {
		int sum = 0;
		
		for(int r = rs; r < rs+size; r++) {
			for(int c = rc; c < rc+size; c++) {
				sum+=spaces[r][c];
			}
		}
		
		if(sum == 0) { // 전부 하얀색인 경우
			white++;
		} else if(sum == size*size) { // 전부 파란색인 경우
			blue++;
		} else {
			int half = size/2;
			// 좌상단
			makeColorSpace(rs, rc, half);
			// 우상단
			makeColorSpace(rs, rc+half, half);
			// 좌하단
			makeColorSpace(rs+half, rc, half);
			// 우하단
			makeColorSpace(rs+half, rc+half, half);
		}
	}
}
