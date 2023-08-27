import java.io.*;
import java.util.*;

public class Main {

    static class QueueNode implements Comparable<QueueNode>{
        int index;
        int distance;

        QueueNode(int index, int distance){
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(QueueNode q){
            if(this.distance < q.distance){
                return -1;
            }
            return 1;
        }
    }

    static ArrayList<ArrayList<QueueNode>> ql = new ArrayList<>();
    static int V, E;
    static final int INF = Integer.MAX_VALUE;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= V; i++){
            ql.add(new ArrayList<>());
        }
        distance = new int[V+1];
        Arrays.fill(distance, INF);

        int start = Integer.parseInt(br.readLine());

        // edge input
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            ql.get(from).add(new QueueNode(to, weight));
        }

        dijkstra(start);

        for(int i = 1; i <= V; i++){
            if(distance[i] == INF){
                sb.append("INF").append("\n");
                continue;
            }
            sb.append(distance[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<QueueNode> q = new PriorityQueue<>();

        q.offer(new QueueNode(start, 0));

        distance[start] = 0;

        while(!q.isEmpty()){
            QueueNode node = q.poll();

            int dist = node.distance;
            int now = node.index;

            if(distance[now] < dist) continue;

            for(int i = 0; i < ql.get(now).size(); i++){
                int cost = distance[now] + ql.get(now).get(i).distance;
                if(cost < distance[ql.get(now).get(i).index]){
                    distance[ql.get(now).get(i).index] = cost;
                    q.offer(new QueueNode(ql.get(now).get(i).index, cost));
                }
            }
        }
    }
}
