class Solution {
	int[] arr;
	int count = 0;
	int targetQueenCount;

	public int solution(int n) {
		// 1. 전역변수 초기화
		// pick : n번째 열에 x번째 행에 퀸을 넣는지 저장하는 배열
		arr = new int[n];
		targetQueenCount = n;

		// 2. 재귀 호출
		recursive(0);
		return count;
	}

	public void recursive(int depth) {
		// 종료 조건 : depth가 n과 같을때 즉, n개의 퀸을 모두 배치한 경우
		if(depth == targetQueenCount) {
			count++;
			return;
		}
		// 실행
		// 해당 depth열에 i번째 행 자리에 퀸을 넣어보고 되면 다음 재귀 함수 호출
		// 안되면 퀸을 넣을 수 없음
		for(int i = 0; i < targetQueenCount; i++) {
			arr[depth] = i;
			if(canPositioningQueen(depth)) {
				recursive(depth + 1);
			}
		}
	}

	// 해당 위치에 퀸을 설치할 수 있는지 검사
	private boolean canPositioningQueen(int col) {
		for(int i = 0; i < col; i++) {
			// case 1 : 같은 행에 위치 시 위치할 수 없음
			if(arr[i] == arr[col]) {
				return false;
			}
			
			// case 2 : 같은 대각선 위치에 있다면 설치 불가능
			else if(Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
				return false;
			}
		}
		// 위 두 경우 제외하면 설치 가능
		return true;
	}
}