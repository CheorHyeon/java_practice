import java.util.*;
public class Main {
    static boolean visited[];
    static int[] distance;
    static ArrayList<Edge>[] A;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        A = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Edge>();
        }
        // 1. 인접리스트 그래프 저장(노드, 가중치)
        for(int i = 0; i < N; i++) {
            int S = scan.nextInt();
            while(true) {
                int E = scan.nextInt();
                if(E == -1) {
                    break;
                }
                int V = scan.nextInt();
                A[S].add(new Edge(E, V));
            }
        }
        
        distance = new int[N+1];
        visited = new boolean[N+1];
        bfs(1);
        int Max = 1;
        // 2. 1번 시작점에서 가장 먼 곳(지름 점 중 하나)을 최대값으로 설정
        for(int i = 2; i <= N; i++) {
            if(distance[Max] < distance[i]) {
                Max = i;
            }
        }
        // 거리 및 방문여부 초기화(탐색 점 변경)
        distance = new int[N+1];
        visited = new boolean[N+1];
        // 3. 최대로 먼 곳(나머지 지름 점 까지의 거리 반환)
        bfs(Max);
        Arrays.sort(distance);
        System.out.println(distance[N]);
    }
    
    private static void bfs(int index) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        visited[index] = true;
        while(!queue.isEmpty()) {
            int node = queue.poll();
            for(Edge i : A[node]) {
                int e = i.e;
                int v = i.value;
                if(!visited[e]) {
                    visited[e] = true;
                    distance[e] = distance[node] + v; // 거리 업로드 : node에서 이동한 것이니 node거리 +
                    queue.add(e);
                }
            }
        }
    }
}
    
class Edge { 
    int e;
    int value;
    public Edge(int e, int value) {
        this.e = e;
        this.value = value;
    }
}