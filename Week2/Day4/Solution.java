import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws Exception {
		// 1. 배열 생성 및 폭탄 값 초기화 # : -1 (불변) / @ :
		// 2. 폭탄 반복을 돌며 해당 위치 상하좌우 검사(#(-1)이면 증가 x, @면 2 증가, 그냥 숫자면 1 증가)
		//  - land[] : 땅에 있는 숫자, (#은 -1)
		//  - isChar[] : 문자인지 저장 배열(@인지만 저장, #은 land에서 -1로)
		//  - 증가 시킬 땅이 문자인지 매번 검색
		// 3. 배열 순회하며 가장 높은 값 출력

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		int N = Integer.parseInt(input.split(" ")[0]);
		int K = Integer.parseInt(input.split(" ")[1]);

		int[][] land = new int[N][N];
		int[][] isChar = new int[N][N];

		// 1. 배열 초기화
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			String[] split = tmp.split(" ");
			for (int j = 0; j < N; j++) {
				if (split[j].equals("@")) {
					isChar[i][j] = 1; // 골뱅이 인지 표시
				} else if (split[j].equals("#")) {
					land[i][j] = -1;
				}
			}
		}

		// 2. 폭탄 반복
		for (int i = 0; i < K; i++) {
			String tmp = br.readLine();
			String[] split = tmp.split(" ");
			int y = Integer.parseInt(split[0]) - 1;
			int x = Integer.parseInt(split[1]) - 1;

			// // 영향을 받는 셀들
			// int[][] affectedCells = new int[][] {
			// 	{y, x}, {y - 1, x}, {y + 1, x}, {y, x - 1}, {y, x + 1}
			// };
			//
			// for (int[] cell : affectedCells) {
			// 	int row = cell[0];
			// 	int col = cell[1];
			// 	if (row >= 0 && row < N && col >= 0 && col < N) {
			// 		if (isChar[row][col] == 1) {
			// 			land[row][col] += 2;
			// 		} else if (land[row][col] != -1) {
			// 			land[row][col]++;
			// 		}
			// 	}
			// }

			// 폭탄 떨어진 곳에도 영향
			// 골뱅이 였으면 2 증가
			if (isChar[y][x] == 1) {
				land[y][x] = land[y][x] + 2;
			}
			// #(-1)이 아니라면 증가
			else if (land[y][x] != -1) {
				land[y][x]++;
			}

			// 상
			if (y - 1 >= 0) {
				if (isChar[y - 1][x] == 1) {
					land[y - 1][x] = land[y - 1][x] + 2;
				}
				// #(-1)이 아니라면 증가
				else if (land[y - 1][x] != -1) {
					land[y - 1][x]++;
				}
			}
			// 하
			if (y + 1 < N) {
				if (isChar[y + 1][x] == 1) {
					land[y + 1][x] = land[y + 1][x] + 2;
				}
				// #(-1)이 아니라면 증가
				else if (land[y + 1][x] != -1) {
					land[y + 1][x]++;
				}
			}

			// 좌
			if (x - 1 >= 0) {
				if (isChar[y][x - 1] == 1) {
					land[y][x - 1] = land[y][x - 1] + 2;
				}
				// #(-1)이 아니라면 증가
				else if (land[y][x - 1] != -1) {
					land[y][x - 1]++;
				}
			}

			// 우
			if (x + 1 < N) {
				if (isChar[y][x + 1] == 1) {
					land[y][x + 1] = land[y][x + 1] + 2;
				}
				// #(-1)이 아니라면 증가
				else if (land[y][x + 1] != -1) {
					land[y][x + 1]++;
				}
			}
		}
		// 최대값 찾기
		int max = 0;
		for (int[] line : land) {
			for (int point : line) {
				max = Math.max(max, point);
			}
		}

		System.out.println(max);
	}
}