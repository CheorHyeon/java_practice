import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 1][M + 1];
		// 1. 배열 초기화
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 2. 누적합 배열 선언 D[i][j] = (1,1) ~ (i, j) 까지의 합
		int[][] D = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				D[i][j] = D[i - 1][j] + D[i][j - 1] - D[i - 1][j - 1] + arr[i][j];
			}
		}

		int answer = Integer.MIN_VALUE;
		// 3. 가능한 부분 배열의 합 모두 최대값 갱신 시도
		for (int rowS = 1; rowS <= N; rowS++) {
			for (int rowE = rowS; rowE <= N; rowE++) {
				for (int colS = 1; colS <= M; colS++) {
					for (int colE = colS; colE <= M; colE++) {
						answer = Math.max(answer,
							D[rowE][colE] - D[rowE][colS - 1] - D[rowS - 1][colE] + D[rowS - 1][colS - 1]);
					}
				}
			}
		}
		System.out.println(answer);
	}
}