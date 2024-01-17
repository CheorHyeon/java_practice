class Solution {
	public String solution(String p) {
		// 1. 입력이 빈 문자열인 경우 빈 문자열 반환
		if (p.isEmpty())
			return p;
		// 2. u와 v로 분리
		int left = 0;
		int right = 0;
		int index = 0;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			if (left == right) {
				index = i;
				break;
			}
		}
		String u = p.substring(0, index + 1);
		String v = p.substring(index + 1);

		// 3. 문자열 u가 올바른 괄호 문자열이라면 문자열 v에 대해 1단계부터 다시 수행
		// 3-1. 수행한 결과 문자열을 u에 이어 붙여 반환
		if (isCorrect(u)) {
			return u + solution(v);
		}

		// 4. 문자열 u가 올바른 괄호 문자열이 아니라면 아래 과정 수행
		// 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙임
		// 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙임
		// 4-3. ')'를 다시 붙임
		// 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어 뒤에 붙임
		return "(" + solution(v) + ")" + reverse(u);
	}

	boolean isCorrect(String str) {
		int left = 0;
		int right = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}
			// 닫는 괄호가 더 많아지면 올바른 괄호 문자열이 아님
			if (right > left) {
				return false;
			}
		}
		return true;
	}

	String reverse(String str) {
		StringBuilder sb = new StringBuilder();
		// 첫번째와 마지막 문자 제거
		for (int i = 1; i < str.length() - 1; i++) {
			// 나머지 문자열의 괄호 방향 뒤집기
			if (str.charAt(i) == '(') {
				sb.append(')');
			} else {
				sb.append('(');
			}
		}
		return sb.toString();
	}
}