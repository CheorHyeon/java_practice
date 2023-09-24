import java.util.LinkedList;
import java.util.Queue;

class Solution {
	public int solution(int n, int[][] edge) {
		// 그래프
		boolean[][] map = new boolean[n + 1][n + 1];
		for (int i = 0; i < edge.length; i++) {
			int start = edge[i][0];
			int end = edge[i][1];
			// 양방향
			map[start][end] = true;
			map[end][start] = true;
		}

		int[] visit = new int[n + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		int max = 0;
		while (!q.isEmpty()) {
			int value = q.poll();

			for (int j = 2; j <= n; j++) {
				if (visit[j] == 0 && map[value][j]) {
					visit[j] += visit[value] + 1;
					q.add(j);
					max = Math.max(max, visit[j]);
				}
			}
		}

		int answer = 0;
		// 개수를 구하는 거니깐 방문한 경로의 수 저장된 배열 검사
		for (int i = 0; i < visit.length; i++) {
			if (max == visit[i]) {
				answer++;
			}
		}

		return answer;
	}
}