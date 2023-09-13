import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(String[] operations) {
		// 1. 리스트를 활용한 큐 방식
		// 2. 명령어 실행하며 최소, 최대 삭제할 값을 최종 리스트에서 해당 값 제거
		// 3. 최종 리스트 검사
		List<Integer> resultList = new ArrayList<>();

		for (String operation : operations) {
			String[] operArr = operation.split(" ");

			if (operArr[0].equals("I")) {
				resultList.add(Integer.parseInt(operArr[1]));
				Collections.sort(resultList);
			} else {
				// 없으면 무시하기 위함
				if (resultList.size() > 0) {
					Integer delTarget = null;
					// 최솟값
					if (operArr[1].startsWith("-")) {
						delTarget = resultList.get(0);
					}
					// 최대값
					else {
						delTarget = resultList.get(resultList.size() - 1);
					}
					resultList.remove(delTarget);
				}
			}
		}

		int result[] = new int[2];

		if (resultList.size() > 0) {
			List<Integer> orderedResultList = resultList.stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
			result[0] = orderedResultList.get(0);
			result[1] = orderedResultList.get(resultList.size() - 1);
		}

		return result;
	}
}