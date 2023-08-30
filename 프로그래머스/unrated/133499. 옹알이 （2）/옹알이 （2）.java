class Solution {
	public int solution(String[] babbling) {
		// 발음할 수 있는 단어를 ""로 변경, 연속된 문자는 발음 못하니 " "
		// 연속되지 않은 단어부터 변경하면 이미 바꿔버리니깐 연속 단어부터 변경
		// 단어가 ""면 +1
		int answer = 0;

		for (int i = 0; i < babbling.length; i++) {
			String s = babbling[i];
			s = s.replaceAll("ayaaya|yeye|woowoo|mama", " ");
			s = s.replaceAll("aya|ye|woo|ma", "");
			if (s.equals("")) {
				answer++;
			}
		}

		return answer;
	}
}