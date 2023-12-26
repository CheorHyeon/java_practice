class Solution {
	char[][] map;

	public int solution(int m, int n, String[] board) {
		int answer = 0;
		// 1. 게임판에 옮겨놓기
		map = new char[m][n];
		for (int i = 0; i < m; i++) {
			map[i] = board[i].toCharArray();
		}
		/*
		2. 게임을 아래의 순서로 진행한다.
		  - 2-1. 없앨 블록을 찾고(없으면 종료)
		  - 2-2. 게임 판을 밑으로 내리고 반복
		 */

		while (true) {
			// 없앨 수 있는 블록의 개수 추출
			int cnt = clearBlock(m, n);
			// 더이상 없앨 수 있는 블록이 없다면 게임 종료
			if (cnt == 0) {
				break;
			}
			answer += cnt;
			// 밑으로 당기기
			dropBlock(m, n);
		}

		return answer;
	}

	/*
		2-1. 게임을 진행한다.
		- 조건에 맞으면 없앨 수 있는 블록의 개수 반환
		  - 해당 블록 우측, 아래, 우측 대각선 아래, 본인 => 총 4개가 인접한 것을 찾음
		  - 찾으면 graph를 true 상태로 변경
		- 전체 다 돌고 나서 true것의 개수를 반환
	 */
	private int clearBlock(int m, int n) {
		int[] dx = {0, 0, 1, 1};
		int[] dy = {0, 1, 0, 1};
		// 삭제할 수 있는지 검사하는 그래프
		boolean[][] check = new boolean[m][n];
		// 게임판 전체 순회
		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				char block = map[i][j];
				// 비워진 블락이라면 다음 문자 검사
				if (block == ' ')
					continue;
				// 해당 문자 인접 부분이 삭제 가능한지 나타내는 flag변수
				boolean canClear = true;
				// 인접한 부분(본인, 우측, 우측 대각선, 아래) 모두 같은 문자인지 검사
				for (int k = 0; k < 4; k++) {
					if (map[i + dx[k]][j + dy[k]] != block) {
						canClear = false;
						break;
					}
				}
				// 삭제할 수 있다면 true로만 변경
				// true로만 변경 해둬야 인접한 또 다른 조건이 만족할 때 영향을 안받을 수 있음
				if (canClear) {
					for (int k = 0; k < 4; k++) {
						check[i + dx[k]][j + dy[k]] = true;
					}
				}
			}
		}

		int cnt = 0;
		// 삭제 처리 및 개수 반환
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (check[i][j]) {
					cnt++;
					map[i][j] = ' ';
				}
			}
		}

		return cnt;
	}

	/*
		없어진 블록이 있다면 그 자리를 위의 블록으로 매꾸기(내리기)
		- idx : 채워질 부분(공백) => 초기값 : 맨 마지막 행을 가르킴
		  - 공백이 아니라면 for문 조건 만족해서 자기 자신 넣기
		  - 이후 행을 하나씩 올라가며 공백인것을 만나면 그대로 올라가며 검사
		  - 공백이 아닌 녀석을 발견하면 공백인 idx에 넣어준다.
	 */
	private void dropBlock(int m, int n) {
		for (int i = 0; i < n; i++) {
			int idx = m - 1;
			for (int j = m - 1; j >= 0; j--) {
				if (map[j][i] != ' ') {
					char temp = map[j][i];
					map[j][i] = ' ';
					map[idx--][i] = temp;
				}
			}
		}
	}
}