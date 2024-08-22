class Solution {
	int[] stones;
	int k;
	public int solution(int[] stones, int k) {
		// 특정 징검다리 4명이 건넌다 했을 때 징검다리를 1번씩 밟아서 확인하는 것이 아닌 한번에 4를 빼보는 방식 도입
		int answer = 0;
		int min = 1, max = 200_000_000;
		
		this.stones = stones;
		this.k = k;

		while (min <= max) {
			int mid = (min + max) / 2;
			// 중간인원수 만큼이 건널 수 있으면 최대값 갱신
			// 탐색 범위 반으로 줄이기
			if(canCross(mid)) {
				answer = Math.max(answer, mid);
				min = mid + 1;
			}
			else {
				max = mid - 1;
			}
		}
		
		return answer;
	}

	private boolean canCross(int people) {
		int cantCrossStoneCount = 0;
		
		for(int stoneCnt : stones) {
			// 해당 징검다리 카운트가 사람 수 보다 적으면 적은 개수 증가
			if(stoneCnt - people < 0)
				cantCrossStoneCount++;
			// k개를 한번에 뛰어넘을 수도 있기 때문에 k개 전에 건널 수 있는 다리 만나면 못건너는 수 0으로 초기화
			else cantCrossStoneCount = 0;
			// k개까지 못 건넌다면 건널 수 없음
			if(k == cantCrossStoneCount) 
				return false;
		}
		return true;
	}
}