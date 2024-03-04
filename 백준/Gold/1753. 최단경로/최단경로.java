import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int V, E;
	public static int[] distance;
	public static boolean[] visited;
	public static ArrayList<Edge>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		distance = new int[V + 1];
		visited = new boolean[V + 1];
		list = new ArrayList[V + 1];
		// 1. 사용할 배열 초기화
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
			distance[i] = Integer.MAX_VALUE;
		}
		// 2. 그래프 인접리스트로 그리기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[u].add(new Edge(v, w));
		}
		// 3. K번째 노드를 시작점으로 다익스트라 알고리즘 수행
		Dijkstra(K);
		// 4. 결과 출력
		for (int i = 1; i <= V; i++) {
			if (visited[i])
				System.out.println(distance[i]);
			else
				System.out.println("INF");
		}
	}

	public static void Dijkstra(int start) {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		q.add(new Edge(start, 0));
		distance[start] = 0;
		while (!q.isEmpty()) {
			Edge current = q.poll();
			int c_v = current.vertex;
			if (visited[c_v]) {
				continue;
			}
			visited[c_v] = true;
			for (int i = 0; i < list[c_v].size(); i++) {
				Edge tmp = list[c_v].get(i);
				int next = tmp.vertex;
				int value = tmp.value;
				if (distance[next] > distance[c_v] + value) {
					distance[next] = distance[c_v] + value;
					q.add(new Edge(next, distance[next])); // 업데이트 된 노드만 큐에 넣기
				}
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int vertex;
		int value;

		Edge(int vertex, int value) {
			this.vertex = vertex;
			this.value = value;
		}

		@Override
		public int compareTo(Edge e) {
			return this.value - e.value;
		}
	}

}