import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public String solution(int[] food) {
		// i번째 음식이 2로 나눴을 때 0 초과 -> 배치 가능
		// 배치 가능하면 리스트에 인덱스 추가
		// 배열 다 돌았으면, 역순 정렬한 리스트 만들고
		// 물 추가하고 그 뒤에 추가하기

		List<Integer> result = new ArrayList<>();

		for (int i = 1; i < food.length; i++) {
			if (food[i] / 2 > 0) {
				for (int j = 0; j < food[i] / 2; j++) {
					result.add(i);
				}
			}
		}

		List<Integer> reverse = result.stream()
			.sorted(Collections.reverseOrder())
			.collect(Collectors.toList());
		
		// 물 추가
		result.add(0);
		// 역순 정렬 리스트 추가
		result.addAll(reverse);
		// String화
		return result.stream()
			.map(String::valueOf)
			.collect(Collectors.joining());
	}
}