import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		// 장르 : 플레이 수 맵 생성
		// 장르 : 총 재생 수 맵 생성
		// 1. 장르 : 총 재생 수 맵을 keySet 정렬하여 가장 처음꺼
		// 1-1. 장르를 가져오고, 해당 배열 인덱스 값 정렬하여 2개 선출(1개라면 1개만)
		// 1-2. 재생 횟수가 같으면 고유 번호가 낮은 노래 먼저(인덱스)
		Map<String, ArrayList<Integer>> genreMap = new HashMap<>();
		Map<String, Integer> playMap = new HashMap<>();

		// 장르별 인덱스 저장, 총합 저장 맵 생성
		for (int i = 0; i < genres.length; i++) {
			String g = genres[i];
			genreMap.putIfAbsent(g, new ArrayList<>());
			genreMap.get(g).add(plays[i]);

			Integer playSum = playMap.getOrDefault(g, 0) + plays[i];
			playMap.put(g, playSum);
		}

		List<Map.Entry<String, Integer>> sortedList = playMap.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			.collect(Collectors.toList());

		List<Integer> resultList = new ArrayList<>();

		for (Map.Entry<String, Integer> entry : sortedList) {
			ArrayList<Integer> playCount = genreMap.get(entry.getKey());
			// 장르 내에서 많은 재생 횟수
			playCount.sort(Comparator.reverseOrder());
			// 장르 내에서 재생 횟수가 같다면 인덱스 적은 순으로 저장 리스트
			ArrayList<Integer> indexs = new ArrayList<>();

			for (int a : playCount) {
				// 2개가 아직 안됐으면 찾기
				for (int i = 0; i < plays.length; i++) {
					if (a == plays[i]) {
						// 없으면 추가하고 종료, 있으면 다음꺼 찾게
						if(!indexs.contains(i)) {
							indexs.add(i);
							// 같은게 2개 이상이라면 빠른 인덱스부터 추가되게
							break;
						}
					}
				}

				// 장르에 속한 곡이 1개라면 해당 인덱스 찾아서 넣고 바로 종료
				// 위에서 어차피 인덱스 찾았으므로 그냥 바로 종료 시켜도 됨
				if (playCount.size() == 1) {
					break;
				}

				// 2개 이상이 속해있다면, 인덱스가 2개를 찾으면 종료
				else if (indexs.size() == 2) {
					break;
				}
			}
			// 해당 장르를 결과에 넣기 -> 총 재생횟수 순으로 진행되니 순서대로 들어감
			resultList.addAll(indexs);
		}

		return resultList.stream().mapToInt(Integer::intValue).toArray();
	}
}
