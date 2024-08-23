import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	int[] dRow = new int[] {-1, 1, 0, 0};
	int[] dCol = new int[] {0, 0, -1, 1};
	int[][][] cost;
	int N;
	int[][] board;

	public int solution(int[][] board) {
		N = board.length;
		// 방향까지 같이 지정 - 코너 도는 경로일 때 500원 추가되니 금액 고려하기 위함
		this.cost = new int[N][N][4];
		this.board = board;
		// 1. 위치, 방향별 금액 최대값 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					cost[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		// 2. 방향 고려한 bfs 탐색
		bfs(0, 0);
		// 3. 결과 반환 : 가장 싼 금액으로 오는 경로 추출(정렬해서 최소)
		return Arrays.stream(cost[N - 1][N - 1])
			.min()
			.getAsInt();
	}

	private void bfs(int startRow, int startCol) {
		Queue<int[]> queue = new LinkedList<>();
		// 시작 위치, 금액, 방향
		// 최초 위치 : 아래로 가거나 오른쪽 가거나 두 경우만 가능
		queue.add(new int[] {startRow, startCol, 0, 1});
		queue.add(new int[] {startRow, startCol, 0, 3});

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int k = 0; k < 4; k++) {
				int nextRow = cur[0] + dRow[k];
				int nextCol = cur[1] + dCol[k];
				int c = cur[2] + (cur[3] == k ? 100 : 600); // 방향이 바뀐 경로라면 코너 비용 추가

				if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
					// 벽이 아니며 다른 경로에서 해당 지점의 해당 방향으로 오는 비용보다 새로 들어오는것이 작으면 진입
					if (board[nextRow][nextCol] != 1 && cost[nextRow][nextCol][k] >= c) {
						cost[nextRow][nextCol][k] = c;
						queue.add(new int[] {nextRow, nextCol, c, k});
					}
				}
			}
		}
	}
}