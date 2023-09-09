import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class Solution {
	public int solution(String[][] clothes) {
		// 1. 종류 : 이름 맵 생성
		// 2. N+1 * N+1 -1로 구해주기(잆거나 안입거나)
		Map<String, ArrayList<String>> map = new HashMap<>();
		// 1. 맵 생성
		for(String[] clothArr : clothes) {
				map.putIfAbsent(clothArr[1], new ArrayList<>());
				map.get(clothArr[1]).add(clothArr[0]);
		}

		// 2. 계산
		if(map.keySet().size() ==1) {
			Set<String> keys = map.keySet();
			// 어차피 키가 1개라서 바로 리스트 개수 반환
			for(String key : keys) {
				ArrayList<String> list = map.get(key);
				return list.size();
			}
		}

		int result = 1;
		for(Map.Entry<String, ArrayList<String>> a : map.entrySet()) {
			// 전체를 1개씩 입거나(n), 아에 안입는 경우 1
			result *= (a.getValue().size() + 1) ;
		}

		// 전체를 아에 안입는 경우 제외
		return --result;
	}
}