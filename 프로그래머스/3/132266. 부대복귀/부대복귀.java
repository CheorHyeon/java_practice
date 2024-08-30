import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	List<List<Integer>> graph = new ArrayList<>();
	int destination;

	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		int[] result = new int[sources.length];
		this.destination = destination;
		// 1. 총 지역수로 그래프 생성
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] road : roads) {
			graph.get(road[0]).add(road[1]);
			graph.get(road[1]).add(road[0]);
		}
		// 2. 부대원 위치에서 bfs 탐색 결과 추가
		for (int i = 0; i < sources.length; i++) {
			result[i] = bfs(sources[i]);
		}
		// 3. 결과 반환
		return result;
	}

	private int bfs(int start) {
		if (start == destination)
			return 0;

		boolean[] visited = new boolean[graph.size()];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		int distance = 0;

		while (!queue.isEmpty()) {
			distance++;
			int size = queue.size();
			// 갈 수 있는곳들만 검사 -> 탐색 범위를 줄임
			for (int i = 0; i < size; i++) {
				int curPosition = queue.poll();

				for (int neighbor : graph.get(curPosition)) {
					if (!visited[neighbor]) {
						if (neighbor == destination)
							return distance;
						visited[neighbor] = true;
						queue.add(neighbor);
					}
				}
			}
		}
		
		// 여기 온것은 갈 수 없는 경우
		return -1;
	}
}