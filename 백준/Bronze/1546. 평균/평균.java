import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 1. 과목 수 입력 받기
		int N = Integer.parseInt(br.readLine());
		String[] numStr = br.readLine().split(" ");
		// 각각의 점수를 정수 배열에 넣기
		int A[] = new int[N];
		int max = -99;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(numStr[i]);
			if (A[i] > max) {
				max = A[i];
			}
			sum += A[i];
		}
		// 3. 계산하기
		System.out.println(sum * 100.0 / max / N);
	}
}