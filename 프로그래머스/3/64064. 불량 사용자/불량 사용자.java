import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Solution {
	HashSet<List<String>> resultSet = new HashSet<>();

	public int solution(String[] user_id, String[] banned_id) {
		List<List<String>> list = new ArrayList<>();
		// 1. 각 banned 패턴에 맞아 떨어지는 user list 구하기
		for (String bannedPattern : banned_id) {
			List<String> patternMatchedList = new ArrayList<>();

			// 사용자 id가 해당 패턴과 맞는지 전수조사
			for (String user : user_id) {
				// 패턴 길이랑 사용자 id 다르면 해당 id는 패턴에 부합하지 않음
				if (user.length() != bannedPattern.length()) {
					continue;
				}
				boolean isMatched = true;
				for (int j = 0; j < user.length(); j++) {
					// 해당 자리수의 문자가 *이 아닌 문자로 다르면 패턴 불일치
					if (bannedPattern.charAt(j) != user.charAt(j)) {
						if (bannedPattern.charAt(j) != '*') {
							isMatched = false;
							break;
						}
					}
				}
				if (isMatched) {
					patternMatchedList.add(user);
				}
			}
			// 조건 만족하는 사람 있는 패턴 부합하는 사람들 목록에 넣기
			if (!patternMatchedList.isEmpty()) {
				list.add(patternMatchedList);
			}
		}

		// 2. 가능한 조합 찾기
		findCombinations(list, new ArrayList<>(), 0);
		return resultSet.size();
	}

	private void findCombinations(List<List<String>> list, List<String> currentList, int index) {
		// 종료 조건 : 현재 인덱스가 리스트 size와 같다면 끝에있는 인덱스까지 도달 => 조건 만족
		if (index == list.size()) {
			Collections.sort(currentList);
			resultSet.add(currentList);
			return;
		}

		// 반복
		for (String user : list.get(index)) {
			// 중복이 안되는 경우만 넣기 : 안되는 경우의 수 사전 차단
			if (!currentList.contains(user)) {
				currentList.add(user);
				findCombinations(list, currentList, index + 1);
				currentList.remove(user);
			}
		}
	}
}