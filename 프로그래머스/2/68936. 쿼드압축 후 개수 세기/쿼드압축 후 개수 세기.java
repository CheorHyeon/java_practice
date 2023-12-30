class Solution {
	int[] answer = new int[2];

	public int[] solution(int[][] arr) {
		int totalSize = arr.length;
		countAfterQuerdZip(0, 0, totalSize, arr);
		return answer;
	}

	/*
		쿼드 압축 결과 반환하는 메서드
		- 1. 작은 영역으로 나누기
		- 2. 나누어진 영역 계산
		- 3. 압축 결과 포함시키기
	 */
	private void countAfterQuerdZip(int startX, int startY, int size, int[][] zipTarget) {
		if (canZip(startX, startY, size, zipTarget)) {
			// 해당 값은 0 아니면 1이니깐, 시작점 기준
			// 만약 압축이 된다면 1개로 압축 된것이니깐 1개만 더해주면 됨
			answer[zipTarget[startX][startY]]++;
			return;
		}
		// 좌 상단
		countAfterQuerdZip(startX, startY, size / 2, zipTarget);
		// 우 상단
		countAfterQuerdZip(startX + size / 2, startY, size / 2, zipTarget);
		// 좌 하단
		countAfterQuerdZip(startX, startY + size / 2, size / 2, zipTarget);
		// 우 상단
		countAfterQuerdZip(startX + size / 2, startY + size / 2, size / 2, zipTarget);
	}

	/*
		압축 되는지 검사하는 메서드
		- 2 x 2가 되면 각각 1칸씩 나누고 각 칸에서 for문 조건이 성립 안되니 무조건 true 반환
		- 2 x 2 초과된 사이즈면 압축 되는지 검사하다가 다른 것 발견하면 바로 false
	 */
	private boolean canZip(int x, int y, int size, int[][] arr) {
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				if(arr[x][y] != arr[i][j]) return false;
			}
		}
		return true;
	}
}