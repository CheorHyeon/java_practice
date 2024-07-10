import java.util.ArrayList;
import java.util.Collections;

class Solution {
	double discount[] = {0.1, 0.2, 0.3, 0.4};
	int totalPeople, totalEmoticon;
	double[] discountInfo ;
	int[][] users;
	int[] emotions;
	ArrayList<int[]> answerArrList = new ArrayList<>();
	public int[] solution(int[][] users, int[] emoticons) {
		// answerArrList.add(new int[] {0, 0});
		totalPeople = users.length;
		totalEmoticon = emoticons.length;
		this.discountInfo = new double[totalEmoticon];
		this.users = users;
		this.emotions = emoticons;

		// 모든 경우의 수 dfs 탐색
		dfs(0);
		// 정렬
		Collections.sort(answerArrList, (o1, o2) -> {
			// 첫번째 정렬 기준은 정기권 가입자 수 많은 경우의 수
			if(o1[0] == o2[0]) {
				// 두번째 정렬 기준은 이모티콘 수익 높은 순
				return o2[1] - o1[1];
			}
			return o2[0] - o1[0];
		});
		// 답 반환
		return answerArrList.get(0);
	}

	private void dfs(int depth) {
		// 종료 조건 : depth가 이모티콘 수와 같을때 -> 이모티콘 할인율을 지정 완료한 케이스
		if(depth == totalEmoticon) {
			int totalUsersMonthlyEmoticonTicket = 0; // 정기권 구입
			int totalUsersEmoticonSum = 0; // 이모티콘 수입 합

			for(int i = 0; i < totalPeople; i++) {
				int personalSum = 0;
				// 구입
				for(int j = 0; j < totalEmoticon; j++) {
					// 이모티콘 할인률이 사용자가 구입하고자 하는 할인률 이상 할인하면 구입 처리
					if(discountInfo[j] >= (users[i][0] / 100.0)) {
						personalSum += emotions[j] * (1.0 - discountInfo[j]);
					}
				}
				// 지정금액 초과한다면 다 취소하고 정기권 구입
				if(personalSum >= users[i][1]) {
					totalUsersMonthlyEmoticonTicket++;
				}
				else {
					totalUsersEmoticonSum += personalSum;
				}
			}
			answerArrList.add(new int[] {totalUsersMonthlyEmoticonTicket, totalUsersEmoticonSum});
		}
		// 수행 조건 : depth가 이모티콘 수와 같을때까지 재귀 호출 -> 이모티콘 할인률 모두 지정
		else {
			for(int i = 0; i < discount.length; i++) {
				discountInfo[depth] = discount[i];
				dfs(depth + 1);
			}
		}
	}
}