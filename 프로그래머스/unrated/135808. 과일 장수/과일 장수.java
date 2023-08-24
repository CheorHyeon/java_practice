import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public int solution(int k, int m, int[] score) {
		// 1. score 내림 차순 정렬
		// 2. 앞에서 부터 m개씩 묶어서 리스트에 넣기
		// 3. m개가 되는 부분 리스트만 결과 리스트에 넣고, 이윤 계산 후 반환
		List<Integer> list = new ArrayList<>(Arrays.stream(score).boxed().collect(Collectors.toList()));

		list.sort(Collections.reverseOrder());

		List<ArrayList<Integer>> resultScore = new ArrayList<>();
		for(int i=0; i<list.size(); i= i+m) {
			ArrayList<Integer> tmp = new ArrayList<>();
			// 크기가 m인 배열 추출, 만일 m이 안되면 중간에 종료시키기.
			for(int j=i; j<i+m; j++) {
				if(j < list.size()) {
					tmp.add(list.get(j));
				}
				else break;
			}
			if(tmp.size() == m) {
				resultScore.add(tmp);
			}
		}

		int result = 0;

		for(ArrayList<Integer> box : resultScore) {
			int min = box.get(0);
			for(int i=0; i<box.size(); i++) {
				min = Math.min(min, box.get(i));
			}

			result += min * m;
		}

		return result;
	}
}