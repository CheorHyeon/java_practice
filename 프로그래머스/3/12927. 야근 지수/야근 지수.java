import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 가장 큰 수를 계속 줄여서 합하는것이 정답
        // 야근 피로도 : 남은 작업량^2의 합이니 가장 큰 수를 최대한 줄이는 것이 좋다.
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        
        for(int i : works)
            pq.add(i);
        
        while(true) {
            if(n == 0) break;
            if(pq.isEmpty()) break;
            int value = pq.poll();
            n--;
            --value;
            if(value > 0) 
                pq.add(value);
        }
        
        long answer = 0;
        while(!pq.isEmpty()) {
            int value = pq.poll();
            answer += value * value;
        }
        
        return answer;
    }
}