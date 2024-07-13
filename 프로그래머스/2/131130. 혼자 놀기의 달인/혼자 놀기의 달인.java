import java.util.*;
class Solution {
    // 상자 그룹의 개수를 저장한 우선순위 큐
    // 나올 수 있는 그룹의 수를 구하고 가장 큰 두 그룹의 곱이 이 문제의 답.
    // 왜냐하면 어떤 임의의 카드를 선택할 시 가장 큰 두 그룹을 뽑는 경우의 수가 반드시 나옴
    PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
    int[] cards;
    boolean[] visited;
    public int solution(int[] cards) {
        
        int answer = 0;
        this.cards = cards;
        
        // cards 크기가 선택한 숫자 n
        int n = cards.length;
        visited = new boolean[n];
        
        // 열지 않은 상태라면
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, 0);
            }
        }
        
        if(queue.size() != 1) {
            answer = queue.poll() * queue.poll();
        }
        
        return answer;
    }
    
    void dfs(int num, int cnt) {
        // 종료 조건
        if(visited[num]) {
            // 그룹의 개수를 넣고 종료
            queue.add(cnt);
            return;
        }
        // 반복
        visited[num] = true;
        dfs(cards[num] - 1, cnt + 1);
    }
}