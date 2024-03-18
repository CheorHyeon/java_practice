import java.util.*;
import java.io.*;
public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static long[] distance;
    static Edge edges[];
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        distance = new long[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE); // 최단 거리 배열 초기화
        // 엣지 리스트 데이터 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, time);
        }
        // 벨만 포드 알고리즘 주행
        distance[1] = 0;
        for(int i = 1; i < N; i++) {    // N보다 1개 적은 수 만큼 반복
            for(int j = 0; j < M; j++) {
                Edge edge = edges[j];
                // 더 작은 최단 거리가 있을 때 업데이트하기
                if (distance[edge.start] != Integer.MAX_VALUE 
                    && distance[edge.end] > distance[edge.start] + edge.time) {
                    distance[edge.end] = distance[edge.start] + edge.time;
                }
            }
        }
        // 음수 사이클 확인하기
        boolean mCycle = false;
        for (int i = 0; i < M; i++) {
            Edge edge = edges[i];
            if (distance[edge.start] != Integer.MAX_VALUE 
                && distance[edge.end] > distance[edge.start] + edge.time) {
                mCycle = true;
                break;
            }
        }
        // 음수 사이클이 없을 때
        if(!mCycle) {
            // 1번부터 출발 -> 기준점이 1번이니 2번부터 출력
            for(int i = 2; i <= N; i++) {
                if(distance[i] == Integer.MAX_VALUE)
                   System.out.println(-1);
                else {
                    System.out.println(distance[i]);
                }
            }
        }
        // 음수 사이클이 있을 때
        else {
            System.out.println(-1);
        }
    }
}

class Edge {
    int start, end, time;
    public Edge(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}