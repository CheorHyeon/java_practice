import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        // 1. 게임 (이기거나 진)횟수가 N-1이면 등수 확정 가능 -> 모든 선수와 경기한 경우
        // 2. A가 B보다 실력 좋으면 항상 이김 -> B가 C를 이겼다면 -> A > B > C 이므로 A가 C를 이긴 결과 추가
        // N-1명의 선수에 대해 항상 이기거나 진다는 결론이 나있으면 순위가 결정된 것!
        int answer = 0;
        int[][] graph = new int[n+1][n+1];
        
       for(int[] edge : results) {
           graph[edge[0]][edge[1]] = 1; // 이김
           graph[edge[1]][edge[0]] = -1; // 짐
       }
        
        // 플로이드 와샬 알고리즘 : a -> b -> c 경로 < a -> c 라면 a -> b -> c 경로가 더 최적의 경로
        // 출발
        for(int i = 1; i <= n; i++){
            // 도착
            for(int j = 1; j <= n; j++) {
                // 거쳐감
                for(int k = 1; k <= n; k++) {
                    // i가 k를 이겼고, k가 j를 이겼다면 -> i가 j를 이김 / j는 i한테 짐
                  if(graph[i][k] == 1 && graph[k][j] == 1) {
                      graph[i][j] = 1;
                      graph[j][i] = -1;
                  }
                    
                // i가 k한테 지고, k가 j한테 진다면 -> i가 j한테 짐 / j는 i한테 이김
                  if(graph[i][k] == -1 && graph[k][j] == -1) {
                      graph[i][j] = -1;
                      graph[j][i] = 1;
                  }
                }
            }
        }
        for(int i = 1; i<= n; i++) {
            int game = 0;
            for(int j = 1; j <= n; j++) {
                // 승부가 결정되었다면
                if(graph[i][j] != 0){
                    game++;
                }
            }
            if(game == n-1){
                answer++;
            }
        }
        return answer;
    }
}