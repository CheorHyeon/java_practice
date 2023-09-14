import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(int brown, int yellow) {
		// 1. 노란색의 개수로 가능한 약수의 조합을 구함
		// 2. 모든 약수 조합으로 사각형을 만들고, 문제의 조건(가로 >= 세로) 만족하면서 가장 괄호의 길이 긴것?

		// 가능한 약수의 쌍(가로 X 세로 형태)
		List<int[]> pairs = new ArrayList<>();
		// 에레토스테네스의 체 : 제곱근까지만 확인하면 약수 구하기 가능 
		for (int i = 1; i <= (int)Math.pow(yellow, 0.5); i++) {
			if (yellow % i == 0) {
				// 몫
				int mock = yellow / i;

				// 가로가 더 긴값으로 갱신
				int x = Math.max(i, mock);
				int y = Math.min(i, mock);
				int[] pair = new int[] {x, y};
				pairs.add(pair);
			}
		}

		int[] result = new int[2];

		for (int[] pair : pairs) {
			int x = pair[0];
			int y = pair[1];

			// 가로 길이가 더 긴것으로 갱신
			if (x > result[0]) {
				// 브라운 개수의 합이 만든 사각형과 같으면 갱신
				if ((x + 2) * (y + 2) == yellow + brown) {
					result[0] = x + 2;
					result[1] = y + 2;
				}
			}
		}

		return result;
	}
}