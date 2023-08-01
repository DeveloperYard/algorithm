package toyproject.somedaybucket.myAlgo;


import java.util.Scanner;

class Switch{
    static int[] switchArr;
    static int start_pointer;
    static int end_pointer;
    static void man(int switchNum, int size){
        for(int i = switchNum; i <= size; i++){
            // 남학생의 경우, 받은 번호부터 사이즈까지 나누어 떨어지는 수(배수)라면 스위치를 반대로 놓도록 하였다.
            if((i % switchNum == 0)){
                switchArr[i] = (switchArr[i] == 1) ? 0 : 1;
            }
        }
    }

    static void women(int switchNum, int size){
        // 투 포인터 알고리즘, 두 점의 위치를 기억
        /*
        * 여학생의 경우, 처음 위치가 들어왔을 때 대칭적으로 뻗어나가면서 비교해야 하기에 투포인터를 사용하여 앞 뒤를 비교할 수 있도록 하였다.
        * 만약 인덱스의 범위를 벗어나거나, 값이 다를 경우 리턴하여 연산을 끝낼 수 있도록 하였다.
        *
        * */
        switchArr[switchNum] = (switchArr[switchNum] == 1) ? 0 : 1;
        start_pointer = switchNum-1;
        end_pointer = switchNum+1;
        if(!checkPossibles(start_pointer, end_pointer, size)){
            return;
        }
        while (true){
            if(!checkPossibles(start_pointer, end_pointer, size)){
                return;
            }
            if (switchArr[start_pointer] == switchArr[end_pointer]){
                switchArr[start_pointer] = (switchArr[start_pointer] == 1) ? 0 : 1;
                switchArr[end_pointer] = (switchArr[end_pointer] == 1) ? 0 : 1;
            } else {
                return;
            }

            start_pointer--;
            end_pointer++;

        }
    }

    static boolean checkPossibles(int i, int j, int size){
        // 왼쪽으로 가는 포인터가 0에 다다르거나, -> 1번 인덱스부터 이용하기 위함
        // 오른쪽으로 가는 포인터가 끝 사이즈를 넘어가게 되면 false 리턴!
        if (i <= 0 || j > size){
            return false;
        }
        return true;
    }
}

public class boj1244 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Switch.switchArr = new int[101];
        sc.nextLine(); // nextInt() -> 개행 문자 전까지 처리하기 때문에 개행 문자를 없애주기 위하여 사용!
        String s = sc.nextLine();

        s = s.replace(" ", ""); // 1 아니면 0으로 이루어져 있기 때문에 공백을 없앰.
        for(int i = 1; i<=s.length(); i++){
            Switch.switchArr[i] = s.charAt(i-1) - '0'; // 인덱스로 참조했을 떄 '0' 만큼 빼서 int 형으로 대입할 수 있도록 함
        }

        int t = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i<t; i++){
            s = sc.nextLine();
            // 이 아래 두 줄은 10을 넘어갈 수도 있기 때문에 공백으로 자른 후 Integer wrapper class의 parseInt() 메서드로 변환해줌
            int gender = Integer.parseInt(s.split(" ")[0]);
            int switch_num = Integer.parseInt(s.split(" ")[1]);
            if (gender == 1){
                Switch.man(switch_num, n);
            }
            else if(gender == 2){
                Switch.women(switch_num, n);
            }
        }

        for(int i = 1; i<=n; i++){
            if (i % 20 == 1 && i > 1){ // 첫 번째 줄 말고, 21, 41, ...번째 줄 등 20줄마다 줄바꿈을 해주기 위한 식
                System.out.println();
            }
            System.out.print(Switch.switchArr[i] + " ");
        }
    }
}
