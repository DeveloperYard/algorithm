import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 분기
	static int totalScore; //받을 수 있는 점수 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위해 BufferedReader 선언
		
		N = Integer.parseInt(br.readLine()); // 처음에는 분기가 나오므로 br.readLine()으로 할당
		
		/*
		 * === 해당 문제를 해결할 아이디어 ===
		 * 
		 * 업무를 진행하던 중 새로운 업무가 들어오면 그것부터 시작, 그리고 새로운 업무가 들어오지 않는다면 이전에 하던 작업을
		 * 한다고 했기 때문에 Stack을 이용해서 풀어야겠다고 생각했다.
		 * 
		 * 스택의 맨 위는 내가 지금 하고 있는 일일 것이고, 새로운 업무가 들어온다면 스택에 push해서 지금 하는 일을 변경해주면 되기 때문!
		 * 
		 * Stack에 들어갈 요소의 형태는 int[]로 지정하였다. 첫 번째는 점수, 그리고 두 번째는 소요되는 시간이 들어가게 된다.
		 * 
		 * */
		Stack<int[]> s = new Stack<>();
		
		for(int i = 0; i < N; i++) { // 분기만큼 진행된다.
			StringTokenizer st = new StringTokenizer(br.readLine()); // 입력을 받기 위함
			if(Integer.parseInt(st.nextToken()) == 0) { // 업무가 들어오지 않았을 때
				if(s.isEmpty()) { // 스택이 비었다면 할 일이 없는 것이므로 스킵!
					continue;
				} else { // 스택이 비지 않았다면 맨 위의 작업은 한 번 진행되어야 하는 것
					int[] a = s.pop(); // 맨 위의 작업을 가져온다.
					if(a[1] == 1) { // 만약 남은 작업 시간이 1이라면, 지금 시간에 1만큼의 일을 해서 끝낼 수 있기 때문에 
									// 전체 평가 점수에 현재 하던 일의 score를 더해준다.
						totalScore += a[0];
					} else { // 만약 남은 작업 시간이 1보다 크다면 작업 시간에서 1만큼 빼주고 다시 스택에 push한다.
						a[1] -= 1;
						s.push(a);
					}
				}
				continue; // 위의 과정들을 끝냈다면 continue한다.
			}
			else { // 입력이 1 A T 형태로 들어 왔을 때, 새로운 업무가 추가된 것!
				int score = Integer.parseInt(st.nextToken()); // 첫 번째는 Score
				int consumeTime = Integer.parseInt(st.nextToken()); // 두 번째는 소요되는 작업 시간이다.
				
				if(consumeTime-1 == 0) { // 만약 작업에 소요되는 시간이 1이라면, 현재 시간에 일을 완료하고 넘어가도 된다.
					totalScore += score; // 전체 평가 점수에 현재 업무의 점수를 더해준다.
					continue; // 더해준 후, 스택에 넣지 않고 스킵한다.
				}
				s.push(new int[] {score, consumeTime-1}); // 만약 소요되는 시간이 1이 넘는다면, 새로운 형태로 배열을 만들어 점수, 그리고 소요 시간 -1을 삽입해준다.
				// 소요시간 -1 으로 넣어주는 이유는 현재 시간에 들어와서 다음 시간이 되기 전 까지 일을 한 번 수행할 수 있기 때문이다.
			}						
		}
		System.out.println(totalScore); // 총 평가 점수를 출력한다.
	}
}
