import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		// 1. 그래프를 그릴 행렬 생성
		// 2. 시작 노드 방문 표시, 시작에서 인접한 노드로 갈 수 있다면 각각 실행 결과 받기(이동값, 마지막 노드)
		// 3. 얻어온 결과 비교
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] inputArray = input.split(" ");
		int N = Integer.parseInt(inputArray[0]);
		int M = Integer.parseInt(inputArray[1]);
		int K = Integer.parseInt((inputArray[2]));

		boolean[][] visited = new boolean[N+1][N+1];
		int[][] graph = new int[N+1][N+1];
		// 1 . 그래프 그리기
		for (int i = 0; i < M; i++) {
			String[] tmp = br.readLine().split(" ");
			int[] tmpInt = Arrays.stream(tmp).mapToInt(Integer::parseInt).toArray();
			int start = tmpInt[0] ;
			int end = tmpInt[1] ;
			// 양방향 처리
			graph[start][end] = 1;
			graph[end][start] = 1;

		}

		// 2. 시작 노드 방문 표시
		for(int d=1; d<visited.length; d++) {
			visited[d][K] = true;
		}
		

		int countList = 1;
		int lastNodes = K;

		// 2-1. bfs 탐색
		for (int j = 1; j < N+1; j++) {
			// 자기 자신과 연결안되니 탐색 제외하기 위한 visited 검사
			// 위에서 시작 노드 방문 표시 했으므로
			// 가장 작은 번호로 이동, 이후 번호는 무시니깐 break;
			if (graph[K][j] == 1 && !visited[K][j]) {
				int[] result = bfs(graph, K , j, visited);
				countList += result[0];
				lastNodes = result[1];
				break;
			}
		}

		System.out.println(String.format("%d %d", countList, lastNodes));
	}

	private static int[] bfs(int[][] graph, int y, int x, boolean[][] visited) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(x);
		// 양방향 처리
		visited[y][x] = true;
		visited[x][y] = true;
		// 방문 노드 개수
		int count = 1;
		// 해당 노드에 아에 접근하지 못하도록
		for(int i=1; i<visited.length; i++) {
			visited[i][x]=true;
		}

		// 갱신용 노드 번호
		int renewNode = 0;
		while(!queue.isEmpty()) {
			renewNode = queue.poll();
			for (int z = 1; z < graph.length; z++) {
				if (graph[renewNode][z] == 1 && !visited[renewNode][z]) {
						queue.add(z);
						count++;
						visited[renewNode][z] = true;
						visited[z][renewNode] = true;
						// 해당 노드로 아에 못오게
						for(int i=1; i<graph.length; i++) {
							visited[i][z] = true;
						}
						break;
					}
				}
			}
		return new int[] {count, renewNode};
	}
}