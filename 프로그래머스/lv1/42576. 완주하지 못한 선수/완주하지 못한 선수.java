import java.util.HashMap;

class Solution {
	public String solution(String[] participant, String[] completion) {
		// 1. 참가자 : 인원 해시
		// 2. 완주자 value를 1개씩 제외 -> value가 1인거 반환

		String answer = "";
		HashMap<String, Integer> map = new HashMap<>();

		// 참가자 : 인원 해시화
		for (String tmp : participant) {
			map.put(tmp, map.getOrDefault(tmp, 0) + 1);
		}

		// 완주자 한명씩 제외
		for (String com : completion) {
			map.put(com, map.get(com) - 1);
		}

		for (String tmp : map.keySet()) {
			if (map.get(tmp) == 1) {
				answer = tmp;
			}
		}

		return answer;
	}
}