class Solution {
	// (1,1) (1,2) (1,3)
	// (2,1) (2,2) (2,3)
	// (3,1) (3,2) (3,3)
	// ==
	// max(1,1) max(1,2) max(1,3) = 1 2 3
	// max(2,1) max (2,2) max(2,3) = 2 2 3
	// max(3, 1) max(3,2) max(3,3) = 3 3 3
	// == 
	// 1차원 배열 변환
	// idx   = 0 1 2 3 4 5 6 7 8
	// value = 1 2 3 2 2 3 3 3 3

	// idx / n = 2/3 = 0 + 1 = 1
	// idx % n = 2%3 = 1 + 1 = 2

	// 1차원 배열 2번 idx는 2차원 배열의 (1, 2)와 같다
	// (1, 2)중 큰 값이 2차원 배열의 값이므로, 1차원 배열의 2번 idx는 2

	// -> 이런식으로 left...righ-left 까지 구함
	// (int) 형변환이 가능한 이유는 배열 각각의 값은 무조건 n 이하이고 n <= 10,000,000

	public int[] solution(int n, long left, long right) {
		int[] answer = new int[(int)(right - left) + 1];
		for (int i = 0; i < answer.length; i++) {
			// left는 n^2미만인데, n으로 나누면 n미만이니 int 형변환 가능
			int y = (int)(left / n + 1);
			int x = (int)(left % n + 1);
			left++;
			// 배열에는 행, 열 중 큰값이 들어가는 규칙을 주석 처음에 확인
			answer[i] = Math.max(y, x);
		}

		return answer;
	}
}