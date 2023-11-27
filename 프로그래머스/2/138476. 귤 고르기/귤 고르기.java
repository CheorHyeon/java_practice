import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	public int solution(int k, int[] tangerine) {
		int solution =0;
		// 1. 무게 : 개수 Map 생성
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < tangerine.length; i++) {
			Integer count = map.getOrDefault(tangerine[i], 0);
			count++;
			// 개수 갱신
			map.put(tangerine[i], count);
		}
		// 2. 개수 많은 순 정렬
		List<Integer> keys = new ArrayList<>(map.keySet());
		Collections.sort(keys, (o1, o2) -> map.get(o2) - map.get(o1));
		
		// 3. k - 값 <=0 이면 종료, 참조한 값 개수 반환
		for(Integer key : keys) {
			k = k -map.get(key);
			solution++;
			
			if(k <=0)
				break;
		}
		return solution;
	}
}