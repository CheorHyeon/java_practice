class Solution {
	public int solution(String[] want, int[] number, String[] discount) {
		int answer = 0;
		// 1. 10일 연속이니 전체길이-9 전까지
		for (int i = 0; i < discount.length - 9; i++) {
			int j;
			// 원하는 품목 하나씩 개수 맞는지 검사
			for (j = 0; j < want.length; j++) {
				int check = 0;
				for (int k = i; k < i + 10; k++) {
					if (discount[k].equals(want[j]))
						check++;
				}
				// 품목 검사하다가 원하는 개수가 안되면 종료 
				if (check < number[j])
					break;
			}
			// 종료안되고 마지막 품목까지 개수가 같다는 것이니 가입 가능한날
			if (j == want.length)
				answer++;
		}
		return answer;
	}
}