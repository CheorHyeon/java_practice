import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
	public int solution(int[][] jobs) {
		// 작업을 기다리는 동안 힙에 넣음
		// 작업을 끝났을 때 남은 일의 시간이 작은 것 꺼냄


		// 3. 한 작업이 끝나는 시점에 우선순위큐에서 꺼내오기

		int answer = 0;
		int time = 0;
		int idx = 0;
		int len = jobs.length;

		// 작업의 소요시간 오름차순 저장 pq
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		// 1. jobs를 정렬(시작시간이 정렬되어 있지 않을 수 있음)
		Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

		while (!pq.isEmpty() || idx < len) {
			// 현재 시간보다 작거나 같은 요청을 큐에 추가
			// 현재 시점보다 이하에 있는 작업들 추가
			// idx를 계속 증가해도 되는 이유는 jobs 배열이 오름차순으로 정렬되어 있음
			while (idx < len && jobs[idx][0] <= time) {
				// 2. 빠른 시작하는 작업들 중 걸리는 시간이 작은 것 순으로 우선순위큐 삽입
				pq.add(jobs[idx++]);
			}
			// 큐에 작업이 없다면 (현재 시점 이전 시간에서 만나는 작업이 아에 없음)
			// 즉 처음 시작이 0초가 아니였던 것!
			if(pq.isEmpty()) {
				// 작업 요청시점이 가장 빠른 작업을 추가
				// 첫번째 작업을 만난 시점을 초기화
				time = jobs[idx][0];
			}
			// 큐에 작업이 있다면
			else {
				// 현재 시점보다 이하에 있는 작업들 중 
				// 작업 소요시간이 가장 빠른 작업 수행
				int[] job = pq.poll();
				time += job[1]; // 작업 끝났으니 작업 처리하기 시작하는 time에 작업시간 플러스
				answer += time - job[0]; // 끝난 시점 - 요청 시점
			}
		}

		return answer / len;
	}
}