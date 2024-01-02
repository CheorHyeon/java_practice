import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(int n) {
		if (n == 1) {
			return new int[] {1};
		}
		if (n == 2) {
			return new int[] {1, 2, 3};
		}
		int[][] arr = new int[n][n];

		// 시작 지점
		int x = -1, y = 0;
		int num = 1;

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i % 3 == 0) {
					x++;  // 세로 이동
				} else if (i % 3 == 1) {
					y++; // 가로 이동
				} else {
					x--;
					y--;
				}
				arr[x][y] = num++;
			}
		}
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				result.add(arr[i][j]);
			}
		}
		return result.stream()
			.mapToInt(Integer::intValue)
			.toArray();
	}
}