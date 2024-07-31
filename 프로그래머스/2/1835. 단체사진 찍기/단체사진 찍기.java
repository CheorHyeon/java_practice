class Solution {
	int answer = 0;
	String[] data;
	boolean[] isStand;
	String[] friends = new String[] {"A", "C", "F", "J", "M", "N", "R", "T"};
	public int solution(int n, String[] data) {
		this.data = data;
		this.isStand = new boolean[8];
		// 1. dfs 탐색으로 모든 경우의수에 대한 탐색
		dfs("", 0);
		// 2. 결과 반환
		return answer;
	}

	public void dfs(String cur, int depth) {
		// 1. 종료 조건
		if(depth == 8) {
			// 주어진 조건에 맞는 경우의 수이면 정답 증가
			if(check(cur)) {
				answer++;
			}
			return;
		}

		// 2. 반복 내용
		for(int i = 0; i < 8; i++) {
			// 줄 서지 않았다면 세우고 다음 dfs
			if(!isStand[i]) {
				isStand[i] = true;
				dfs(cur+friends[i], depth + 1);
				isStand[i] = false;
			}
		}
	}

	private boolean check(String cur) {
		boolean isCorrect = true;
		for(String condition : data) {
			String firstFriend = condition.charAt(0) + "";
			String secondFriend = condition.charAt(2) + "";
			char sign = condition.charAt(3);
			Integer distanceCondition = Integer.parseInt(condition.charAt(4) + "");

			// 현재 줄선애들 조건 체크
			Integer firstPosition = cur.indexOf(firstFriend);
			Integer secondPosition = cur.indexOf(secondFriend);
			// 둘 사이 프렌즈 수
			Integer distance = Math.abs(firstPosition - secondPosition) - 1;
			// 조건 검사 : 아닌 경우 false
			switch (sign) {
				case '=' :
					if(!distance.equals(distanceCondition)) {
						isCorrect = false;
					}
					break;
				case '>' :
					if(!(distance > distanceCondition)) {
						isCorrect = false;
					}
					break;
				case '<' :
					if(!(distance < distanceCondition)) {
						isCorrect = false;
					}
					break;
			}
			// 조건 하나라도 만족안했으면 다음 조건 검사할 필요도 없이 그냥 불가
			if(!isCorrect) {
				return false;
			}
		}
		// 반복문에서 반환이 안됐으면 모든 조건 만족한 경우
		return true;
	}
}