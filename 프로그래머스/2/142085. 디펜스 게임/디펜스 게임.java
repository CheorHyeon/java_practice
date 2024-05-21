import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
	public int solution(int n, int k, int[] enemy) {
		// 최대 병력 수로 유지시켜줄 PQ 정의
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		int roundIndex = 1;
		for (int round : enemy) {
			pq.add(round);
			n -= round;

			// 병력이 음수된 경우 찬스권 사용
			if (n < 0) {
				while (!pq.isEmpty()) {
					if (k == 0) {
						break;
					}
					// 찬스권을 싸운 녀석들 중에 가장 많은 녀석일 때 사용해야 이득이니
					// 해당 라운드의 병력 수만큼 병사를 더해준다.
					n += pq.poll();
					k--;
					// 0명 이상되면 찬스 사용 멈추기
					if (n >= 0) {
						break;
					}
				}
			}

			// 라운드 종료 전에 찬스 다 쓰고 병력 없다면 현재 라운드 - 1로 통과 라운드 반환
			if (n < 0) {
				return roundIndex - 1;
			}
			
			// 다음 라운드
			roundIndex++;
		}

		// 전체 통과 시 전체 라운드 반환
		return enemy.length;
	}
}