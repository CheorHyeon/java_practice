import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
	public int solution(int[] priorities, int location) {
		// 1. 우선순위큐에 우선순위 삽입
		// 2. 하나씩 꺼내면서 배열의 인덱스 확인, 해당 인덱스가 location이면 몇번만에 나오는지 확인
		int answer = 0;
		// Max Heap 구현체
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 정렬

		for (int priority : priorities) {
			pq.offer(priority);
		}

		while (!pq.isEmpty()) {
			// 최대값을 발견 -> 그 앞에것들은 다시 맨 뒤로가니깐 원래 맨 뒤 인덱스까지만 탐색
			// 맨 마지막까지 탐색이 끝나면 찾았던 최대값 앞에 것들 검사하기 위한 for문
			for (int i = 0; i < priorities.length; i++) {
				if (priorities[i] == pq.peek()) {
					// 현재 가장 높은 우선순위가 location에 해당하면
					if (i == location) {
						return ++answer;
					}
					// 현재 가장 높은 우선순위가 location에 해당하지 않으면
					pq.poll();
					answer++;
				}
			}
		}
		return answer;
	}
}