import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int V = Integer.parseInt(input[2]);

		int[][] graph = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			String[] in = br.readLine().split(" ");
			int start = Integer.parseInt(in[0]);
			int end = Integer.parseInt(in[1]);
			graph[start][end] = 1;
			graph[end][start] = 1;
		}

		boolean[] visited = new boolean[N + 1];
		visited[V] = true;

		boolean[] visited2 = new boolean[N + 1];
		visited2[V] = true;

		StringBuilder dfsResult = new StringBuilder();
		dfs(0, V, graph, visited, dfsResult);
		System.out.println(dfsResult.toString().trim());

		StringBuilder bfsResult = new StringBuilder();
		bfs(V, graph, visited2, bfsResult);
		System.out.println(bfsResult.toString().trim());
	}

	private static void bfs(int start, int[][] graph, boolean[] visited2, StringBuilder result) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);

		while (!queue.isEmpty()) {
			int curY = queue.poll();
			result.append(curY).append(" ");

			int[] curArr = graph[curY];
			for (int i = 1; i < curArr.length; i++) {
				if (!visited2[i] && curArr[i] == 1) {
					visited2[i] = true;
					queue.add(i);
				}
			}
		}
	}

	private static void dfs(int cnt, int start, int[][] graph, boolean[] visited, StringBuilder result) {
		result.append(start + " ");
		int[] arr = graph[start];

		for (int i = 1; i < arr.length; i++) {
			if (!visited[i] && arr[i] == 1) {
				visited[i] = true;
				dfs(cnt + 1, i, graph, visited, result);
			}
		}
	}
}