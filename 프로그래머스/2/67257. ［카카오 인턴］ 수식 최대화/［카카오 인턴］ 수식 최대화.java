import java.util.ArrayList;

class Solution {
	public long solution(String expression) {
		long answer = Long.MIN_VALUE;
		String[][] op = {{"+", "-", "*"}, {"+", "*", "-"}, {"-", "*", "+"}, {"-", "*", "+"}, {"-", "+", "*"},
			{"*", "-", "+"}, {"*", "+", "-"}};
		ArrayList<String> list = new ArrayList<>();
		int start = 0;
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '-' || expression.charAt(i) == '+' || expression.charAt(i) == '*') {
				list.add(expression.substring(start, i)); // 연산자 앞 숫자 추가
				list.add(expression.charAt(i) + ""); // 연산자 추가
				start = i + 1; // 시작 인덱스 조정
			}
		}
		list.add(expression.substring(start)); // 마지막 숫자 추가

		for (int i = 0; i < op.length; i++) {
			ArrayList<String> sub_list = new ArrayList<>(list);
			for (int k = 0; k < 3; k++) {
				for (int j = 0; j < sub_list.size(); j++) {
					// 연산자를 나타내고, 우선순위와 부합한다면
					if (op[i][k].equals(sub_list.get(j))) {
						// List의 j-1번째 인덱스를 2번째 인자값으로 바꿔주는 내부 메서드
						sub_list.set(j - 1, calc(sub_list.get(j - 1), sub_list.get(j), sub_list.get(j + 1)));
						// 기존 j번째는 연산자였으니, 계산 완료한 연산자와 그 뒷순자를 제거
						sub_list.remove(j);
						sub_list.remove(j);
						// 기존 연산자 위치였으니 그 앞숫자로 이동(set 메서드로 연산 결과가 바껴있음)
						j--;
					}
				}
			}
			answer = Math.max(answer, Math.abs(Long.parseLong(sub_list.get(0))));
		}
		return answer;
	}

	private String calc(String num1, String op, String num2) {
		long n1 = Long.parseLong(num1);
		long n2 = Long.parseLong(num2);
		switch (op) {
			case "+" -> {
				return n1 + n2 + "";
			}
			case "*" -> {
				return n1 * n2 + "";
			}
			default -> {
				return n1 - n2 + "";
			}
		}
	}
}