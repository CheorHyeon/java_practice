import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
	public int solution(int N, int number) {
		List<Set<Integer>> countList = new ArrayList<>();

		// 8번 까지만 검사하면 되니 8개의 셋 생성
		for (int i = 0; i < 9; i++)
			countList.add(new HashSet<>());

		// N이 1개 들어간 것은 N 자신뿐
		countList.get(1).add(N);

		for (int i = 2; i < 9; i++) {
			// 2개 ~ 8개 들어간 계산식 계산을 저장하는 셋
			Set<Integer> countSet = countList.get(i);

			// 이전 결괴에 1개짜리 연산검사
			for (int j = 1; j <= i; j++) {
				// 4개로 만든 숫자 : 1 + 3 / 2 + 2 / 3 + 1
                // 2개 1개 1개 -> 2개 1개가 3개로 만든 숫자에 이미 포함
				Set<Integer> preSet = countList.get(j);
				Set<Integer> postSet = countList.get(i - j);
				for (int preNum : preSet) {
					for (int postNum : postSet) {
						countSet.add(preNum + postNum);
						countSet.add(preNum - postNum);
						countSet.add(preNum * postNum);
						if (preNum != 0 && postNum != 0) {
							countSet.add(preNum / postNum);
						}

						countSet.add(postNum + preNum);
						countSet.add(postNum - preNum);
						countSet.add(postNum * preNum);

						if (preNum != 0) {
							countSet.add(postNum / preNum);
						}
					}
				}
				// 자기 자신 여러개 이루어진 숫자도 추가
				countSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));
			}
		}
		for (Set<Integer> sub : countList) {
			if (sub.contains(number))
				return countList.indexOf(sub);
		}
		return -1;
	}
}