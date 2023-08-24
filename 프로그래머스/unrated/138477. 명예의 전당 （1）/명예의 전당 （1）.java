import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
	public int[] solution(int k, int[] score) {
		// 전체 가수 중 상위 k번째 이내 -> 명예의 전당
		// 시작 이후 초기 k일 까지는 모든 가수가 명예의 전당
		// k번째 순위가 빠지고 추가
		// 매일 발표된 명예의 전당의 최하위 점수
		// 1. 일차를 증가 시켜가며, 명예의 전당에 우선 넣는다.
		// 2. 명예의 전당을 내림 차순 정렬하고, 만일 사이즈가 k를 넘는다면, k+1을 버린다.
		// 3, k번째 사람의 점수를 발표 점수 리스트에 넣는다.
		// 4. 일차가 끝나면 반환한다.

		List<Integer> honors = new ArrayList<Integer>();
		int[] result = new int[score.length];

		for(int i=0; i<score.length; i++) {
			// 1. 일차 증가하며 명예의 전당 넣기
			honors.add(score[i]);
			// 2. 내림 차순 정렬 및 사이즈 조정
			Collections.sort(honors, Collections.reverseOrder());
			while (honors.size() > k) {
				honors.remove(honors.size()-1);
			}
			// 가장 꼴찌가 그날 발표 점수
			result[i] = honors.get(honors.size()-1);
		}
		
		return result;
	}
}