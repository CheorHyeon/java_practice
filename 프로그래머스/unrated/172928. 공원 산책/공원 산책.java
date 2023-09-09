class Solution {
	public int[] solution(String[] park, String[] routes) {
		// 1. 공원 배열 생성 (S를 만나면 시작 위치 저장)
		// 2. 명령에서 배열 범위 유효한지 확인 + 장애물 없는지 확인 => 하나라도 안맞으면 무시
		// 3. 다 돌고나서 위치 출력

		// 1. 공원 배열 생성
		int[][] board = new int[park.length][park[0].length()];

		int startCol = 0;
		int startRow = 0;

		for (int i = 0; i < board.length; i++) {
			String rowData = park[i];
			for (int j = 0; j < rowData.length(); j++) {
				if (rowData.charAt(j) == 'S') {
					startCol = j;
					startRow = i;
				} else if (rowData.charAt(j) == 'X') {
					board[i][j] = -1;
				}
			}
		}

		// 2. 명령에서 배열 범위 유효한지 확인 + 장애물 없는지 확인 => 하나라도 안맞으면 무시
		for (String route : routes) {
			String[] routeArr = route.split(" ");
			String NSWE = routeArr[0];
			int walk = Integer.parseInt(routeArr[1]);

			boolean canWalk = true;
			switch (NSWE) {
				case "N" -> {
					// 북쪽으로 갈 수 있으면 갱신
					if (startRow - walk >= 0) {
						for (int tmp = 1; tmp <= walk; tmp++) {
							// 장애물 만나는지 검사
							if (board[startRow - tmp][startCol] == -1) {
								canWalk = false;
								break;
							}
						}
						if (canWalk) {
							startRow -= walk;
						}

					}
				}
				case "S" -> {
					// 남쪽으로 갈 수 있으면 갱신
					if (startRow + walk < board.length) {
						for (int tmp = 1; tmp <= walk; tmp++) {
							// 장애물 만나는지 검사
							if (board[startRow + tmp][startCol] == -1) {
								canWalk = false;
								break;
							}
						}
						if (canWalk) {
							startRow += walk;
						}
					}
				}
				case "W" -> {
					// 서쪽으로 갈 수 있으면 갱신
					if (startCol - walk >= 0) {
						for (int tmp = 1; tmp <= walk; tmp++) {
							// 장애물 만나는지 검사
							if (board[startRow][startCol - tmp] == -1) {
								canWalk = false;
								break;
							}
						}
						if (canWalk) {
							startCol -= walk;
						}
					}
				}
				default -> {
					// 동쪽으로 갈 수 있으면 갱신
					if (startCol + walk < board[0].length) {
						for (int tmp = 1; tmp <= walk; tmp++) {
							// 장애물 만나는지 검사
							if (board[startRow][startCol + tmp] == -1) {
								canWalk = false;
								break;
							}
						}
						if (canWalk) {
							startCol += walk;
						}
					}
				}
			}
		}

		return new int[] {startRow, startCol};
	}
}