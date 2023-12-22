import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        // 철수가 가지고 있는 토핑 : 조각 수 Map
        Map<Integer, Integer> map_1 = new HashMap<>();
        // 동생이 가지고 있는 토핑 : 조각 수 Map
        Map<Integer, Integer> map_2 = new HashMap<>();
        
        // 1. 동생에게 몰아주기
        for (int e : topping) {
            map_2.put(e, map_2.getOrDefault(e, 0) + 1);
        }
        
        for (int e : topping) {
            // 2.동생이 철수에게 하나씩 주면서 동등하게 나눴는지 비교
            // 하나 줌(증가)
            map_1.put(e, map_1.getOrDefault(e, 0) + 1);
            // 동생이 가진 해당 토핑 조각 개수 조정
            int broRollCount = map_2.get(e);
            map_2.put(e, --broRollCount);
            // 철수에게 주고 나서 토핑 조각이 0개가 됐다면 롤케이크 조각 없음 
            // -> 맵에서 제거
            if(broRollCount == 0) {
                map_2.remove(e);
            }
            
            // 3. 철수와 동생의 가진 토핑 개수를 비교하여 같으면 정답
            // 같은 갯수의 토핑을 가지고 있다면 동등하게 나눈 것
            if(map_1.size() == map_2.size()) {
                answer++;
            }
        }
        return answer;
    }
}