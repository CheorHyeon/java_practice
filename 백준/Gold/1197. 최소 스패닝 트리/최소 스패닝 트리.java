import java.util.*;

public class Main {
    static int[] parent;
    static PriorityQueue<pEdge> queue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        queue = new PriorityQueue<>();
        parent = new int[N+1];
        // 1. 대표 노드 저장 배열 초기화
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        // 2. 엣지 그래프 우선순위 큐 저장하기 -> 가중치 정렬 자동화 위함
        for(int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int v = sc.nextInt();
            queue.add(new pEdge(s, e, v));
        }
        int useEdge = 0;
        int result = 0;
        // 3. 모든 노드 연결을 위한 N - 1개의 엣지 사용
        while (useEdge < N - 1) {
            pEdge now = queue.poll();
            // 사이클이 생기지 않을 때만 연결처리 위함
            // 조건에 맞지 않으면 useEdge 증가하지 않아 연결처리 안한것
            if(find(now.s) != find(now.e)) {
                union(now.s, now.e);
                result = result + now.v;
                useEdge++;
            }
        }
        // 4. 결과 가중치 출력
        System.out.println(result);
    }
    
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b) {
            parent[b] = a;
        }
    }
    
    public static int find(int a) {
        if(a == parent[a]) {
            return a;
        }
        else {
            return parent[a] = find(parent[a]);
        }
    }
}

class pEdge implements Comparable<pEdge> {
    int s;
    int e;
    int v;
    pEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }
    
    @Override
    public int compareTo(pEdge o) {
        return this.v - o.v;
    }
}