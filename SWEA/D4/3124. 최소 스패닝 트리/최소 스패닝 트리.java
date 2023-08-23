import java.io.*;
import java.util.*;

public class Solution {

    static int TC;
    static int N, M;
    static int[] parents;
    static Edge[] edges;
    static int find(int a){
        if(a == parents[a]){
            return a;
        }
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;

        return true;
    }
    static class Edge implements Comparable<Edge>{
        int from, to, weight;

        Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.weight, e.weight);
        }
    }

    static void make(){
        parents = new int[N+1];
        for(int i = 1; i <= N; i++){
            parents[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= TC; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            edges = new Edge[M];
            make();
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges[i] = new Edge(from, to, weight);
            }

            Arrays.sort(edges);

            long result = 0;
            int count = 0;

            for(int i = 0; i < M; i++){
                if(union(edges[i].from, edges[i].to)){
                    result += edges[i].weight;
                    if(++count == N-1){
                        break;
                    }
                }
            }

            sb.append("#"+t+" "+result).append("\n");
        }

        System.out.println(sb);
    }
}
