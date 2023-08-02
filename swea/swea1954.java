package java_boj;

import java.util.*;


class Snail {
	static int[][] arr;
	static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static boolean [][] visited;
	static int num = 1;
	
	static void snailMatrix() {
		int x = 0, y = 0;
		visited[0][0] = true;
		int curDir = 0;
		for(int i = 0; i<arr.length*arr.length; i++) {
			arr[x][y] = num++;
			visited[x][y] = true;
			int nx = x + direction[curDir][0];
			int ny = y + direction[curDir][1];
			if(!changeDir(nx, ny)) {
				curDir+=1;
				curDir %= 4;
				x += direction[curDir][0];
				y += direction[curDir][1];
				visited[x][y] = true;
			} else {
				x = nx;
				y = ny;
				visited[x][y] = true;
			}
		}
	}
	
	static boolean changeDir(int x, int y) {
		if (x < 0 || y < 0 || x >= arr.length || y >= arr.length) {

			return false;
		}
		if (visited[x][y] == true) {

			return false;
		}
		return true;
	}
}

public class swea1954 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for(int i = 1; i<t+1; i++) {
			int n = sc.nextInt();
			if (n == 1) {
				System.out.println("#"+i);
				System.out.println(1);
				continue;
			}
			Snail.arr = new int[n][n];
			Snail.visited = new boolean[n][n];
			
			Snail.snailMatrix();
			
			System.out.println("#"+i);
			
			for(int j = 0; j<Snail.arr.length; j++) {
				for (int e : Snail.arr[j]) {
					System.out.print(e + " ");
				}
				System.out.println();
			}
			
			Snail.num = 1;
		}
	}
}
