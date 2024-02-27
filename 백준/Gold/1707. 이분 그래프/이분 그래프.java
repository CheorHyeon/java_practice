import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] A;
    static int[] check;
    static boolean visited[];
    static boolean isEven;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int t = 0; t < N; t++) {
            String[] s = br.readLine().split(" ");
            int V = Integer.parseInt(s[0]);
            int E =Integer.parseInt(s[1]);
            A = new ArrayList[V+1];
            visited = new boolean[V+1];
            check = new int[V+1];
            isEven = true;
            for(int i = 1; i <= V; i++) {
                A[i] = new ArrayList<Integer>();
            }
            // 1. 인접리스트로 그래프 저장하기
            for(int i = 0; i < E; i++) {
                s = br.readLine().split(" ");
                int start = Integer.parseInt(s[0]);
                int end = Integer.parseInt(s[1]);
                A[start].add(end);
                A[end].add(start);
            }
            // 2. 모든 노드에서 dfs 실행(주어진 그래프가 1개로 연결된 보장이 없기 때문)
            for(int i = 1; i <= V; i++) {
                if (isEven)
                    dfs(i);
                else
                    break;
            }
            // 3. 이분그래프일 경우와 아닐 경우 출력
            if(isEven)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
    
    public static void dfs(int node) {
        visited[node] = true;
        for(int i : A[node]) {
            if(!visited[i]){
            // 집합 0, 1로 구분하여 저장하기
            check[i] = (check[node] + 1) % 2;
            dfs(i);
            }
            // 방문한 적이 있고 같은 그룹에 속한다면 사이클 발생한 것
            else if(check[node] == check[i]) {
                isEven = false;
                break;
            }
        }
    }
}