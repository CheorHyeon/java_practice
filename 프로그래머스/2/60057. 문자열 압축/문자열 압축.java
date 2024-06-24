class Solution {
	int min = 0;
	String origin;

	public int solution(String s) {
		origin = s;
		min = s.length();

		// 1. 문자열 나눠 최소 결과 찾기
		findMinZip();
		// 2. 반환
		return min;
	}

	private void findMinZip() {
		// 1. 1글자, 2글자, .. 압축 증가
		// 절반 길이까지만 보면됨 -> 0번 인덱스부터 시작하니깐 절반까지만 압축 가능
		for (int i = 1; i <= origin.length() / 2; i++) {
			StringBuilder sb = new StringBuilder();
			int cnt = 1;
			String cur = origin.substring(0, i);

			for (int j = i; j < origin.length(); j = j + i) {
				int end = Math.min(j + i, origin.length());
				String next = origin.substring(j, end);

				if (next.equals(cur)) {
					cnt++;
				} else {
					// 여기 온것은 같지 않다는 것
					if (cnt != 1) {
						sb.append(cnt + cur);
					} else {
						sb.append(cur);
					}
					cnt = 1;
					cur = next;
				}
			}

			// 마지막꺼 추가 해주기
			if (cnt != 1) {
				sb.append(cnt + cur);
			} else {
				sb.append(cur);
			}
            
			// 2. 최종 결과와 비교
			min = Math.min(sb.toString().length(), min);
		}

	}

}