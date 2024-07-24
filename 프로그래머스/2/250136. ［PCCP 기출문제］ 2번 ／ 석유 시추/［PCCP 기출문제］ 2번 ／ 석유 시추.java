import java.util.*;
class Solution {
	boolean[][] visited;
	int[][] land;
	int[] dRow = new int[] {-1, 1, 0, 0};
	int[] dCol = new int[] {0, 0, -1, 1};
	// 시추관 위치별 석유량
	int[] oil;

	public int solution(int[][] land) {
		this.land = land;
		this.visited = new boolean[land.length][land[0].length];
		this.oil = new int[land[0].length];
		// 1. 열마다 시추관을 꽂으며 석유가 있으면 해당 위치에서 bfs 탐색
		for (int i = 0; i < land[0].length; i++) {
			int cnt = 0;
			for (int j = 0; j < land.length; j++) {
				// 1-2. 해당 위치에 석유가 있으면서 아직 방문하지 않았다면 bfs 탐색
				if (land[j][i] == 1 && !visited[j][i]) {
					bfs(j, i);
				}
			}
		}
		// 2. 최대 많은 석유량을 가진 시추관 추출
		return Arrays.stream(oil).max().getAsInt();
	}

	public void bfs(int startRow, int startCol) {
		Queue<Integer> rQueue = new LinkedList<>();
		Queue<Integer> cQueue = new LinkedList<>();

		rQueue.add(startRow);
		cQueue.add(startCol);

		visited[startRow][startCol] = true;
		int result = 1;

		HashSet<Integer> colSet = new HashSet<>();
		colSet.add(startCol);

		while (!rQueue.isEmpty()) {
			int curRow = rQueue.poll();
			int curCol = cQueue.poll();

			for (int i = 0; i < 4; i++) {
				int nextRow = curRow + dRow[i];
				int nextCol = curCol + dCol[i];
				// 범위 내에 있으면서 석유 위치이면서 방문하지 않았다면 방문처리 및 다음 반복 추가
				if (nextRow >= 0 && nextRow < land.length && nextCol >= 0 && nextCol < land[0].length) {
					if (land[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
						result++;
						visited[nextRow][nextCol] = true;
						rQueue.add(nextRow);
						cQueue.add(nextCol);
						// 해당 열이 포함됨을 명시하기 위해 추가
						colSet.add(nextCol);
					}
				}
			}
		}

		Iterator<Integer> iterator = colSet.iterator();

		while (iterator.hasNext()) {
			// 해당 시추관에 꽂으면 result만큼 오일이 발견된다(하나의 석유그룹 가능)
			oil[iterator.next()] += result;
		}
	}
}