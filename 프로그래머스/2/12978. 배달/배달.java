import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
	int[][] map;
	int contryCount;

	public int solution(int N, int[][] road, int K) {
		int answer = 0;
		// N개 마을에 대한 그래프 배열
		this.map = new int[N + 1][N + 1];
		this.contryCount = N;
		// 1. 인접행렬리스트 -> 가중치 그래프 행렬
		for (int[] roadInfo : road) {
			int loadInfo = roadInfo[2];
			// 만약 두 마을을 연결한 경로가 이전에 있다면 여러 경로가 있다는 것 -> 최소값으로 갱신
			if (map[roadInfo[0]][roadInfo[1]] != 0) {
				loadInfo = Math.min(map[roadInfo[0]][roadInfo[1]], loadInfo);
			}
			// 항상 최소 경로값인 경로로 갱신 - 마을을 잇는 도로니까 양방향
			map[roadInfo[0]][roadInfo[1]] = loadInfo;
			map[roadInfo[1]][roadInfo[0]] = loadInfo;
		}

		// 2. 다익스트라 알고리즘을 통해 1번부터 해당 마을로 갈 수 있는 최단경로 갱신
		dijkstra(1);

		// 3. k 이하인 마을 개수 반환
		for (int i = 1; i <= N; i++) {
			// K보다 작으면 해당 마을 배달 가능(0이면 자기 마을이니깐 배달 가능, 못가면 다익스트라 함수에서 무한대)
			if (map[1][i] <= K) {
				answer++;
			}
		}
		return answer;
	}

	private void dijkstra(int start) {
		boolean[] visited = new boolean[contryCount + 1];
		int[] distance = new int[contryCount + 1];
		Arrays.fill(distance, 1_000_000_000);
		// 자기 자신한테 가는 경로는 0
		distance[start] = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[] {start, 0});

		while (!pq.isEmpty()) {
			int[] node = pq.poll();
			int cur = node[0];
			int edge = node[1];
			visited[cur] = true;

			// 방문하지 않은 노드들 중 현재 노드를 거쳐서 다음 노드를 갔을때 경로가 바로 간 경로보다 짧으면
			// 우선순위큐에 넣고 거리 갱신
			for (int i = 1; i <= contryCount; i++) {
				if (!visited[i] && map[cur][i] != 0 && distance[i] > edge + map[cur][i]) {
					// 거리 갱신 및 우선순위큐 삽입
					distance[i] = edge + map[cur][i];
					pq.add(new int[] {i, distance[i]});
				}
			}
		}

		// 4. 최종 결과를 map 배열에 저장
		for (int i = 1; i < contryCount + 1; i++) {
			map[start][i] = distance[i];
		}
	}
}