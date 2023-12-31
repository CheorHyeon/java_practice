import java.util.Queue;
import java.util.LinkedList;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        // Queue로 변환
        for(int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            /*
              - add : 큐의 공간이 충분하지 않으면 예외 발생
              - offer : 큐가 가득 찼을 때 예외를 발생하지 않고 false를 반환
            */
            que1.offer(queue1[i]);
        }
        
        for(int i = 0; i < queue2.length; i++) {
            sum2 += queue2[i];
            que2.offer(queue2[i]);
        }
        
        // 두 큐에 나눠서 저장, 합이 같아야 함 => 무조건 짝수여야 함
        if((sum1 + sum2) % 2 !=0)
            return -1;
        
        int count = 0;
        while (sum1 != sum2) {
            count++;
            // que1의 합이 더 크니깐 que1에서 빼서 que2에 삽입
            if(sum1 > sum2) {
                int value = que1.poll();
                sum1 -=value;
                sum2 +=value;
                que2.offer(value);
            }
            else {
                int value = que2.poll();
                sum1 += value;
                sum2 -= value;
                que1.offer(value);
            }
            // 값이 안나오는 경우 2 : 큐가 제자리로 왔을때에도 계속 반복하기 때문
            // ex) [3, 2, 7, 2] [4, 6, 5, 1]을 옮긴다고 했을 때
            // 하나를 빼서 다른쪽 맨 뒤로 옮기는 등 하나의 큐로 볼 수 있음
            // 그러면 2n번 반복하면 다시 제자리로 오기 때문
            if(count > ((queue1.length + queue2.length) * 2)) return -1;
        }
        
        return count;
    }
}