import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public String solution(String X, String Y) {
		// X, Y 전체 숫자의 개수를 저장하는 배열 생성
		// 배열의 숫자가 둘다 1 이상이면 해당 숫자 리스트에 추가
		// 오름차순 정렬하고 스트링 반환

		// "230", "2300" -> 2, 3, 0 -> 320
		List<Integer> list = new ArrayList<>();

		// 각 숫자 개수 저장 배열
		int[] numbersX = new int[10];
		int[] numbersY = new int[10];

		for (int i = 0; i < X.length(); i++) {
			int digit = Integer.parseInt(X.charAt(i) + "");
			numbersX[digit]++;
		}
		for (int i = 0; i < Y.length(); i++) {
			int digit = Integer.parseInt(Y.charAt(i) + "");
			numbersY[digit]++;
		}

		for (int i = 0; i < numbersX.length; i++) {
			// 동일한 숫자를 넣었으면, 다음 반복때 또 반복되면 추가 안되게 하기 위g
			// ex) X = 3403, Y = 13203 , X의 0번째 3 탐색할 때 Y에서 2개 검출 -> X의 마지막 3에서는 Y 3 검출 안되게
			while (numbersX[i] >= 1 && numbersY[i] >= 1) {
				list.add(i);
				numbersX[i]--;
				numbersY[i]--;
			}
		}

		if (list.size() == 0) {
			return "-1";
		}

		list = list.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());

		String s = list.stream().map(a -> a + "").collect(Collectors.joining());
		if (s.startsWith("0")) {
			return "0";
		}
		return s;
	}
}