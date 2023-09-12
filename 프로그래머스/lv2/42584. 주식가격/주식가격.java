import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(int[] prices) {
		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < prices.length; i++) {
			if (i == prices.length - 1) {
				result.add(0);
				break;
			}
			// 떨어진 경우 찾기
			int j = i + 1;
			for (; j < prices.length; j++) {
				if (prices[i] > prices[j]) {
					result.add(j - i);
					break;
				}
			}
			if (j == prices.length) {
				// 여기 오면 떨어지지 않은 경우
				result.add((j - 1) - i);
			}
		}

		return result.stream().mapToInt(Integer::intValue).toArray();

	}
}