import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

class Main {
	public static void main(String[] args) throws Exception {
		// 2중 for문으로 다 방문하면서, 해당 상, 하, 좌, 우에도 동일한 아파트 유형인지 검사, 맞으면 체크
		// K개 이상이면 단지 수 저장하는 배열에 1 증가
		// 단지 수 저장한 배열의 최대값 출력(중복일 수 있으니, 여러개 찾아서 대소비교 하여 출력)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		String[] splitInput = input.split(" ");
		int N = Integer.parseInt(splitInput[0]);
		int K = Integer.parseInt(splitInput[1]);

		int[][] lands = new int[N][N];

		for (int i = 0; i < N; i++) {
			String tmpInput = br.readLine();
			lands[i] = Arrays.stream(tmpInput.split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		boolean[][] visited = new boolean[N][N];
		// 최대 타입은 30개
		int[] result = new int[31];

		for (int i = 0; i < lands.length; i++) {
			for (int j = 0; j < lands.length; j++) {
				if (!visited[i][j]) {
					bfs(lands, i, j, visited, result, K);
				}
			}
		}

		// 단지 수 개수 오름차순
		List<Integer> orderList = Arrays.stream(result)
			.boxed()
			.sorted(Collections.reverseOrder())
			.collect(Collectors.toList());

		// 최대 단지 아파트 타입 개수
		int maxTypeResult = orderList.get(0);
		// 최대 단지 아파트 타입 -> 개수 같으면 타입 번호 큰거니깐 그냥 쭉 탐색
		int maxType = 0;
		for (int i = 1; i < result.length; i++) {
			if (result[i] == maxTypeResult) {
				maxType = i;
			}
		}

		System.out.println(maxType);
	}

	private static void bfs(int[][] lands, int i, int j, boolean[][] visited, int[] result, int K) {
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};

		Queue<Integer> queue = new LinkedList<>();
		queue.add(j);
		queue.add(i);
		// 방문 표시
		visited[i][j] = true;

		// 단지 개수
		int count = 1;

		// 아파트 타입
		int type = lands[i][j];

		while (!queue.isEmpty()) {
			int x = queue.poll();
			int y = queue.poll();

			// 상 하 좌 우 모두 검사
			for (int k = 0; k < dx.length; k++) {
				int renewX = x + dx[k];
				int renewY = y + dy[k];
				if (renewX >= 0 && renewX < lands.length && renewY >= 0 && renewY < lands.length) {
					if (!visited[renewY][renewX]) {
						// 같은 아파트 타입이면 다음 탐색 대상
						// 다른 아파트 타입이면 함수 호출한 곳에서 다음에 탐색 대상으로 visited 갱신 x
						if (lands[renewY][renewX] == type) {
							queue.add(renewX);
							queue.add(renewY);
							visited[renewY][renewX] = true;
							count++;
						}
					}
				}
			}
		}
		// 해당 타입의 아파트 3개 이상이면 단지 형성
		// 단지 수 증가
		if (count >= K) {
			result[type]++;
		}
	}
}