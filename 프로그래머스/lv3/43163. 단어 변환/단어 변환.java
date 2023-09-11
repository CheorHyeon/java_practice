import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {

	public boolean canConvert(String start, String end) {
		int diffCount = 0;
		// 모든 단어의 길이가 같다고 했기에, 길이 비교 필요 없음.
		for (int i = 0; i < start.length(); i++) {
			if (start.charAt(i) != end.charAt(i)) {
				diffCount++;
			}
		}

		return diffCount == 1;
	}

	public int solution(String begin, String target, String[] words) {
		// 1. target이 words에 없으면 불가, 있으면 진행
		// 2. 변환 가능한 단어들을 바꿔가며 도달여부 검사(bfs)

		int answer = 0;

		List<String> wordsList = Arrays.stream(words).collect(Collectors.toList());

		// 1. target 없으면 종료
		if (!wordsList.contains(target)) {
			return 0;
		}

		Queue<String> queue = new LinkedList<>();
		queue.add(begin);

		while (!queue.isEmpty()) {
			// 만일 다음 단어로 갈 수 있는게 2개 이상이라면, answer++로만 하면 값이 2개가 증가
			// 다음 단어로 갈 수 있는 것 전체를 1개만 증가 시킴 -> 단계로 묶음
			for (int q = 0; q < queue.size(); q++) {
				String cur = queue.poll();

				// 타겟과 같으면 횟수 반환
				if (cur.equals(target)) {
					return answer;
				}
				
				List<String> removeList = new ArrayList<>();

				for (String tmp : wordsList) {
					if (canConvert(cur, tmp)) {
						queue.add(tmp);
						removeList.add(tmp);
					}
				}
				
				wordsList.removeAll(removeList);
			}
			answer++;
		}
		// 목표 단어로 도달할 수 없을 경우엔 0 출력
		return 0;
	}
}