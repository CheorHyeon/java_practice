import java.util.Stack;

public class Solution {
	public int[] solution(int[] arr) {
		Stack<Integer> stack = new Stack<>();
		// 1. 중복되지 않게 스택에 넣기
		for (int i = 0; i < arr.length; i++) {
			// 스택의 마지막 들어온 숫자와 비교하여 다르면 넣기
			if (!stack.isEmpty()) {
				int peek = stack.peek();
				if (peek != arr[i]) {
					stack.push(arr[i]);
				}
			}
			// 최초 실행 : 스택 빈 경우
			else {
				stack.push(arr[i]);
			}
		}
		// 2. 스택에서 꺼내기
		int[] result = new int[stack.size()];
		int index = stack.size() - 1;
		while (!stack.isEmpty()) {
			// Stack은 LIFO 구조니 역순 저장
			result[index--] = stack.pop();
		}

		// 3. 반환
		return result;
	}
}