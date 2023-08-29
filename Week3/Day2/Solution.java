import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
	// 1. 빈집이 아닌 곳(전기 필요한 곳)부터 상하좌우 검사, 1인곳 최초 만나면 발전기 설치
	// 2. 0을 만나면 탐색 종료, 0을 만날때까지 이어진 것, 전체 N만큼 탐색하면 종료
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] land = new int[N][N];
		boolean[][] visited = new boolean[N][N];

		// 땅 그리기
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			int[] tmpInt = new int[N];
			for (int j = 0; j < tmp.length; j++) {
				tmpInt[j] = Integer.parseInt(tmp[j]);
			}
			land[i] = tmpInt;
		}

		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (land[i][j] == 1 && !visited[i][j]) {
					bfs(land,visited, i, j);
					count++;
				}
			}
		}

		System.out.println(count);
	}

	private static void bfs(int[][] land, boolean[][] visited, int startY, int startX) {
		// 상 하 좌 우 좌표 설정
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};

		Queue<Integer> queue = new LinkedList<>();

		queue.add(startY);
		queue.add(startX);
		// 방문 했으므로 발전기 설치
		visited[startY][startX] = true;

		while (!queue.isEmpty()) {
			int y = queue.poll();
			int x = queue.poll();

			// 상 하 좌 우 검사
			for (int k = 0; k < 4; k++) {
				int reNewY = y + dy[k];
				int reNewX = x + dx[k];
				// 범위 내에 있는지 검사
				if ((reNewX >= 0 && reNewX < land.length) && (reNewY >= 0 && reNewY < land.length)) {
					if (land[reNewY][reNewX] == 1 && !visited[reNewY][reNewX]) {
						queue.add(reNewY);
						queue.add(reNewX);
						visited[reNewY][reNewX] = true;
					}
				}
			}
		}
	}
}