import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[] parent;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        tree = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        // 1. A의 인접 리스트에 그래프 데이터 저장하기
        for(int i = 0; i < N - 1; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            tree[s].add(e);
            tree[e].add(s);
        }
        depth = new int[N + 1];
        parent = new int[N + 1];
        visited = new boolean[N + 1];
        
        // 2. parent와 depth bfs를 통해 구하기
        bfs(1);
       
        // 3. LCA 수행
        int M = sc.nextInt(); // 질의의 수
        for(int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int LCA = excuteLCA(a, b);
            System.out.println(LCA);
        }
    }
    
    static int excuteLCA(int a, int b) {
        // 앞쪽에 depth가 더 높게 바꾸기
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        // depth 같게 맞추기
        while (depth[a] != depth[b]) {
            a = parent[a];
        }
        // depth 맞추고 같은 노드가 아니라면 하나씩 올려가며 같은 노드 찾기
        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
    // bfs 구현하기
    private static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        visited[node] = true;
        int level = 1;
        int now_size = 1;
        int count = 0;
        while(!queue.isEmpty()) {
            int now_node = queue.poll();
            for (int next : tree[now_node]) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    parent[next] = now_node; // 부모 노드 저장
                    depth[next] = level; // 노드 depth 저장
                }
            }
            // 확인한 자식 노드 수
            count++;
            // 자식 노드를 다 확인했다면
            if(count == now_size) {
                count = 0;
                // 다음 레벨 검사할 때 현재 자식노드의 수 변경
                // 현재의 자식 노드의 수가 다음 노드의 수!
                now_size = queue.size();
                level++;
            }
        }
    }
}