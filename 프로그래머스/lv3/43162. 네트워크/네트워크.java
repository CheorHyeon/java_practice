import java.util.LinkedList;
import java.util.Queue;

class Solution {
	public int solution(int n, int[][] computers) {
		// i번째와 i+1이 연결되어있다면, i+1과, i+2가 연결 -> 하나의 네트워크
		// 네트워크 그룹으로 묶고, 마지막 네트워크 그룹이 개수 -> 중간에 연결 안된 것 있을 수 있음
		// 연결 안됐을 때는 result[i]가 0일것이라, 그거 만큼 개수 증가
		int[] result = new int[n];
		boolean[][] visited = new boolean[n][n];
		int count = 0;
		for (int i = 0; i < computers.length; i++) {
			for (int j = 0; j < computers[0].length; j++) {
				if (i != j && computers[i][j] == 1 && computers[j][i] == 1 && !visited[i][j]) {
					count++;
					bfs(i, i, visited, result, computers, count);
				}
			}
		}

		for (int i = 0; i < result.length; i++) {
			if (result[i] == 0) {
				count++;
			}
		}

		return count;
	}

	private void bfs(int y, int x, boolean[][] visited, int[] result, int[][] graph, int groupNum) {
		// 서로 연결된 그래프
		Queue<Integer> queue = new LinkedList<>();
		visited[y][x] = true;
		visited[x][y] = true;
		// 동일 그룹 지정
		result[y] = groupNum;
		result[x] = groupNum;
		// x와 연결된 노드들도 동일한 그룹에 포함시키기 위함
		queue.add(x);

		while (!queue.isEmpty()) {
			int next = queue.poll();
			for (int i = 0; i < visited.length; i++) {
				// 서로 연결되어 있다면 그룹 정보 갱신
				if (next != i && !visited[next][i] && !visited[i][next] &&
					graph[next][i] == 1 && graph[i][next] == 1) {
					queue.add(i);
					result[next] = groupNum;
					result[i] = groupNum;
					visited[next][i] = true;
					visited[i][next] = true;
				}
			}
		}

	}
}