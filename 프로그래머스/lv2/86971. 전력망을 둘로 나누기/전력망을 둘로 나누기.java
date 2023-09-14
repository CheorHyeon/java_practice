import java.util.LinkedList;
import java.util.Queue;
class Solution {
    int[][] arr;
    public int solution(int n, int[][] wires) {
        int answer = n;
        arr = new int[n+1][n+1];
        
        // 1. 인접행렬로 그래프 표현
        for(int i=0; i<wires.length; i++) {
            arr[wires[i][0]][wires[i][1]] = 1;
            arr[wires[i][1]][wires[i][0]] = 1;
        }
        
        // 2. 선을 하나씩 끊어보며 순회
        int a, b;
        for(int i=0; i<wires.length; i++) {
            // 연결 정보 가져옴
            a = wires[i][0];
            b = wires[i][1];
            
            // 선을 하나 끊음
            arr[a][b] = 0;
            arr[b][a] = 0;
            
            // bfs
            answer = Math.min(answer, bfs(n,a));
            
            // 선을 다시 복구
            arr[a][b] = 1;
            arr[b][a] = 1;            
        }
        
        return answer;
    }
    
    int bfs(int n, int start) {
        boolean[] visit = new boolean[n+1];
        int cnt = 1;
        
        Queue<Integer> queue = new LinkedList<>();
        // 연결 끊은 지점부터 연결된 노드 수 탐색
        queue.offer(start);
        
        while(!queue.isEmpty()) {
            int point = queue.poll();
            visit[point] = true;
            
        // 시작점과 연결된 노드에 방문한 적이 없고 연결을 발견하면 큐에 추가 및 개수 증가
            for(int i=1; i<=n; i++) {
                if(!visit[i]&& arr[point][i] == 1) {
                    queue.offer(i);
                    cnt++;
                }
            }
        }
        
        return (int)Math.abs(cnt - (n - cnt));
        
    }
}