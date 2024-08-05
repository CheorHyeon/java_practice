class Solution {
	public int solution(int n, long l, long r) {
		int result = 0;

		for(long i = l - 1; i < r; i++) {
			if(isOne(i)) result++;
		}
		return result;
	}

	// 인덱스 x를 5로 나눌 수 없을 때까지 계속 나누면서 나머지가 2가 나오는지 확인
	private boolean isOne(long x) {
		while (x >= 5) {
			if(x % 5 == 2) return false;
			x /= 5;
		}
		return x != 2;
	}
}