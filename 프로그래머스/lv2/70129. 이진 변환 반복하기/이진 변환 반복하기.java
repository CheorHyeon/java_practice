class Solution {
	public int[] solution(String s) {
		// 1. "0"이면 횟수만 증가, "1"이면 문자열 추가
		// 2. 생성한 문자열의 길이를 2진수로 변환
		// 3. 변환 횟수 증가 및 s가 1이 될 때까지 반복
		int zeroCount = 0;
		int transCount = 0;
		while (!s.equals("1")) {
			StringBuilder tmp = new StringBuilder();
			char[] c = s.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (c[i] == '0') {
					zeroCount++;
				} else
					tmp.append(c[i]);
			}

			int length = tmp.length();
			s = Integer.toBinaryString(length);
			transCount++;
		}

		int[] result = new int[2];
		result[0] = transCount;
		result[1] = zeroCount;
		return result;
	}
}