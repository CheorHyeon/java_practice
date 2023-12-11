class Solution {
	String change(int num, int base) {
		int quotient = num / base;
		int mod = num % base;
		if (quotient != 0) {
			return change(quotient, base) + String.valueOf(mod);
		} else {
			return String.valueOf(mod);
		}
	}

	boolean check(long num) {
		if (num == 2)
			return true;
		if (num == 1 || num % 2 == 0)
			return false;
		// 루트값 까지만 나눠보기
		for (long i = 3; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	public int solution(int n, int k) {
		// 1. 진법 변환(몫이 0이 될때까지 나누기 -> 나머지를 역순으로)
		String number = change(n, k);
		// 2. 0으로 구분해서 소수인지 확인
		String[] nums = number.split("0+");
		int answer = 0;
		for (String num : nums) {
			if (check(Long.valueOf(num))) {
				answer++;
			}
		}
		return answer;

	}

}