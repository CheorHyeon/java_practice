import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	public static void main(String[] args) throws Exception {
		// 1. N X N 행렬 -> 그래프 표시
		// 2. [a, b], [b, a] => !0 (다리가 있다는 것)으로 연합 개수 -1
		// 2-1. [b, c], [c, b] => 연합국 -1, ... 반복, 연합에 포함된 노드는 모두 방문표시

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int[][] graph = new int[N + 1][N + 1];
		// 1. 그래프 그리기
		for (int i = 0; i < M; i++) {
			String[] tmp = br.readLine().split(" ");
			// 다리 연결 표시
			graph[Integer.parseInt(tmp[0])][Integer.parseInt(tmp[1])] = 1;
		}

		int[] visited = new int[N + 1];
		int count = 1;

		for (int i = 1; i <= N; i++) {
			if (visited[i] == 0) {
				Queue<Integer> q = new LinkedList<>();
				q.add(i);
				while (!q.isEmpty()) {
					int currentNode = q.poll();
					// 연합 번호로 매기기
					visited[currentNode] = count;

					for (int nextNode = 1; nextNode <= N; nextNode++) {
						if (graph[currentNode][nextNode] == 1 &&
							graph[nextNode][currentNode] == 1 &&
							visited[nextNode] == 0) {
							q.add(nextNode);
						}
					}
				}
				// 같은 연합으로 묶기가 끝났으니 다음 연합 번호로
				count++;
			}
		}
		// 연합 다 했어도 마지막에 카운트가 한번 더 증가하니 하나 빼주기
		System.out.println(count - 1);

	}
}