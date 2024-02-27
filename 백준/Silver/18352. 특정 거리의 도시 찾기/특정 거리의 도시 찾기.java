import java.util.*;
public class Main {
    static int visited[];
    static ArrayList<Integer>[] A;
    static int N, M, K, X;
    static List<Integer> answer;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        K = scan.nextInt();
        X = scan.nextInt();
        A = new ArrayList[N+1];
        answer = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Integer>();
        }
        // 1. 그래프 표시
        for(int i = 0; i < M; i++) {
            int S = scan.nextInt();
            int E = scan.nextInt();
            A[S].add(E);
        }
        // 2. 방문 배열 초기화
        visited = new int[N+1];
        for(int i = 0; i <= N; i++) {
            visited[i] = -1;
        }
        // 3. 시작점 기준 bfs 탐색
        bfs(X);
        // 4. 시작점부터 K만에 갈 수 있는 노드 추가
        for(int i = 0; i <= N; i++) {
            if(visited[i] == K){
                answer.add(i);
            }
        }
        // 5. 갈 수 있는곳이 없다면 -1 출력
        if(answer.isEmpty()) {
            System.out.println(-1);
        }
        // 5-1. 오름차순 정렬 후 출력
        else {
            Collections.sort(answer);
            for(int node : answer) {
                System.out.println(node);
            }
        }
    }
    
    private static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node]++;
        while(!queue.isEmpty()){
            int nowNode = queue.poll();
            for(int i  : A[nowNode]) {
                // 방문하지 않았다면 현재 노드에서 1칸 더 갈 수 있는 경로로 표시
                if(visited[i] == -1) {
                    visited[i] = visited[nowNode] + 1;
                    queue.add(i);
                }
            }
        }
    }
}