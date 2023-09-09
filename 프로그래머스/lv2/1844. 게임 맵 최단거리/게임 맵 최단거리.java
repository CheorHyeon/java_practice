import java.util.LinkedList;
import java.util.Queue;

class Solution {
	int[][] maps;
	int[][] visited;

	public int solution(int[][] maps) {
		// bfs로 가면서 우, 하, 상, 좌 순으로 하면 최단거리?

		this.maps = maps;
		this.visited = new int[maps.length][maps[0].length];

		return bfs(0, 0);
	}

	private int bfs(int startY, int startX) {
		// 시작 칸 방문
		visited[0][0] = 1;
		// 상, 하, 좌, 우 순
		int[] dx = new int[] {0, 0, -1, 1};
		int[] dy = new int[] {-1, 1, 0, 0};
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startY);
		queue.add(startX);

		while (!queue.isEmpty()) {
			int targetY = queue.poll();
			int targetX = queue.poll();

			// 종료점이라면 목표 도달
			if (targetY == maps.length - 1 && targetX == maps[0].length - 1) {
				return visited[targetY][targetX];
			}

			int nextY = 0;
			int nextX = 0;

			for (int i = 0; i < dx.length; i++) {
				nextY = targetY + dy[i];
				nextX = targetX + dx[i];

				if (nextX >= 0 && nextX < maps[0].length && nextY >= 0 && nextY < maps.length) {
					// 0이면 방문한 것이 없는 것임
					if (visited[nextY][nextX] == 0 && maps[nextY][nextX] == 1) {
						visited[nextY][nextX] = visited[targetY][targetX] + 1;
						// 만일 경로가 2개가 된다면 그 지점에서 두곳을 각각 들림!!
						// 위, 우측 이라면 -> 위, 우측 큐 삽입 -> 위 갔다가 갈 수 있는곳 큐에 추가 
                        // -> 오른쪽 갔다가 갈 수 있는곳 큐에 추가
						// 따라서 도착하자마자 바로 그냥 끝내버리기가 가능!
						queue.add(nextY);
						queue.add(nextX);
					}
				}
			}
		}
		// 여기 온거면 도달 못한 것
		return -1;
	}
}