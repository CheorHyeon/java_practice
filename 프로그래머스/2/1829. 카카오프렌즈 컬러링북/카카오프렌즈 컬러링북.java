import java.util.LinkedList;
import java.util.Queue;

class Solution {
	int numberOfArea = 0;
	int maxSizeOfOneArea = 0;
	int[] dRow = new int[] {-1, 1, 0, 0};
	int[] dCol = new int[] {0, 0, -1, 1};
	boolean[][] visited;
	int[][] picture;

	public int[] solution(int m, int n, int[][] picture) {
		visited = new boolean[m][n];
		this.picture = picture;

		// 1. 영역 찾기 위해 bfs 탐색
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 색이 있으면서 방문하지 않았다면 bfs탐색 및 정답 값 갱신
				if (!visited[i][j] && picture[i][j] != 0) {
					bfs(i, j);
				}
			}
		}

		// 2. 답 반환
		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	private void bfs(int startRow, int startCol) {
		Queue<Integer> rQ = new LinkedList<>();
		Queue<Integer> cQ = new LinkedList<>();

		rQ.add(startRow);
		cQ.add(startCol);

		visited[startRow][startCol] = true;
		int cnt = 1; // 색의 수
		int color = picture[startRow][startCol]; // 어떤색인지

		while (!rQ.isEmpty()) {
			int curRow = rQ.poll();
			int curCol = cQ.poll();

			for (int i = 0; i < 4; i++) {
				int nextRow = curRow + dRow[i];
				int nextCol = curCol + dCol[i];

				if (nextRow >= 0 && nextRow < visited.length && nextCol >= 0 && nextCol < visited[0].length) {
					if (!visited[nextRow][nextCol] && picture[nextRow][nextCol] == color) {
						cnt++;
						visited[nextRow][nextCol] = true;
						rQ.add(nextRow);
						cQ.add(nextCol);
					}
				}
			}
		}
		// 전체 그룹 수 증가
		numberOfArea++;
		// 영역 큰 것으로 갱신
		maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cnt);
	}
}