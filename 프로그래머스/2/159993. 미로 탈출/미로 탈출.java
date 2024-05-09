import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static int[] dRow = {-1, 1, 0, 0};
	static int[] dCol = {0, 0, -1, 1};
	static int[] levor = new int[2];
	static String[][] map;
	static boolean[][] visited;

	static boolean isFindedLever = false;

	public int solution(String[] maps) {
		int answer = 0;

		// 1. 탐색을 위한 2차원 배열과 방문 배열 초기화
		String[][] map = new String[maps.length][maps[0].length()];
		this.visited = new boolean[maps.length][maps[0].length()];
		int[] start = new int[2];

		boolean isFinedStartRegion = false;

		// 2. map 그리기 및 최초 시작위치 탐색
		for (int i = 0; i < maps.length; i++) {
			String[] temp = maps[i].split("");
			map[i] = temp;
			if (!isFinedStartRegion) {
				for (int j = 0; j < temp.length; j++) {
					if (temp[j].equals("S")) {
						start[0] = i;
						start[1] = j;
						isFinedStartRegion = true;
						break;
					}
				}
			}
		}
		this.map = map;
		this.visited = new boolean[map.length][map[0].length];

		// 3. 레버까지 BFS 탐색
		int first = bfs(start, "L");
		if (first == -1) {
			return -1;
		}

		// 4. 탈줄지점까지 bfs 탐색
		// 레버 누르고 왔던길 다시 갈 수 있으니 방문 배열 초기화
		this.visited = new boolean[map.length][map[0].length];
		int second = bfs(levor, "E");
		if (second == -1) {
			return -1;
		}

		// 5. 정답 반환(시작 -> 레버 / 레버 -> 종료지점)
		return first + second;
	}

	public int bfs(int[] start, String target) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {start[0], start[1], 0});
		visited[start[0]][start[1]] = true;

		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int currentRow = temp[0];
			int currentCol = temp[1];
			int cnt = temp[2];

			for (int i = 0; i < 4; i++) {
				int newRow = currentRow + dRow[i];
				int newCol = currentCol + dCol[i];
				if (newRow >= 0 && newRow < map.length && newCol >= 0 && newCol < map[0].length) {
					if (map[newRow][newCol].equals(target)) {
						if (!isFindedLever) {
							levor[0] = newRow;
							levor[1] = newCol;
							isFindedLever = true;
						}
						return cnt + 1;
					}

					if (!map[newRow][newCol].equals("X") && !visited[newRow][newCol]) {
						visited[newRow][newCol] = true;
						queue.add(new int[] {newRow, newCol, cnt + 1});
					}
				}
			}
		}
		return -1;
	}
}