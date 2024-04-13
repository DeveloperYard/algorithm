import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int C;
    static int[][] arr;
    static ArrayList<Integer> ls = new ArrayList<>();

    static int curN, curM, curMax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        curN = Integer.parseInt(st.nextToken());
        curM = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        curMax = Math.max(curN, curM);

        arr = new int[curMax][curMax];

        for(int i = 0; i < curN; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < curM; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            ls.add(Integer.parseInt(st.nextToken()));
        }

        for(int mt : ls){
            switch (mt) {
                case 1:
                    one();
                    break;
                case 2:
                    two();
                    break;
                case 3:
                    three();
                    break;
                case 4:
                    four();
                    break;
                case 5:
                    five();
                    break;
                case 6:
                    six();
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < curN; i++){
            for(int j = 0; j < curM; j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
    // 상하 반전
    public static void one(){
        for(int i = 0; i < curN/2; i++){
            int[] tmp = Arrays.copyOf(arr[i], curM);
            arr[i] = Arrays.copyOf(arr[curN-i-1], curM);
            arr[curN-i-1] = tmp;
        }
    }

    // 좌우 반전
    public static void two(){
        for(int i = 0; i < curM/2; i++){
            for(int j = 0; j < curN; j++){
                int tmp = arr[j][i];
                arr[j][i] = arr[j][curM-i-1];
                arr[j][curM-i-1] = tmp;
            }
        }
    }

    // 시계 90도 회전
    public static void three(){
        int[][] tmpMap = new int[curMax][curMax];

        for(int i = 0; i < curN; i++){
            for(int j = 0; j < curM; j++){
                tmpMap[j][curN-i-1] = arr[i][j];
            }
        }

        arr = tmpMap;
        int cs = curN;
        curN = curM;
        curM = cs;
    }

    // 반시계 90도 회전
    public static void four(){
        int[][] tmpMap = new int[curMax][curMax];

        for(int i = 0; i < curN; i++){
            for(int j = 0; j < curM; j++){
                tmpMap[curM-j-1][i] = arr[i][j];
            }
        }
        arr = tmpMap;
        int cs = curN;
        curN = curM;
        curM = cs;
    }

    // 배열 배치 1
    public static void five(){
        int[][] tmpMap = new int[curN/2][curM/2];
        int[][] tmp = new int[curMax][curMax];
        for(int i = 0; i < curN/2; i++){
            tmpMap[i] = Arrays.copyOf(arr[i], curM/2);
        }
        // 4 -> 1
        for(int i = 0; i < curN/2; i++){
            for(int j = 0; j < curM/2; j++){
                tmp[i][j] = arr[curN/2+i][j];
            }
        }
        // 3 -> 4
        for(int i = curN/2; i < curN; i++){
            for(int j = 0; j < curM/2; j++){
                tmp[i][j] = arr[i][curM/2+j];
            }
        }

        // 2 -> 3
        for(int i = curN/2; i < curN; i++){
            for(int j = curM/2; j < curM; j++){
                tmp[i][j] = arr[i-curN/2][j];
            }
        }

        // 1 -> 2
        for(int i = 0; i < curN/2; i++){
            for(int j = curM/2; j < curM; j++){
                tmp[i][j] = tmpMap[i][j-curM/2];
            }
        }

        arr = tmp;
    }

    // 배열 배치 2
    public static void six(){
        int[][] tmpMap = new int[curN/2][curM/2];
        int[][] tmp = new int[curMax][curMax];
        for(int i = 0; i < curN/2; i++){
            tmpMap[i] = Arrays.copyOf(arr[i], curM/2);
        }
        // 2 -> 1
        for(int i = 0; i < curN/2; i++){
            for(int j = 0; j < curM/2; j++){
                tmp[i][j] = arr[i][curM/2+j];
            }
        }
        // 3 -> 2
        for(int i = i = 0; i < curN/2; i++){
            for(int j = curM/2; j < curM; j++){
                tmp[i][j] = arr[curN/2+i][j];
            }
        }

        // 4 -> 3
        for(int i = curN/2; i < curN; i++){
            for(int j = curM/2; j < curM; j++){
                tmp[i][j] = arr[i][j-curM/2];
            }
        }

        // 1 -> 4
        for(int i = curN/2; i < curN; i++){
            for(int j = 0; j < curM/2; j++){
                tmp[i][j] = tmpMap[i-curN/2][j];
            }
        }
        arr = tmp;
    }
}
