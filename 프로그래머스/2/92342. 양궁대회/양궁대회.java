import java.util.Arrays;

class Solution {
	public int[] solution(int n, int[] info) {
		int[] answer = new int[11];
		int[] tmp = new int[11];
		int maxDiff = 0;

		// bit 연산 경우의 수, 라이언이 이기는 경우 1, 지는 경우 0
		for(int subSet = 1; subSet < (1 << 10); subSet++) {
			// cnt : 라이언이 사용한 화살의 수(n개까지만 사용 가능, 남는 화살 0점에 쏴버리기 용)
			int ryan = 0, apeach = 0, cnt = 0;
			for(int i = 0; i < 10; i++) {
				// 라이언이 해당 과녁 점수를 이기는 경우 -> 라이언이 점수를 가져가는 경우
				if((subSet & (1 << i)) != 0) {
					ryan += 10 - i; // 라이언 점수
					tmp[i] = info[i] + 1; // 어피치가 맞춘 화살보다 1개 더 맞추면 됨
					cnt += tmp[i];
				}
				// 비기거나 어피치가 해당 과녁 점수 이기는 경우
				else {
					// 라이언 해당 과녁 0번 쏜거 처리
					tmp[i] = 0;
					// 어피치가 해당 과녁 쏜 경우라면 이김 처리
					if(info[i] > 0) {
						apeach += 10 - i;
					}
				}
			}

			// 화살을 n번보다 더 쐈다면 무시
			if(cnt > n) continue;

			// 남은 화살만큼 0점에 쏘기 처리
			tmp[10] = n - cnt;

			// 점수 차 같은 경우가 있다면 낮은 점수에서 이기는 경우로 변경
			if(ryan - apeach == maxDiff) {
				for(int i = 10; i >= 0; i--) {
					if(tmp[i] > answer[i]) {
						maxDiff = ryan - apeach;
						answer = Arrays.copyOf(tmp, tmp.length);
						break;
					}
					// 기존의 경우가 낮은 점수 맞춘 경우가 크다면 변경 필요가 없음
					else if (tmp[i] < answer[i]) {
						break;
					}
				}
			}
			// 점수차가 더 크다면 라이언 이긴 경우로 정답을 갱신해준다.
			if(ryan - apeach > maxDiff) {
				maxDiff = ryan - apeach;
				answer = Arrays.copyOf(tmp, tmp.length);
			}
		}

		// 0인 경우면 어피치가 무조건 이기니 불가
		if(maxDiff == 0) {
			return new int[] {-1};
		}

		return answer;
	}
}