import java.util.Arrays;

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
			// 항상 최소 경로값인 경로로 갱신
			map[roadInfo[0]][roadInfo[1]] = loadInfo;
			map[roadInfo[1]][roadInfo[0]] = loadInfo;
		}

		// 2. bfs 탐색을 통해 1번부터 해당 마을로 갈 수 있는 최단경로 갱신
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
		// 연결된 경로는 대체, 연결 안된것은 무한대
		for (int i = 1; i < contryCount + 1; i++) {
			if (map[start][i] != 0) {
				distance[i] = map[start][i];
			}
		}

		for (int i = 1; i < contryCount + 1; i++) {
			// 1. 방문하지 않은 정점 중 최소 거리를 가진 정점을 찾음
			int current = getSmallIndex(visited, distance);
			// 2. 방문 처리
			visited[current] = true;
			// 3. 현재 정점에서 다른 정점으로의 거리를 갱신
			for (int j = 1; j < contryCount + 1; j++) {
				if (!visited[j] && map[current][j] != 0 && distance[current] + map[current][j] < distance[j]) {
					distance[j] = distance[current] + map[current][j];
				}
			}
		}

		// 4. 최종 결과를 map 배열에 저장
		for (int i = 1; i < contryCount + 1; i++) {
			map[start][i] = distance[i];
		}
	}

	private int getSmallIndex(boolean[] visited, int[] distance) {
		int min = 1_000_000_000;
		int index = 0;
		for (int i = 1; i < contryCount + 1; i++) {
			if (!visited[i] && distance[i] < min) {
				min = distance[i];
				index = i;
			}
		}
		return index;
	}
}