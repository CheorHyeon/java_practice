import java.util.Stack;

class Solution {
	public int solution(String s) {
		// 1. stack에 하나씩 넣고 다음에 넣을 문자가 꺼낼 문자와 같으면 꺼냄
		// 2. 최종적으로 stack의 size가 0이면 1반환

		Stack<Character> stack = new Stack<>();
		char[] tmp = s.toCharArray();

		for (int i = 0; i < tmp.length; i++) {
			char c = tmp[i];
			// 스택이 비워있다면 우선 넣기
			if (stack.isEmpty()) {
				stack.push(c);
			}
			// 비워있지 않다면 꺼낼 문자와 같으면 꺼내기(연속 문자)
			else {
				if (stack.peek() == c) {
					stack.pop();
				}
				// 다른 문자라면 스택에 넣기
				else {
					stack.push(c);
				}
			}
		}

		if (stack.isEmpty())
			return 1;
		return 0;
	}
}