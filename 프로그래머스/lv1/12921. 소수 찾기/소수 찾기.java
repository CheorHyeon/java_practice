class Solution {
	public int solution(int n) {
		// 에레토스테네스의 채 이론으로 각 수마다의 limit을 구한다.
		// 2는 소수이므로 result를 1로 세팅
		// 3~n까지 2~limit 나눠보며 나눠 떨어지지 않는 경우 result를 증가시킨다.

		// 2는 소수
		int result = 1;
		
		for (int i = 3; i <= n; i++) {
			int limit = (int)Math.sqrt(i);
			boolean isDecimal = true;
			for (int j = 2; j <= limit; j++) {
				if (i % j == 0) {
					isDecimal = false;
					break;
				}
			}
			if (isDecimal) {
				result++;
			}
		}
		return result;
	}
}