import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(int[] progresses, int[] speeds) {
		List<Integer> result = new ArrayList<>();

		for(int i=0; i< progresses.length; i++) {
			// 1. 한 개 기능을 개발하는데 필요한 날짜 계산
			double days = (100 - progresses[i]) / (double) speeds[i];
			int daysUp = (int) Math.ceil(days);
			// 2. 함께 배포할 기능의 index 찾기
			int j = i+1;
			for(; j < progresses.length; j++) {
				// 필요한 날짜만큼 개발했을 때 완료하지 못한 시점
				if( progresses[j] + daysUp * speeds[j] < 100) {
					break;
				}
			}
			// 3. 이번에 배포할 기능의 개수 추가하기
			// 개발 완료 못한 시점에서 개발 시작 시점 빼주면 사이의 개수
			result.add(j - i);
			// j - 1로 바꿨으니, 다음 for문에서 ++하니 j부터 시작함
			i = j-1;
		}
		return result.stream().mapToInt(Integer::intValue).toArray();
	}
}