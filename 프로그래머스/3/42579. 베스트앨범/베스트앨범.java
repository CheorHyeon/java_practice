import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		// 1. Map 생성
		HashMap<String, Integer> playCountMap = new HashMap<>();
		HashMap<String, List<Integer>> genrePlayIndexMap = new HashMap<>();
		for (int i = 0; i < genres.length; i++) {
			String genre = genres[i];
			// 장르별 음악 재생 횟수 합 저장
			playCountMap.put(genre, playCountMap.getOrDefault(genre, 0) + plays[i]);
			// 장르별 음악 인덱스 저장하기
			List<Integer> genrePlayCountList = genrePlayIndexMap.getOrDefault(genre, new ArrayList<>());
			genrePlayCountList.add(i);
			genrePlayIndexMap.put(genre, genrePlayCountList);
		}

		// 2. 재생 많이된 장르 먼저 수록
		List<Map.Entry<String, Integer>> entrySetSortedList = playCountMap.entrySet()
			.stream()
			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
			.collect(Collectors.toList());

		// 3. 정답 찾기
		List<Integer> result = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : entrySetSortedList) {
			List<Integer> list = genrePlayIndexMap.get(entry.getKey());
			// 해당 장르 음악이 1개라면 그냥 해당 인덱스 추가
			if (list.size() == 1) {
				result.add(list.get(0));
				continue;
			}
			// 음악 2개 이상이라면 재생 횟수가 많은 순으로 정렬 후 삽입, 같을 경우 인덱스 낮은 순
			List<Integer> sortedList = list.stream()
				.sorted((o1, o2) -> {
						int play1 = plays[o1];
						int play2 = plays[o2];
						if (play1 == play2) {
							// 재생 횟수가 같은 경우, 인덱스가 낮은 노래가 먼저 오도록 변경
							return o1 - o2;
						} else if (play1 < play2) {
							// 양수 반환 : 첫 번째 인자가 두번째 인자보다 큼(왼 > 오)
							// 항상 큰것이 뒤로 가게 정렬되기 때문에 왼쪽꺼가 뒤로 가짐
							// Comparator : 양수일 경우 첫번째가 뒤에 나옴
							return 1;
						}
						// play1 > play2
						else {
							// 음수 반환 : 첫 번째 인자가 두번째 인자보다 작음(왼 < 오)
							// 첫번째 인자가 항상 작으니 첫번째꺼가 앞에옴
							// Comparator : 음수일 경우 첫번째가 앞에 나옴
							return -1;
						}
					}
				)
				.collect(Collectors.toList());

			for (int idx = 0; idx < sortedList.size(); idx++) {
				// 2개 까지만 넣기
				if (idx >= 2) {
					break;
				}
				result.add(sortedList.get(idx));
			}
		}

		// 4. 반환
		return result.stream()
			.mapToInt(Integer::intValue)
			.toArray();

	}
}