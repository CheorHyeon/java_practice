import java.util.*;
import java.io.*;

public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] parent;
	static int[][] map;
	static int N, M, sNum;
	static boolean[][] visited;
	static ArrayList<ArrayList<int[]>> sumList;
	static ArrayList<int[]> mList;
	static PriorityQueue<bEdge> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		// 1. map 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 2. 섬 분리하기
		sNum = 1;
		sumList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// BFS 탐색을 통해 섬 분리, 섬 좌표 저장한 리스트를 섬 목록에 추가
				if (map[i][j] != 0 && !visited[i][j]) {
					BFS(i, j);
					sNum++;
					sumList.add(mList);
				}
			}
		}
		// 3. 최소 신장 트리 알고리즘 적용
		queue = new PriorityQueue<>();
		// 섬의 각 지점에서 만들 수 있는 모든 엣지 저장
		for (int i = 0; i < sumList.size(); i++) {
			ArrayList<int[]> now = sumList.get(i);
			for (int j = 0; j < now.size(); j++) {
				int row = now.get(j)[0];
				int col = now.get(j)[1];
				int nowSumNumber = map[row][col];
				// 네 방향 검색
				for (int d = 0; d < 4; d++) {
					int tempRow = dr[d];
					int tempCol = dc[d];
					int bridgeLength = 0;
					while (row + tempRow >= 0 && row + tempRow < N &&
						col + tempCol >= 0 && col + tempCol < M) {
						// 같은 섬이면 엣지 만들 수 없음 (섬의 끝칸에서만 다리 설치 가능)
						if (map[row + tempRow][col + tempCol] == nowSumNumber) {
							break;
						}
						// 같은 섬도 아니고 바다도 아닌 경우 -> 다리를 통해 연결했다는 것
						else if (map[row + tempRow][col + tempCol] != 0) {
							// 길이가 2 이상일 때만 다리 설치 가능
							if (bridgeLength >= 2) {
								queue.add(new bEdge(nowSumNumber, map[row + tempRow][col + tempCol], bridgeLength));
							}
							// 다리 연결을 했건 안했건간 일단 다른 섬과 검사 끝
							break;
						}
						// 같은 섬도 아니고 바다인 경우 -> 다리 연결
						else {
							bridgeLength++;
						}
						// 만약 위로 다리를 설치하는 경우 -> 다리를 쭉 세로로만 설치해야 함
						// 따라서 위로만 줄임
						if (tempRow < 0) {
							tempRow--;
						}
						// 아래로 다리 설치 시 -> 다리를 쭉 세로로만
						else if (tempRow > 0) {
							tempRow++;
						} else if (tempCol < 0) {
							tempCol--;
						} else if (tempCol > 0) {
							tempCol++;
						}
					}
				}
			}
		}
		// 섬의 수만큼 대표 배열 초기화
		parent = new int[sNum + 1];
		for (int i = 1; i <= sNum; i++) {
			parent[i] = i;
		}
		int useEdge = 0;
		int result = 0;
		// 최소신장트리 알고리즘 수행
		while (!queue.isEmpty()) {
			bEdge now = queue.poll();
			if (find(now.s) != find(now.e)) {
				union(now.s, now.e);
				result = result + now.v;
				useEdge++;
			}
		}
        // 0일때 연결 시작 -> sum - 2
		if (useEdge == sNum - 2) {
			System.out.println(result);
		} else {
			System.out.println(-1);
		}
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			parent[b] = a;
		}
	}

	public static int find(int a) {
		if (a == parent[a]) {
			return a;
		} else
			return parent[a] = find(parent[a]);
	}

	private static void BFS(int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		mList = new ArrayList<>();
		int[] start = {i, j};
		queue.add(start);
		mList.add(start);
		visited[i][j] = true;
		map[i][j] = sNum;
		while (!queue.isEmpty()) {
			int now[] = queue.poll();
			int r = now[0];
			int c = now[1];
			for (int d = 0; d < 4; d++) {
				int tempR = dr[d];
				int tempC = dc[d];
				while (r + tempR >= 0 && r + tempR < N &&
					c + tempC >= 0 && c + tempC < M) {
					if (visited[r + tempR][c + tempC] == false && map[r + tempR][c + tempC] != 0) {
						addNode(r + tempR, c + tempC, queue);
					} else {
						break;
					}
					if (tempR < 0)
						tempR--;
					else if (tempR > 0)
						tempR++;
					else if (tempC < 0)
						tempC--;
					else if (tempC > 0)
						tempC++;
				}
			}
		}
	}

	// 특정 위치를 섬의 정보로 넣어주는 함수
	private static void addNode(int i, int j, Queue<int[]> queue) {
		map[i][j] = sNum;
		visited[i][j] = true;
		int[] temp = {i, j};
		mList.add(temp);
		queue.add(temp);
	}
}

class bEdge implements Comparable<bEdge> {
	int s, e, v;

	bEdge(int s, int e, int v) {
		this.s = s;
		this.e = e;
		this.v = v;
	}

	@Override
	public int compareTo(bEdge o) {
		return this.v - o.v;
	}
}