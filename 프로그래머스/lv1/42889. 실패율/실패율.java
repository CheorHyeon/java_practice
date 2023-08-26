import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(int N, int[] stages) {
		// 실패율 : (도달했으나 아직 클리어x) / (도달한 플레이어 수)
		// 전체 스테이지 N, 멈춰있는 스테이지 번호 배열 stages
		// 실패율이 높은 스테이지 부터 내림차순
		// 1. stage에서 가장 작은 단계를 구한다.
		// 1-1. 만약 제일 작은 단계가 N하고 같으면, 해당 단계에서 전부 실패니까 제일 높은 실패율
		// -> 이 경우라면 N, 1~N 순서 반환
		// 2. 1~N 단계까지 각각 실패율을 구한다
		// 2-1. 1단계 실패 -> 1 / 전체 사용자 수, 2단계 실패 => 2 / 2단계 이상 도달한 사용자 ..
		// 3. 대소 비교 후 내림차순 정렬 반환

		// 1. stage에서 가장 작은 단계 구하기.
		int minStage = stages[0];
		for (int stage : stages) {
			if (minStage > stage) {
				minStage = stage;
			}
		}

		// 1-1. 제일 작은 단계가 전체 단계랑 같다 -> 해당 단계 전부 실패
		if (minStage == N) {
			int[] result = new int[N];
			result[0] = N;
			for (int i = 1; i < N; i++) {
				result[i] = i;
			}
			return result;
		}

		List<Double> fails = new ArrayList<>();
		// 2. 1~N 단계 실패율 각각 구하기
		for (int i = 1; i <= N; i++) {
			int stageCount = 0;
			int stageAllCount = 0;
			for (int j = 0; j < stages.length; j++) {
				if (stages[j] >= i) {
					stageAllCount++;
					if (stages[j] == i) {
						stageCount++;
					}
				}
			}

			// 스테이지에 도달한 유저가 없는 경우 0으로 정의(문제 조건)
			if(stageCount==0) {
				fails.add(0.0);
			}
			else {
				double failPercent = (double)stageCount / stageAllCount;
				fails.add(failPercent);
			}
		}

		List<Double> sortedList = fails.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());

		// 중복 제거한 오름 차순
		LinkedHashSet<Double> setList = new LinkedHashSet<>(sortedList);

		List<Integer> resultList = new ArrayList<>();
		// 값 같은 "인덱스(들) + 1" 값을 찾아서 정답에 하나씩 넣기
		// 실패율이 똑같으면 앞의 단계부터 나오니깐 고려 안해도 됨
		for (Double a : setList) {
			for (int i = 0; i < fails.size(); i++) {
				if (a.equals(fails.get(i))) {
					resultList.add(i + 1);
				}
			}
		}

		return resultList.stream()
			.mapToInt(Integer::intValue)
			.toArray();
	}
}