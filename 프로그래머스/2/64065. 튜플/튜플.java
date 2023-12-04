import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

class Solution {
	public int[] solution(String s) {
		s = s.replace("{", "");
		// 맨 뒤 }} 제거
		s = s.substring(0, s.length() - 2);
		// 각 튜플에 속한 집합 추출
		String[] eachSet = s.split("},");
		// 각 문자열 길이만큼 정렬
		Arrays.sort(eachSet, (s1, s2) -> s1.length() - s2.length());
		// 길이가 짧은 순 -> 앞에 나오는 숫자니 순서는 중요
		Set<Integer> resultTuple = new LinkedHashSet<>();
		for (String set : eachSet) {
			String[] setInNumbers = set.split(",");
			// 숫자 하나씩 set에 넣기
			for (String number : setInNumbers) {
				resultTuple.add(Integer.parseInt(number));
			}
		}

		return resultTuple.stream()
			.mapToInt(Integer::intValue)
			.toArray();
	}
}