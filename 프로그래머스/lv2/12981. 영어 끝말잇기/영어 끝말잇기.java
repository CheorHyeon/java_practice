import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
	public int[] solution(int n, String[] words) {
		// 1. 사람별 말한 단어 해시맵 저장 및 말한 단어 저장
		// 2-1. 해시맵 저장 하고, 이전 단어와 비교 => 마지막 글자와 첫글자 일치 안하면 종료
		// 2-2. 이전에 말했던 단어와 말한 단어 비교
		// 3. 없으면 0, 0 반환

		HashMap<Integer, List<String>> map = new HashMap<>();
		int[] result = new int[2];
		for (int i = 0; i < words.length; i++) {
			// 0~n-1번 사람 말하는 것 저장
			int person = i % n;
			map.putIfAbsent(person, new ArrayList<>());

			// 해당 사람이 말한 단어 저장
			String word = words[i];
			map.get(person).add(word);

			// 이전 사람이 말한 단어 추출
			if (i != 0) {
				String before = words[i - 1];
				// 첫 단어가 이전 단어의 마지막 알파벳과 다르다면 해당 사람이 잘못
				// 이미 말한 단어라면 해당 사람, 몇번째 말한건지 반환
				// 사람, 인덱스 번호 0번부터 시작하니 1 더해주기
				if (word.charAt(0) != before.charAt(before.length() - 1) || isBeforeSaying(words, i, word)) {
					result[0] = person + 1;
					result[1] = map.get(person).indexOf(word) + 1;
					break;
				}
			}

		}
		// 중복된 사람 없으면 0, 0 리턴
		return result;
	}

	private boolean isBeforeSaying(String[] words, int end, String word) {
		// 이전에 말한적 있는 단어
		for(int i=0; i<end; i++) {
			if(words[i].equals(word)) {
				return true;
			}
		}
		// 말한적 없음
		return false;
	}
}