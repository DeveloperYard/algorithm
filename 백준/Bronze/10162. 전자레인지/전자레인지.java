import java.util.*;

/*
 * === 문제 해결 아이디어 ===
 * 
 * i) 최소한의 버튼만을 눌러 목표 시간을 만들어야 하므로 DP를 이용해야 한다고 생각하였다.
 * -> 하지만 초기에 dp배열로만 구현하니, 각 버튼이 얼마나 쓰였는지 알 수 있는 방법이 없었다.
 * 
 * ii) 각 버튼이 얼마나 눌렸는지 저장할 2차원 배열을 하나 더 생성해서, dp배열로 최소값을 비교해서 그 버튼 수보다 적다면 갱신하는 방법을 이용하였다.
 * 
 * */

public class Main {
	static int[] dp; // 버튼의 개수를 저장할 dp배열
	private static int[][] storingBtnList; // 각각의 버튼이 얼마나 쓰였는지 저장할 배열
	// storingBtnList의 행은 현재 시간, 열은 현재 시간까지 오기 위해 눌린 버튼의 수이다. 0은 A버튼 횟수, 1은 B버튼 횟수, 2는 C버튼 횟수를 저장해준다. 
	public static void main(String[] args) {
		// 세 가지 버튼의 초기값을 입력해준다.
		int btnA = 300; 
		int btnB = 60;
		int btnC = 10;
		
		Scanner sc = new Scanner(System.in); // 숫자 하나만 입력받으면 되므로 Scanner를 이용하였다.
		int T = sc.nextInt(); // 만들어야 하는 시간이다.
		
		dp = new int[10001]; // 주어질 수 있는 T가 10000 이하이므로 이렇게 지정하였다.
		storingBtnList = new int[10001][3];
		
		// 각 버튼들을 한 번 눌렀을 때의 그 시간을 초기화해준다.
		dp[btnA] = 1; 
		storingBtnList[btnA][0] = 1; // A버튼만큼의 시간에서 A버튼 횟수를 초기화해준다.
		dp[btnB] = 1;
		storingBtnList[btnB][1] = 1; // B버튼만큼의 시간에서 B버튼 횟수를 초기화해준다.
		dp[btnC] = 1;
		storingBtnList[btnC][2] = 1; // C버튼만큼의 시간에서 C버튼 횟수를 초기화해준다.
		
		// i를 10부터로 지정한 이유는 버튼 중 최소값인 C의 값, 즉 10 이하라면 만들 수 있는 경우의 수가 없기 때문이다.
		for(int i = 10; i <= T; i++) {
			if(dp[i-btnC] > 0) { // 만약 C버튼을 누르기 전의 시간이 갱신되어 있었다면 i-btnC에서 i 만큼의 시간을 만들 수 있다는 뜻이다. 아래 A, B 또한 같다.
				if(dp[i] == 0) { // dp[i]가 0이라면 아직 초기화가 되지 않은 것이다.
					dp[i] = dp[i-btnC]+1; // i-btnC만큼의 시간에서 버튼 한 개(btnC)를 누른 횟수 만큼 초기화해준다.
					storingBtnList[i][0] = storingBtnList[i-btnC][0]; // 이전 시간의 버튼의 수만큼 초기화해준다.
					storingBtnList[i][1] = storingBtnList[i-btnC][1]; // 이전 시간의 버튼의 수만큼 초기화해준다.
					storingBtnList[i][2] = storingBtnList[i-btnC][2]+1; // 이전 시간 버튼 수에서 C버튼을 한 번 더 누르는 것으로 초기화해준다.
				}
				else {
					if(dp[i] > dp[i-btnC]+1) { // 만약 갱신은 되어있는데, i-btnC에서 C버튼을 한 번 더 누르는 것보다 걸리는 횟수가 많다면
						storingBtnList[i][0] = storingBtnList[i-btnC][0]; // 역시 위와 같이 초기화해준다. 버튼이 눌리는 최소값을 구해야 하기 때문이다.
						storingBtnList[i][1] = storingBtnList[i-btnC][1];
						storingBtnList[i][2] = storingBtnList[i-btnC][2]+1; // 위와 같이 C버튼의 개수+1한 것으로 갱신해준다.
						
						dp[i] = dp[i-btnC]+1; // dp[i]를 갱신해준다.
					}
					// 만약 dp[i] <= dp[i-btnC]+1 라면 굳이 갱신하지 않아도 최소값이므로 넘어간다.
				}
			}
			if(i >= 60 && dp[i-btnB] > 0) { // C버튼과 같으나 IndexOutOfBounds 예외를 방지하기 위해 범위 조건을 추가해주었다.
				if(dp[i] == 0) {
					dp[i] = dp[i-btnB]+1;
					storingBtnList[i][0] = storingBtnList[i-btnB][0];
					storingBtnList[i][1] = storingBtnList[i-btnB][1]+1;
					storingBtnList[i][2] = storingBtnList[i-btnB][2];
				}
				else {
					if(dp[i] > dp[i-btnB]+1) {
						storingBtnList[i][0] = storingBtnList[i-btnB][0];
						storingBtnList[i][1] = storingBtnList[i-btnB][1]+1;
						storingBtnList[i][2] = storingBtnList[i-btnB][2];
						
						dp[i] = dp[i-btnB]+1;
					}
				}
			}
			if(i >= 300 && dp[i-btnA] > 0) { // 위와 같이 예외 방지를 위해 조건을 추가해주었다.
				if(dp[i] == 0) {
					dp[i] = dp[i-btnA]+1;
					storingBtnList[i][0] = storingBtnList[i-btnA][0]+1;
					storingBtnList[i][1] = storingBtnList[i-btnA][1];
					storingBtnList[i][2] = storingBtnList[i-btnA][2];
		
				}
				else {
					if(dp[i] > dp[i-btnA]+1) {
						storingBtnList[i][0] = storingBtnList[i-btnA][0]+1;
						storingBtnList[i][1] = storingBtnList[i-btnA][1];
						storingBtnList[i][2] = storingBtnList[i-btnA][2];
						
						dp[i] = dp[i-btnA]+1;
					}
				}
			}
		}
		
		// 만약 목표한 시간의 모든 버튼의 횟수가 0이라면, 만들 수 있는 경우의 수가 없다는 뜻이므로 -1을 출력한다.
		if(storingBtnList[T][0] == 0 && storingBtnList[T][1] == 0 && storingBtnList[T][2] == 0) {
			System.out.println(-1);
		} else {
			// 그것이 아니라면 각 버튼이 눌린 횟수를 A, B, C 순으로 출력한다.
			System.out.println(storingBtnList[T][0] + " " + storingBtnList[T][1]+
					" " + storingBtnList[T][2]);			
		}
	}
}
