import java.util.LinkedList;
import java.util.Queue;

class Solution {
	boolean[][] visited;
	char[][] map;
	int[] dr = {-1, 1, 0, 0};
	int[] dc = {0, 0, -1, 1};
	int[] start = new int[2];

	public int solution(String[] board) {
		visited = new boolean[board.length][board[0].length()];
		map = new char[board.length][board[0].length()];

		boolean isFindStart = false;
		// 1. map 생성 & 시작 지점 찾기
		for (int i = 0; i < board.length; i++) {
			map[i] = board[i].toCharArray();
			if (!isFindStart) {
				for (int j = 0; j < map[i].length; j++) {
					if (map[i][j] == 'R') {
						start[0] = i;
						start[1] = j;
						isFindStart = true;
					}
				}
			}
		}

		// 2. 게임 진행
		return bfs(start[0], start[1]);
	}

	private int bfs(int startRow, int startCol) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {startRow, startCol, 0});
		visited[startRow][startCol] = true;
		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int curRow = tmp[0];
			int curCol = tmp[1];
			int curCount = tmp[2];
			if(map[curRow][curCol] == 'G') {
				return curCount;
			}
			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dr[i];
				int newCol = curCol + dc[i];
				// map 유효범위 내에 있으면서 장애물 만날때의 위치 구하기
				while (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length && map[newRow][newCol] != 'D') {
						newRow += dr[i];
						newCol += dc[i];
				}
				// 장애물 만나기 직전 위치로 수정
				newRow -= dr[i];
				newCol -= dc[i];

				// 방문했던 곳이거나 최대 이동한 위치가 현재위치와 동일하다면 해당 경로 넘어가기
				if(visited[newRow][newCol] || (curRow == newRow && curCol == newCol)) {
					continue;
				}

				visited[newRow][newCol] = true;
				queue.add(new int[]{newRow, newCol, curCount+1});
			}
		}
		return -1;
	}
}