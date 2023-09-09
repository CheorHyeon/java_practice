import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};

		int N = scanner.nextInt();
		int K = scanner.nextInt();
		int Q = scanner.nextInt();
		scanner.nextLine();

		List<String[]> matrix = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			matrix.add(scanner.nextLine().split(""));
		}

		for (int i = 0; i < Q; i++) {
			int y = scanner.nextInt() - 1;
			int x = scanner.nextInt() - 1;
			String c = scanner.next();

			matrix.get(y)[x] = c;

			Queue<int[]> queue = new LinkedList<>();
			queue.add(new int[] {y, x});
			boolean[][] visited = new boolean[N][N];
			visited[y][x] = true;

			// 방문한 위치 저장용 배열
			List<int[]> path = new ArrayList<>();
			while (!queue.isEmpty()) {
				int[] current = queue.poll();
				// 방문 위치 저장
				path.add(current);
				for (int j = 0; j < 4; j++) {
					// 상, 하, 좌, 우 다 검사
					int nextX = current[1] + dx[j];
					int nextY = current[0] + dy[j];

					if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
						//방문하지 않았고, 좌표의 값이 c라면 인접이니 큐에넣고 다음 탐색
						if (!visited[nextY][nextX] && matrix.get(nextY)[nextX].equals(c)) {
							visited[nextY][nextX] = true;
							// 다음 방문 경로 추가
							queue.add(new int[] {nextY, nextX});
						}
					}
				}
			}
			// 큐를 다 돌고나서 path의 길이가 K이상이라면 해당 칸들 비워주기
			if (path.size() >= K) {
				for (int[] coords : path) {
					matrix.get(coords[0])[coords[1]] = ".";
				}
			}
		}
		for (String[] tmp : matrix) {
			System.out.println(Arrays.stream(tmp).collect(Collectors.joining()));
		}
	}
}