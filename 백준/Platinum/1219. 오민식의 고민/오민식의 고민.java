import java.util.*;
import java.io.*;
public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, sCity, eCity;
    static long distance[], cityMoney[];
    static Edge edges[];
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 노드 개수, 시작 위치, 종료 위치, 엣지 수
        N = Integer.parseInt(st.nextToken());
        sCity = Integer.parseInt(st.nextToken());
        eCity = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        distance = new long[N];
        cityMoney = new long[N];
        Arrays.fill(distance, Long.MIN_VALUE); // 최단 거리 배열 초기화
        for(int i = 0; i < M; i++) { // 엣지 리스트 데이터 저장
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, price);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cityMoney[i] = Long.parseLong(st.nextToken());
        }
        distance[sCity] = cityMoney[sCity];  // 시작 위치 거리 초기화(최초 도시 수익)
        // 양수 사이클 전파되도록 충분히 큰수로 반복
        for(int i = 0; i <= N + 100; i++) {
            for(int j = 0; j < M; j++) {
                Edge edge = edges[j];
                int start = edge.start;
                int end = edge.end;
                int price = edge.price;
                // 출발 노드가 방문하지 않은 노드이면 skip
                if (distance[start] == Long.MIN_VALUE) continue;
                // 출발 노드가 양수 사이클에 연결된 노드라면 종료 노드도 연결 노드로 업데이트
                else if (distance[start] == Long.MAX_VALUE) {
                    distance[end] = Long.MAX_VALUE;
                }
                // 더 많은 돈을 벌 수 있는 새로운 경로가 발견됐을 때 새로운 경로값 없데이트
                else if (distance[end] < distance[start] + cityMoney[end] - price) {
                    distance[end] = distance[start] + cityMoney[end] - price;
                    // N - 1 반복 이후 업데이트되는 종료 노드는 양수 사이클 연결 노드로 변경
                    if (i > N - 1) distance[end] = Long.MAX_VALUE;
                }
            }
        }
        // 도착 불가
        if(distance[eCity] == Long.MIN_VALUE) {
            System.out.println("gg");
        }
        // 양수 사이클 연결되어 무한일 때
        else if(distance[eCity] == Long.MAX_VALUE) {
            System.out.println("Gee");
        }
        // 그 외의 경우 출력
        else {
            System.out.println(distance[eCity]);
        }
        
    }
}

class Edge {
    int start, end, price;
    public Edge(int start, int end, int price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }
}