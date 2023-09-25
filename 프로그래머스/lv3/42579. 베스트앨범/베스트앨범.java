import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		// 장르 : 플레이 수 맵 생성
		// 장르 : 총 재생 수 맵 생성
		// 1. 장르 : 총 재생 수 맵을 ketSet 정렬하여 가장 처음 꺼
		// 1-1 장르를 가져오고, 해당 배열 인덱스 값 정렬하여 2개 선출(1개라면 1개만)
		// 1-2 재생 횟수가 같으면 고유 번호가 낮은 노래 먼저(인덱스)

		HashMap<String, ArrayList<Integer>> genMap = new HashMap<>();
		HashMap<String, Integer> playMap = new HashMap<>();
		for(int i=0; i< genres.length; i++) {
			String gen = genres[i];
			genMap.putIfAbsent(gen, new ArrayList<>());
			genMap.get(gen).add(plays[i]);

			Integer playSum = playMap.getOrDefault(gen, 0) + plays[i];
			playMap.put(gen, playSum);
		}

		List<Map.Entry<String, Integer>> sortedList = playMap.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			.collect(Collectors.toList());

		List<Integer> resultList = new ArrayList<>();

		for(Map.Entry<String, Integer> entry : sortedList) {
			ArrayList<Integer> playCount = genMap.get(entry.getKey());
			playCount.sort(Comparator.reverseOrder());
			ArrayList<Integer> indexs = new ArrayList<>();

			for(int a : playCount) {
				for(int i=0; i< plays.length; i++) {
					if(a == plays[i]) {
						if(!indexs.contains(i)){
							indexs.add(i);
							// 같은게 여러개 일 수 있으니 앞에꺼 먼저 넣고 종료
							break;
						}
					}
				}
				if(playCount.size() == 1) {
					break;
				}
				
				else if(indexs.size() == 2) {
					break;
				}
			}
			resultList.addAll(indexs);
		}
		
		return resultList.stream().mapToInt(Integer::intValue).toArray();
	}
}