import java.util.Scanner;

class Main {
	public static void main(String[] args) throws Exception {
		// (y, x)칸에서 위/아래 방향 선 -> 해당 칸부터 끝까지 있는 모든 칸의 가로 선의 개수가 교점
		// 좌/우 방향 선 -> 해당 칸부터 끝까지 있는 모든 칸의 세로 선의 개수가 교점
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int M = scanner.nextInt();
		scanner.nextLine();

		// 0은 세로선, 1은 가로선을 나타냄
		// 반직선에 포함되어 있는 선의 개수를 저장함
		long[][][] dp = new long[2][N + 1][N + 1];
		long spotCount = 0;

		for (int s = 0; s < M; s++) {
			int y = scanner.nextInt();
			int x = scanner.nextInt();
			String dir = scanner.next();
			scanner.nextLine();

			// D는 아래로 가는 직선이니, 가로로 된 직선 있는지 검사
			if (dir.equals("D")) {
				for (int i = y; i <= N; i++) {
					spotCount += dp[1][i][x]; // 그래프 그리면 해당 가로선에 있는 직선의 수 더함(끝까지 반복)
					dp[0][i][x]++;  // 해당 세로 직선을 하나 증가시킴
				}
			}
			// U는 위로 가는 직선이니, 올라가면서 가로로 된 직선 있으면 더하기
			else if (dir.equals("U")) {
				for (int i = 1; i <= y; i++) {
					spotCount += dp[1][i][x];
					dp[0][i][x] += 1;
				}
			} else if (dir.equals("R")) {
				for (int i = x; i <= N; i++) {
					spotCount += dp[0][y][i];
					dp[1][y][i] += 1;
				}
			} else if (dir.equals("L")) {
				for (int i = 1; i <= x; i++) {
					spotCount += dp[0][y][i];
					dp[1][y][i] += 1;
				}
			}
		}

		System.out.println(spotCount);
	}
}