import java.util.Arrays;
import java.util.Collections;

class Solution {
	public int solution(String[] friends, String[] gifts) {
		// 1. 사전 정보 추출
		// 1-1. 그래프로 선물 준 개수 표시하기
		int[][] map = new int[friends.length][friends.length];
		for(String gift : gifts) {
			String[] s = gift.split(" ");
			String giver = s[0];
			String taker = s[1];
			int giverIndex = findFriendIndex(friends, giver);
			int takerIndex = findFriendIndex(friends, taker);
			map[giverIndex][takerIndex]++;
		}
		// 1-2. 선물지수 구하기
		int[][] giftCalTable = new int[friends.length][3];
		for(int i = 0; i < map.length; i++) {
			int giveSum = 0;
			int takerSum = 0;
			// 준것과 받은것 총합
			for(int j = 0; j < map[0].length; j++) {
				giveSum += map[i][j];
				takerSum += map[j][i];
			}
			giftCalTable[i][0] = giveSum;
			giftCalTable[i][1] = takerSum;
			giftCalTable[i][2] = giveSum - takerSum; // 선물지수
		}
		// 2. 각 친구별 선물 받는 횟수 저장
		int[] giftCount = new int[friends.length];
		boolean[][] visited = new boolean[friends.length][friends.length];
		for(int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[0].length; j++) {
				// 자기 자신한텐 못주니 경우의 수 고려 안하기
				if(i == j) {
					continue;
				}
				// 자기 자신과 아닌 다른 친구들끼리의 경우 & 고려한 경우의 수가 아닌 경우만
				if(!visited[i][j]) {
					// 조회 대상이 준경우와 받은 경우 추출
					int targetGiveCount = map[i][j];
					int targetTakeCount = map[j][i];
					// 비교
					// case1 : A가 더 많이 준 경우면 A가 다음달 받기
					if(targetGiveCount > targetTakeCount) {
						giftCount[i]++;
					}
					// case2 : B가 더 많이 준 경우면 B가 다음달 받기
					else if(targetGiveCount < targetTakeCount) {
						giftCount[j]++;
					}
					// 같거나 아에 안주고받았으면 선물 지수 비교
					else {
						int AGiftCount = giftCalTable[i][2];
						int BGiftCount = giftCalTable[j][2];
						// 같으면 아에 안주고받으니 대소만 비교
						if(AGiftCount > BGiftCount) {
							giftCount[i]++;
						}
						else if(AGiftCount < BGiftCount) {
							giftCount[j]++;
						}
					}
					// 방문처리
					visited[i][j] = true;
					visited[j][i] = true;
				}
				
			}
		}
		// 3. 2에서 구한 배열 최대값 반환
		return Arrays.stream(giftCount).max().getAsInt();
	}

	private int findFriendIndex(String[] friends, String friendName) {
		for(int i = 0; i < friends.length; i++) {
			String temp = friends[i];
			if(temp.equals(friendName)) {
				return i;
			}
		}
		// 친구 이름 없는 경우, 이 경우는 오지 않으니 임의의 값
		return -1;
	}
}