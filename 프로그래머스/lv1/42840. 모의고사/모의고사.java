import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(int[] answers) {
		// A : 6번째 부터 반복 정답, B : 9번째 부터 반복 정답, C : 11번째부터 반복 정답
		// answers를 다 돌면서 정답과 학생들이 찍은 값(인덱스%) 비교해서 같으면 증가
		// 1. 찍은 정답 배열 선언
		int[] A = new int[]{1, 2, 3, 4, 5};
		int[] B = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
		int[] C = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
		// 2. 맞춘 개수 저장
		int countA = 0;
		int countB = 0;
		int countC = 0;

		for(int i=0; i <answers.length; i++) {
			// A학생 정답일 경우
			if(answers[i] == A[i%5]) {
				countA++;
			}
			// B학생 정답일 경우
			if(answers[i] == B[i%8]) {
				countB++;
			}
			// C학생 정답일 경우
			if(answers[i] == C[i%10]) {
				countC++;
			}
		}

		List<Integer> list = new ArrayList<>();
		list.add(countA);
		list.add(countB);
		list.add(countC);

		Integer max = Collections.max(list);

		// 최대로 맞춘 사람의 인덱스 리스트
		List<Integer> indexList = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(max)) {
				indexList.add(i);
			}
		}

		int[] result = null;
		// 최대 정답자가 2명 이상일 때
		if(indexList.size() >1) {
			List<Integer> resultList = new ArrayList<>();
			for(int i=0; i<indexList.size(); i++){
				resultList.add(indexList.get(i) + 1);
			}
			result = resultList.stream().mapToInt(Integer::intValue).toArray();
		}
		// 최대 정답자 1명일 때
		else {
			result = new int[]{indexList.get(0) + 1} ;
		}
		return result;
	}
}