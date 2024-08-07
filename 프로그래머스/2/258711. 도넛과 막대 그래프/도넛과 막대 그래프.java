import java.util.HashMap;
import java.util.Map;

class Solution {
	public int[] solution(int[][] edges) {

		// 각 노드의 나오는 간선 수, 들어오는 간선 수 저장하는 map
		Map<Integer, int[]> map = new HashMap<>();

		int extraNode = -1; // 추가된 노드
		int doughnut = 0; // 도넛 그래프 수
		int stick = 0; // 막대 그래프 개수
		int figure8 = 0; // 8자 그래프 개수

		// 1. 각 노드의 간선 개수 계산
		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];

			if (!map.containsKey(from)) {
				map.put(from, new int[] {0, 0});
			}

			if (!map.containsKey(to)) {
				map.put(to, new int[] {0, 0});
			}

			map.get(from)[0]++; // 나가는 간선 수 증가
			map.get(to)[1]++; // 들어오는 간선 수 증가
		}

		// 2. 노드를 탐색하며 각 그래프의 핵심 노드를 찾으면 개수 갱신
		for (int key : map.keySet()) {
			int[] count = map.get(key);

			// 생성한 정점 : 나가는 간선이 2개 이상이고 들어오는 간선이 없을 경우
			// 제한사항 內 : 그래프 수의 합은 2개 이상 -> 나가는 수는 무조건 2개 이상
			if (count[0] >= 2 && count[1] == 0) {
				extraNode = key;
			}

			// 8자 그래프 : 들어오는 간선과 나가는 간선이 각 2개 이상인 경우
			else if (count[0] >= 2 && count[1] >= 2) {
				figure8++;
			}

			// 막대 그래프 : 나가는 간선이 없고 들어오는 간선이 있을 경우
			else if (count[0] == 0 && count[1] > 0) {
				stick++;
			}
		}

		// 도넛 그래프 수 : 추가한 노드에서 나오는 정점 수 - (stick + figure8)
		// 전체 그래프 수는 생성한 정점에서 나가는 간선 수이므로 2개의 그래프를 제외한 나머지는 모두 도넛
		doughnut = map.get(extraNode)[0] - stick - figure8;

		return new int[] {extraNode, doughnut, stick, figure8};
	}
}