import java.util.*;
import java.io.*;

public class Main {
    public static ArrayList<Integer>[] A;
    public static boolean visited[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        A = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        // 인접리스트 초기화
        for(int i = 1; i < n + 1; i++) {
            A[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            // 양방향 엣지
            A[s].add(e);
            A[e].add(s);
        }
        int count = 0;
        for (int i = 1; i < n + 1; i++) {
            // 방문하지 않은 노드가 있다면 해당 노드부터는 새로운 그래프 시작점이니 count 증가
            if(!visited[i]) {
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }
    
    public static void DFS(int v) {
        // 방문한 적이 있으면 탐색 종료
        if(visited[v]) {
            return ;
        }
        visited[v] = true;
        for(int i : A[v]) {
            // 연결된 노드 중 방문하지 않은 노드만 탐색
            if(!visited[i]) {
                DFS(i);
            }
        }
    }
}