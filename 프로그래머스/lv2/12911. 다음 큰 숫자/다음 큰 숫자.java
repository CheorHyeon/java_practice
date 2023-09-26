class Solution {
	public int solution(int n) {
		int answer = 0;
		int tmp = n;
		while (true) {
			tmp++;
			if (Integer.bitCount(n) == Integer.bitCount(tmp)) {
				answer = tmp;
				break;
			}
		}
		return answer;
	}
}