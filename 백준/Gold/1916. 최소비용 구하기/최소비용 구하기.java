import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static ArrayList<Node>[] list;
    static int[] dist;
    static boolean[] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        dist = new int[N+1];
        visit = new boolean[N+1];
        // 1. 거리 배열 및 그래프 배열 초기화
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }
        // 2. 그래프 그리기
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, weight));
        }
        // 3. 출발지 ~ 목적지 다익스트라 알고리즘 적용하여 출력
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        bw.write(dijkstra(start, end) + "\n");
        bw.close();
        br.close();
    }
    
    static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        while(!pq.isEmpty()) {
            Node nowNode = pq.poll();
            int now = nowNode.targetNode;
            if(!visit[now]) {
                visit[now] = true;
                for(Node n : list[now]) {
                    // 선택노드 + 비용 < 타깃 노드일 때 업데이트
                    if(dist[n.targetNode] > dist[now] + n.value) {
                        dist[n.targetNode] = dist[now] + n.value;
                        pq.add(new Node(n.targetNode, dist[n.targetNode]));
                    }
                }
            }
        }
        return dist[end];
    }
    
    static class Node implements Comparable<Node> {
        int targetNode;
        int value;
        Node(int targetNode, int value) {
            this.targetNode = targetNode;
            this.value = value;
        }
        @Override
        public int compareTo(Node n) {
            return this.value - n.value;
        }
    }
}