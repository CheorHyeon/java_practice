class Solution {
	int[][] matrix;
	public int[] solution(int rows, int columns, int[][] queries) {
		this.matrix = new int[rows + 1][columns + 1]; // 행렬 배열
		int[] answer = new int[queries.length]; // 정답 배열

		// 1. 행렬 채우기
		for(int i = 1; i <= rows; i++) {
			for(int j = 1; j <= columns; j++) {
				matrix[i][j] = (i - 1) * columns + j;
			}
		}

		// 2. 회전하고 최솟값 answer에 저장
		for(int i = 0; i < queries.length; i++) {
			answer[i] = rotate(queries[i]);
		}
		
		return answer;
	}

	private int rotate(int[] query) {
		int r1 = query[0];
		int c1 = query[1];
		int r2 = query[2];
		int c2 = query[3];

		int temp = this.matrix[r1][c1]; // 시작위치 값 임시 저장
		int min = temp;
		// 1. 왼쪽 세로 아래에서 위로 올리기
		for(int i = r1; i < r2; i++) {
			this.matrix[i][c1] = this.matrix[i+1][c1];
			if(min > this.matrix[i][c1]) min = this.matrix[i][c1];
		}
		// 2. 아래 가로 오른쪽에서 왼쪽으로 옮기기
		for(int i = c1; i < c2; i++) {
			this.matrix[r2][i] = this.matrix[r2][i+1];
			if(min > this.matrix[r2][i]) min = this.matrix[r2][i];
		}
		// 3. 오른쪽 세로 위에서 아래로 옮기기
		for(int i = r2; i > r1; i--) {
			this.matrix[i][c2] = this.matrix[i-1][c2];
			if(min > this.matrix[i][c2]) min = this.matrix[i][c2];
		}
		// 4. 위쪽 가로 왼쪽에서 오른쪽으로 옮기기
		for(int i = c2; i > c1; i--) {
			this.matrix[r1][i] = this.matrix[r1][i-1];
			if(min > this.matrix[r1][i]) min = this.matrix[r1][i];
		}
		// 처음에 빼둔 값을 c+1에 위치
		this.matrix[r1][c1 + 1] = temp;
		// 최솟값 반환
		return min;
	}
}