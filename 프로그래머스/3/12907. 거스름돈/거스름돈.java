class Solution {

	int[][] dp;

	public int solution(int n, int[] money) {
		this.dp = new int[money.length + 1][n + 1];

		for (int i = 1; i <= money.length; i++) {
			for (int j = 0; j <= n; j++) {
				if (j == 0) {
					dp[i][j] = 1; // 맨 첫 열 1로 초기화
				}
				// 현재 고려할 동전이 고려할 금액보다 크다면 현재 동전을 0 ~ k번 사용한 경우의 수 고려
				// 0 ~ k-1번 사용한 경우의 수는 dp[i][j - money[i - 1]] 여기서 구했고, k번 사용했을 경우에는 이전 동전들로 amount 금액 고려한 경우
				else if (j - money[i - 1] >= 0) {
					dp[i][j] = (dp[i - 1][j] + dp[i][j - money[i - 1]]) % 1000000007;
				} else
					dp[i][j] = dp[i - 1][j];
			}
		}

		return dp[money.length][n];
	}
}