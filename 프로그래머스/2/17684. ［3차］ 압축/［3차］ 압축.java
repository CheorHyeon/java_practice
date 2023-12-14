import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(String msg) {
		// 1. 사전 초기화
		// 2. 현재 입력값과 일치하는 가장 긴 문자열 W 찾기
		// 3. W에 해당하는 색인번호 출력, 입력에서 W 제거
		// 4. 처리되지 않은 다음 글자 남아있다면(C), W+C를 사전에 등록

		// 대문자 변환
		msg = msg.toUpperCase();
		// 출력 리스트
		List<Integer> answer = new ArrayList<>();
		// 사전 리스트
		List<String> dictionary = new ArrayList<>();
		// 1. 사전 초기화
		initDictionary(dictionary);
		// 2. 현재 입력값과 일치하는 가장 긴 문자열 W 찾기
		for (int i = 0; i < msg.length(); i++) {
			// 가장 긴 문자열 W 찾기
			String W = msg.charAt(i) + "";
			StringBuilder word = new StringBuilder(W);
			int next = i;
			for (int j = i + 1; j < msg.length(); j++) {
				word.append(msg.charAt(j));
				// 포함되어있으면 계속 추가 및 갱신
				if (dictionary.contains(word.toString())) {
					W = word.toString();
					// 가장 마지막 단어의 알파벳 위치
					next = j;
				}
				// 다음 알파벳 추가한 문자가 없으면 종료
				else {
					break;
				}
			}
			// 3. W에 해당하는 색인번호 결과에서 넣기
			int index = findIndexByW(W, dictionary);
			answer.add(index);
			// 4. 처리되지 않은 다음 글자 남아있으면 W+C 사전 등록
			if (next < msg.length() - 1) {
				// 다음 글자 C 추가한 문자 추가
				dictionary.add(W + msg.charAt(next+1));
			}
			// 3. 입력에서 W 제거를 위해 인덱스 번호 조정
			// 여기 지나가면 +1 해주니깐, i=next로 해서 마지막 문자 다음부터 검사하도록
			i = next;
		}

		return answer.stream().mapToInt(Integer::intValue).toArray();
	}
	
	/*
		인덱스 찾기, 없으면 -1 반환
		하지만 없으면 호출 안되기 때문에 solution 메서드에서는 별도 조건문 x
	 */

	private int findIndexByW(String target, List<String> dictionary) {
		int result = -1;
		for (int i = 0; i < dictionary.size(); i++) {
			String word = dictionary.get(i);
			if (target.equals(word)) {
				result = i + 1;
				break;
			}
		}
		return result;
	}

	/*
		한 글자 알파벳 사전 초기화
	 */
	private void initDictionary(List<String> dictionary) {
		for (int i = 0; i < 26; i++) {
			String initTarget = String.valueOf((char)('A' + i));
			dictionary.add(initTarget);
		}
	}
}