import java.util.Arrays;
import java.util.Stack;

class Solution {
	public int solution(String s) {
		// 1. 올바른 괄호 형태인지 확인
		// 2. 문자열 회전후 반복
		// 여는 괄호 나오면 stack에 넣기, 닫는 괄호 나오면 마지막 것이 같은쌍인지 확인
		int count = 0;

		for (int i = 0; i < s.length()-1; i++) {
			// 올바른 괄호 형태인지 확인
			if (checkCorrect(s)) {
				count++;
			}
			// 회전 시킨 s로 갱신
			s = circleLeftString(s);
		}

		return count;

	}

	private String circleLeftString(String s) {
		char[] sToCharArr = s.toCharArray();
		String transStr = String.valueOf(Arrays.copyOfRange(sToCharArr, 1, sToCharArr.length)) + sToCharArr[0];
		return transStr;
	}

	private boolean checkCorrect(String s) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char target = s.charAt(i);
			if (target == '(' || target == '{' || target == '[') {
				stack.push(target);
			} else {
				// 닫는 괄호인데 스택에 현재 아무것도 없다
				if (stack.isEmpty()) {
					return false;
				}
				char inStackChar = stack.peek();

				switch (target) {
					case ')' -> {
						if (inStackChar != '(') {
							return false;
						}
						stack.pop();
					}
					case '}' -> {
						if (inStackChar != '{') {
							return false;
						}
						stack.pop();
					}
					default -> {
						if (inStackChar != '[') {
							return false;
						}
						stack.pop();
					}
				}
			}
		}
        if(stack.size() !=0) {
			return false;
		}
		return true;
	}
}