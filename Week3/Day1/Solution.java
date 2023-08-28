import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws Exception {
		// 1. N을 큰 수인 B로 최대한 나눈다.
		// 2. 나눈수의 나머지가 0이면 B로 나눈 나머지가 최소
		// - 0이 아니면 A로 나눠보고 안나눠지면, 나눈거를 하나 줄여봄
		// - 나눠떨어질때까지 줄여보고, 안되면 -1
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		String[] tmp = br.readLine().split(" ");
		int A = Integer.parseInt(tmp[0]);
		int B = Integer.parseInt(tmp[1]);

		int count = 0;

		// 최대 B만 먹었을 때
		int maxB = N / B;

		// 큰 물약으로 전부 회복 되면 끝
		if (N % B == 0) {
			count = maxB;
		}
		// 전부 회복이 안된다면, 큰 물약을 하나씩 줄여가며 체크
		// 짤짤이도 A로 회복이 전부 되면 그때 개수 넣고 종료
		// 큰 물약으로 크게 채우는것이 최소값
		else {
			for (int i = maxB; i >= 0; i--) {
				int remain = N - B * i;
				if (remain % A == 0) {
					count = i + remain / A;
					break;
				}
			}
		}

		if (count == 0) {
			System.out.println(-1);
		} else {
			System.out.println(count);
		}
	}
}