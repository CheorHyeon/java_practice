import java.util.Stack;

class Solution {
	Stack<Integer> assistance;
	// 트럭에 싣은 상자의 개수
	int answer = 0;
	// 택배 기사님이 원하는 상자 번호를 가져오기 위한 index
	int idx = 0;
	// 택배 기사님이 원하는 상자 순서
	int[] hopeOrder;

	public int solution(int[] order) {
		assistance = new Stack<>();
		hopeOrder = order;
		// 반복문은 컨베이어 벨트를 의미 (가장 높은 상자 번호는 order의 length)
		for (int box = 1; box <= hopeOrder.length; box++) {
			// 컨베이어 벨트에서 온 상자가 택배 기사님이 원하는 상자 번호가 아닌 경우
			if (hopeOrder[idx] != box) {
				// 보조 컨테이너에서 원하는 박스가 있다면 트럭에 넣기
				if (!assistance.isEmpty() && idx < hopeOrder.length && assistance.peek() == hopeOrder[idx]) {
					answer++;
					idx++;
					assistance.pop();
					additionalCheck();
				}
				// 보조 컨테이너에 원하는 박스가 없다면 보관하기 위해 보조 컨테이너에 넣기
				else {
					// 보조 컨베이어 벨트에 상자 추가
					assistance.add(box);
				}
			}
			// 컨베이어 벨트에서 온 상자가 택배 기사님이 원하는 상자 번호일 경우
			// 트럭에 싣고, 기사님이 원하는 박스 번호가 보조 컨테이너에서 꺼낼 수 있는지 Check
			else {
				idx++;
				answer++;
				additionalCheck();
			}
		}

		return answer;
	}

	/*
		택배 기사님이 희망하는 박스번호가 보조 컨베이어벨트에 있는지 반복하는 메서드
		여러개 연속으로 나올 수도 있으니 반복문으로 확인
	 */
	private void additionalCheck() {
		while (!assistance.isEmpty() && idx < hopeOrder.length && hopeOrder[idx] == assistance.peek()) {
			idx++;
			answer++;
			assistance.pop();
		}
	}
}