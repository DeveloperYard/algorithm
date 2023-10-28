import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static HashSet<Integer> friendsArea = new HashSet<>();
    static int M;

    static int[][] map;
    static int mapIdx;
    static ArrayList<ArrayList<Node>> al = new ArrayList<>();
    static final int INF = 10000*100000+1;
    static int myHome;
    static int minDist = -1;
    static boolean[] visited;
    static class Node {
        int to, dist;

        Node(int to, int dist){
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 3; i++){
            friendsArea.add(Integer.parseInt(st.nextToken())-1);
        }

        map = new int[friendsArea.size()][N];
        for(int i = 0 ; i < map.length; i++){
            Arrays.fill(map[i], INF);
        }

        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++)
            al.add(new ArrayList<>());

        int from, to, dist;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            from = Integer.parseInt(st.nextToken())-1;
            to = Integer.parseInt(st.nextToken())-1;
            dist = Integer.parseInt(st.nextToken());

            al.get(from).add(new Node(to, dist));
            al.get(to).add(new Node(from, dist));
        }
        for(int e : friendsArea){
            visited = new boolean[N];
            dijkstra(e);
            mapIdx++;
        }
        // floyd(); // memory exceed!
        whereIsMyHome();

        System.out.println(myHome);
    }

    private static void dijkstra(int start){
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));

        q.add(new Node(start, 0));
        map[mapIdx][start] = 0;
        visited[start] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(!visited[cur.to]){
                visited[cur.to] = true;
            }

            for(Node n : al.get(cur.to)){
                if(!visited[n.to] && map[mapIdx][n.to] > cur.dist + n.dist){
                    map[mapIdx][n.to] = cur.dist + n.dist;
                    q.add(new Node(n.to, map[mapIdx][n.to]));
                }
            }
        }
    }
    private static void whereIsMyHome(){
        for(int i = 0; i < N; i++){
            int curMinDist = INF;
            for(int j = 0; j < map.length; j++){
                if(curMinDist > map[j][i]){
                    curMinDist = map[j][i];
                }
            }
            if(minDist < curMinDist){
                myHome = i+1;
                minDist = curMinDist;
            }
        }
    }
}
