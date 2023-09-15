import java.util.Arrays;

class Solution {
	int[] graph;

	public int solution(int n, int[][] costs) {
		// 최소 신장 트리 : 가장 적은 비용으로 노드 연결
		// 크루스칼 알고리즘 활용하여 모든 노드 연결
		// 1. 거리가 짧은 순서대로 그래프를 그린다.
		// 1-1. 그래프를 그릴 때 사이클이 발생하지 않게 그림(Union-find 알고리즘)
		// 2. 각각 그려줄 때 간선의 비용을 더해주고 최종적으로 출력함

		int answer = 0;

		this.graph = new int[n];

		for (int i = 0; i < graph.length; i++) {
			graph[i] = i;
		}

		// 간선의 비용이 저렴한 순으로 정렬
		Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

		for (int[] cost : costs) {
			int start = cost[0];
			int end = cost[1];
			int num = cost[2];

			int startParent = getParent(start);
			int endParent = getParent(end);

			// 부모가 같으면 이미 그래프가 연결된거라 같지 않을때만 연결 처리
			if (startParent != endParent) {
				if(startParent > endParent) {
					graph[startParent] = endParent;
				}
				else {
					graph[endParent] = startParent;
				}
				// 비용 더해주기
				answer += num;
			}
		}

		return answer;
	}

	// 부모 찾기
	private int getParent(int node) {
		if (graph[node] == node)
			return node;
		else
			return getParent(graph[node]);
	}
}