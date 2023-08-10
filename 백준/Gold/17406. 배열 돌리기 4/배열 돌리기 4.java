import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int[][] ansMap;
	
	static String[] commandsArr;
	static int[] combi;
	static boolean[] visited;
	static int N, M, K;
	
	static ArrayList<String> combiArr; // 명령어를 저장할 곳
	static ArrayList<String> commandsSeqArr; // 명령어의 순서를 저장할 곳 
	private static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][M+1];
        ansMap = new int[N+1][M+1];
        
        combi = new int[K];
        visited = new boolean[K];
        combiArr = new ArrayList<>();
        commandsSeqArr = new ArrayList<>();
        
        
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 1; column <= M; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i = 0; i<=N; i++) {
        	System.arraycopy(map[i], 0, ansMap[i], 0, M+1);
        }
        
        for(int i = 0; i<K; i++) {
        	combiArr.add(br.readLine());
        }
        
        permutation(0); // 명령어 순열 생성
        
        
        for(int i = 0; i<commandsSeqArr.size(); i++) {
        	for(int j = 0; j <= N; j++) {
        		System.arraycopy(ansMap[j], 0, map[j], 0, M+1);
        	}

        	String comSeq = commandsSeqArr.get(i); // 명령어의 순서를 가져옴 ex) 1 3 2 4, 1 2 3 4 etc..
        	
        	
        	StringTokenizer comSt = new StringTokenizer(comSeq);
        	for(int j = 0; j<K; j++) {
        		String com = combiArr.get(Integer.parseInt(comSt.nextToken()));
        		st = new StringTokenizer(com);
        		
        		int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                
                int startRow = r - s;
                int startColumn = c - s;
                int lastRow = r + s;
                int lastColumn = c + s;
                
                while(r+s >= startRow || c+s >= startColumn){
                     solve(startRow++,startColumn++,lastRow--,lastColumn--);
                }
        	}
        	
        	
        	for(int p = 1; p<=N; p++) {
            	int sum = 0;
            	for(int j = 0; j <= M; j++) {
            		sum += map[p][j];
            	}
            	
            	if (sum < ans) {
            		ans = sum;
            	}
            }   
        }
        System.out.println(ans);
    }
    
	private static void permutation(int count) {
    	if(count == K) {
    		String str = "";
    		for(int i = 0; i< K; i++) {
    			str += combi[i]+" ";
    		}
    		commandsSeqArr.add(str);
    		
    		return;
    	}
    	for(int i = 0; i<K; i++) {
    		if(visited[i]) continue;
    		combi[count] = i;
    		visited[i] = true;
    		permutation(count+1);
    		visited[i] = false;
    	}
    }
	
	
    private static void solve(int startRow, int startColumn , int lastRow, int lastColumn) {

        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = startColumn; i < lastColumn; i++) {
            queue.offer(map[startRow][i]);
        }
        for (int i = startRow; i < lastRow; i++) {
            queue.offer(map[i][lastColumn]);
        }
        for (int i = lastColumn; i > startColumn; i--) {
            queue.offer(map[lastRow][i]);
        }
        for (int i = lastRow; i > startRow; i--) {
            queue.offer(map[i][startColumn]);
        }
        
        // code : 시계 방향으로 한 번 돌림
        Collections.rotate((List<?>) queue, 1);


        for (int i = startColumn; i < lastColumn; i++) {
            map[startRow][i] = queue.poll();
        }
        for (int i = startRow; i < lastRow; i++) {
            map[i][lastColumn] = queue.poll();
        }
        for (int i = lastColumn; i > startColumn; i--) {
            map[lastRow][i] = queue.poll();
        }
        for (int i = lastRow; i > startRow; i--) {
            map[i][startColumn] = queue.poll();
        }
        
        
    }
}
