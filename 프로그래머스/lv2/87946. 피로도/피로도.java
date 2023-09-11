class Solution {
	int answer;
	boolean[] visited;
	int[][] dungeons;

	public int solution(int k, int[][] dungeons) {
		// 완전 탐색 -> dfs 풀이
		this.answer = 0;
		this.visited = new boolean[dungeons.length];
		this.dungeons = dungeons;
		dfs(0, k, 0);
		return answer;
	}

	private void dfs(int cnt, int k, int ans) {
		for (int i = 0; i < dungeons.length; i++) {
			// 방문한 적이 없고, 최소 피로도 이상이라면
			if (!visited[i] && k >= dungeons[i][0]) {
				visited[i] = true;
				dfs(cnt + 1, k - dungeons[i][1], ans + 1);
				visited[i] = false;
			}
		}
		// for문 끝 -> 최대한으로 던전 돌았을때의 횟수
		// 최대값 갱신
		answer = Math.max(answer, cnt);
	}
}