class Solution {
	int giveLen, takeLen;

	int update(int[] list, int len, int cap) {
		while (len > 0) {
			// cap 이 0이 되면 자연스레 종료, 0을 빼줘도 어차피 값 변동 없음
			// 수거의 경우 일부 수거
			if(cap < list[len - 1]) {
				list[len - 1] -= cap;
				break;
			}
			// cap >= list[len - 1] : 완전히 배달할 수 있음
			// 수거의 경우 완전히 수거
			else {
				cap -= list[len - 1];
				len--;
			}
		}
		return len;
	}
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		giveLen = deliveries.length;
		takeLen = pickups.length;

		while (giveLen > 0 || takeLen > 0) {
			// 맨 끝집이 0이면 배달갈 필요 없음 -> 배달 갈 가장 먼 집 찾기
			while (giveLen > 0 && deliveries[giveLen - 1] == 0) {
				giveLen--;
			}
			while (takeLen > 0 && pickups[takeLen - 1] == 0) {
				takeLen--;
			}

			answer += Math.max(giveLen, takeLen) * 2;
			giveLen = update(deliveries, giveLen, cap);
			takeLen = update(pickups, takeLen, cap);
		}
		return answer;
	}
}