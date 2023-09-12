import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        // 1. 스코빌을 min-heap에 넣기
        // 2. 제일 처음꺼 빠져나오는 값이 K 이상일때까지 반복
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int count =0;
        
        for(int s : scoville) {
            pq.add(s);
        }
        
        while(pq.peek() < K) {
            // 1개만 남았다 -> 최대한 돌았는데도 K가 못된 경우
            if(pq.size() <= 1) {
                return -1;
            }
            else {
                int minFirst = pq.poll();
                int minSec = pq.poll();
                
                // 음식 섞어서 넣기
                int mix = minFirst + minSec * 2;
                pq.add(mix);
                count++;
            }
        }
        
       return count;
    }
}