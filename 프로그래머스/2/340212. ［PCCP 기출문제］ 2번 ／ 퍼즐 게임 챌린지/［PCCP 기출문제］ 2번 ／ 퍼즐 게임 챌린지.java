class Solution {
	int[] diffs;
	int[] times;
	long limit;

	public int solution(int[] diffs, int[] times, long limit) {
		this.diffs = diffs;
		this.times = times;
		this.limit = limit;

		long left = 1; // 0번째인 diffs[0] 값
		long right = limit;

		// 이진 탐색을 하며 만족하는 최소 수준 찾기
		while (left <= right) {
			long mid = (left + right) / 2;

			if (isImpossible(mid)) {
                // mid일때 불가하니 그 다음 수준으로 조정
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
        
        // mid 다음 가능한 수준 최소값
		return (int)left;
	}

	/*
		제한 시간 내 모든 퍼즐 클리어 불가능 한지 여부 반환
	 */
	private boolean isImpossible(long level) {
		// 첫번째 꺼는 난이도 1짜리라 클리어 시간 초기화
		long clearTime = times[0];

		// 나머지 순회하며 클리어 시간 추출
		for (int i = 1; i < diffs.length; i++) {
			if (diffs[i] > level) {
				clearTime += (times[i] + times[i - 1]) * (diffs[i] - level);
			}
			// 클리어 타임 한번 더 더해주는 로직은 공통 작업
			clearTime += times[i];
		}

		// limit 안에 클리어 했는지 확인
		if (clearTime <= limit)
			return false;

		return true;
	}
}