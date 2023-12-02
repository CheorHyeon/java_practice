class Solution {
	public int[][] solution(int[][] arr1, int[][] arr2) {
		int[][] answer = new int[arr1.length][arr2[0].length];
		// 1번째 행 x 1번째열의 결과가 (1,1)
		// 1번째 행 x 2번째열의 결과가 (1, 2)
		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[i].length; j++) {
				for (int k = 0; k < arr1[0].length; k++) {
					// a[1][1] * b[1][1] + a[1][2] * b[2][1] / a[1][1] * b[1][2] + a[1][2] * b[2][2] 이런식으로 열, 행 부분이 증가하면서 더함
					answer[i][j] += arr1[i][k] * arr2[k][j];
				}
			}
		}
		return answer;
	}
}