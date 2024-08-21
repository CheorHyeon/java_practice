import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];

		// 1. 보석의 종류 저장 - 개수 파악 용
		Set<String> gemSet = Arrays.stream(gems).collect(Collectors.toSet());

		// 2. 보석의 종류, 개수를 저장하기 위한 map
		Map<String, Integer> gemMap = new HashMap<>();

		int gemCnt = gemSet.size();
		int len = gems.length;

		int distance = Integer.MAX_VALUE; // right - left 저장
		int left = 0;
		int right = 0;
		int start = 0;
		int end = 0;

		while (true) {
			// 전체 보석 수와 쓸어 담은 보석의 종류 수가 같다면 distance를 갱신하고 범위를 줄이기 위해 left 이동
			// 범위를 줄이다가 안되기 직전의 값이 최대로 하기 위해 맨 밑에 if문으로 추가 검사
			if(gemCnt == gemMap.size()) {
				String leftGem = gems[left];
				gemMap.put(leftGem, gemMap.get(leftGem) - 1);
				if(gemMap.get(leftGem) == 0) {
					gemMap.remove(leftGem);
				}
				left++;
			}
			// 끝까지 탐색범위 검사했으면 더이상 불가하므로 종료
			else if(right == len) break;
			// 쓸어담은 보석의 종류가 모든 종류의 보석을 담지 못한 경우 right 이동
			else {
				String rightGem = gems[right];
				gemMap.put(rightGem, gemMap.getOrDefault(rightGem, 0) + 1);
				right++;
			}
			// 범위를 바꿨을 때 조건 만족하면 답 갱신(최소값이니)
			if(gemCnt == gemMap.size()) {
				if(right - left < distance) {
					distance = right - left;
					start = left + 1; // 시작 수납장 1번부터니깐 1 증가한 숫자
					end = right;
				}
			}
		}

		answer[0] = start;
		answer[1] = end;
		return answer;
	}
}