import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public int solution(int n, int[] lost, int[] reserve) {
		// 1. 여벌 가져온 학생이 도난일 수 있으니, 해당 학생은 여벌과 도난에서 제외
		// 2. lost와 reserve를 번호순 정렬
		// 3. lost의 학생의 번호와 reserve에 앞이나 뒤 번호가 있으면
		// 3-1. lost와 reserve에서 해당 학생 제외
		// 4. 못빌리는 녀석들만 리스트에 남겨두고, 전체 학생수에서 빼주면 정답

		List<Integer> lostList = Arrays.stream(lost).boxed().sorted().collect(Collectors.toList());
		List<Integer> reserveList = Arrays.stream(reserve).boxed().sorted().collect(Collectors.toList());

		// 못빌려주는 녀석들
		List<Integer> cantLend = new ArrayList<>();

		for (int i = 0; i < lostList.size(); i++) {
			for (int j = 0; j < reserveList.size(); j++) {
				if (lostList.get(i).equals(reserveList.get(j))) {
					cantLend.add(lostList.get(i));
					break;
				}
			}
		}
		// 빌려줄 수 없으니, 빌릴애들과 빌려주는 애들에서 제외시키기
		lostList.removeAll(cantLend);
		reserveList.removeAll(cantLend);

		// 빌릴 수 있느 녀석들
		List<Integer> landedPeople = new ArrayList<>();
		// 빌려준 녀석들
		List<Integer> landPerson = new ArrayList<>();

		for (int i = 0; i < lostList.size(); i++) {
			for (int j = 0; j < reserveList.size(); j++) {
				if (lostList.get(i).equals(reserveList.get(j) - 1) || lostList.get(i).equals(reserveList.get(j) + 1)) {
					//빌려준 이력이 없는 녀석이면
					if (!landPerson.contains(reserveList.get(j))) {
						// 빌린녀석에 추가
						landedPeople.add(lostList.get(i));
						// 밸려준 녀석에 추가
						landPerson.add(reserveList.get(j));
						break;
					}
				}
			}
		}
		// 빌렸으니 잃어비린 녀석에서 제거
		lostList.removeAll(landedPeople);

		return n - lostList.size();

	}
}