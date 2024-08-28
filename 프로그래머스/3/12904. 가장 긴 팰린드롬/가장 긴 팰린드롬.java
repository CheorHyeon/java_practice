class Solution {
	String origin;

	public int solution(String s) {
		this.origin = s;
		// 최대 팰린드롬의 길이부터 하나씩 줄여가며 해당 길이를 만족하는 문자열이 있는지 확인
		for (int pal = s.length(); pal > 0; pal--) {
			// 0번부터 시작해서 찾고자 하는 팰린드론의 길이 범위를 찾음
			// 만약 찾는 최대 팰린드롬 길이가 3이라면 (0 ~ 2), (1 ~ 3), (2 ~ 4), ... 배열 범위 끝까지만 탐색
			for (int start = 0; start + pal <= s.length(); start++) {
                // 가장 긴 pal의 길이부터 하나씩 줄여갔으니 최초에 만족하는 것이 최대 크기
				if (isPalindrome(start, start + pal - 1))
					return pal;
			}
		}
		return 0;
	}

	/*
		시작 ~ 끝 범위 문자가 팰리드롬 문자 맞는지 검사하는 메서드
	 */
	private boolean isPalindrome(int start, int end) {
		while (start <= end) {
			if (origin.charAt(start++) != origin.charAt(end--))
				return false;
		}
		return true;
	}
}