import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(String[] keymap, String[] targets) {
		// 타겟 "AABB" -> "A" 일때 keymap에서 다 돌면서 가장 작은 인덱스번호 찾기 -> 끝까지 반복
		// 1. target을 하나씩 꺼내고, 해당 타겟문자열에서 첫번째 문자를 만들 수 있는 키맵에서 가장 작은 인덱스의 합을 리스트 추가(없으면 -1로 종료)
		// 2. targets의 끝까지 돌고 난 뒤 결과 리스트 배열화 출력

		List<Integer> list = new ArrayList<>();

		for (String target : targets) {
			int count = 0;
			int min = -1;
			for (int i = 0; i < target.length(); i++) {
				String tmp = target.charAt(i) + "";
				// 키보드로 해당 문자를 만들 수 있느냐
				for (int j = 0; j < keymap.length; j++) {
					String keyBoard = keymap[j];
					int startMin = keyBoard.indexOf(tmp);
					if (min < 0 && startMin >= 0) {
						// 불가능 했는데 문자 생성이 가능하면 해당 카운트
						min = startMin;
					} else if (min >= 0 && startMin >= 0) {
						if (min > startMin) {
							min = startMin;
						}
					}
				}
				// 키보드를 다 돌았는데 못만드는 문자열 발견? 그러면 걍 못만드는것으로 나머지 문자 볼 필요 x
				if (min == -1) {
					count = min;
					break;
				} else {
					// i번째는 i+1번 누른것이니 1 더해주기
					count += min + 1;
					// 다음 조건을 위해 초기화
					min = -1;
				}
			}
			list.add(count);
		}

		return list.stream().mapToInt(Integer::intValue).toArray();
	}
}