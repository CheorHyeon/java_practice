import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws Exception {
		// 1. N, K 추출 -> N * N인 배열 생성
		// 2. 구름판 생성(입력받은 0과 1을 배열로 저장)
		// 3. 값이 0인 곳을 탐색하고, 깃발을 세울 수 있는 지 검사
		// 4. 세울 수 있다면, K가 1~8이니깐 / 11~18로 깃발 세우기(구름이 1이니깐, 1과 구분하기 위함)
		// 5. 배열을 탐색하며 K에다가 10을 더한 값과 일치하면 count 증가 후 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] split = input.split(" ");
		// 1. N, K 추출
		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);
		// 2. 구름판 생성
		int[][] cloud = new int[N][N];
		// 2-1. 각각 입력값 초기화
		for (int i = 0; i < N; i++) {
			String input2 = br.readLine();
			String[] input2Split = input2.split(" ");
			for (int j = 0; j < N; j++) {
				cloud[i][j] = Integer.parseInt(input2Split[j]);
			}
		}
		// 3. 값이 0인 곳에 깃발 세울 수 있는지 검사 후 세우기
		// 상, 하, 좌, 우, 대각선 -> 칸이 넘치면 무시
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 현재 위치가 구름이 아니라면
				if (cloud[i][j] == 0) {
					// 4. 깃발과 구름과 구별하기 위해 10부터 시작
					cloud[i][j] = 10;
					// 위에 검사가 가능하다면
					if (i - 1 >= 0) {
						// 왼쪽 대각선 검사가 가능하다면
						if (j - 1 >= 0) {
							// 왼쪽 대각선이 구름이라면, 증가
							if (cloud[i - 1][j - 1] == 1) {
								cloud[i][j]++;
							}
						}
						// 바로 위칸(상) 검사가 가능하다면
						if (j >= 0) {
							// 바로 위(상) 구름이라면, 증가
							if (cloud[i - 1][j] == 1) {
								cloud[i][j]++;
							}
						}
						// 우측 대각선이 존재한다면
						if (j + 1 < N) {
							// 우측 대각선이 구름이라면 증가
							if (cloud[i - 1][j + 1] == 1) {
								cloud[i][j]++;
							}
						}
					}

					// 왼쪽 검사 가능한지
					if (j - 1 >= 0) {
						if (cloud[i][j - 1] == 1) {
							cloud[i][j]++;
						}
					}
					// 우측 검사가 가능한지
					if (j + 1 < N) {
						if (cloud[i][j + 1] == 1) {
							cloud[i][j]++;
						}
					}

					// 아래쪽 검사가 가능한지
					if (i + 1 < N) {
						// 왼쪽 아래 대각선 검사 가능한지
						if (j - 1 >= 0) {
							if (cloud[i + 1][j - 1] == 1) {
								cloud[i][j]++;
							}
						}
						//바로 아래쪽 검사
						if (j >= 0) {
							if (cloud[i + 1][j] == 1) {
								cloud[i][j]++;
							}
						}
						// 오른쪽 아래 대각선 검사 가능한지
						if (j + 1 < N) {
							if (cloud[i + 1][j + 1] == 1) {
								cloud[i][j]++;
							}
						}
					}
				}
			}
		}

		// 조건에 맞는 깃발 개수
		int count = 0;
		// 4-1. K에 10 더하기
		K += 10;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 5. 깃발의 값이 일치하는 개수
				if (cloud[i][j] == K) {
					count++;
				}
			}
		}
		System.out.println(count);
	}
}