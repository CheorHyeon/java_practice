import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 문자열을 구분자로 쪼개주는 클래스 -> Token 생성(쪼개진 문자열)
		// 기본 구분자는 \t, \n
		// 특정 구분자를 지정해주려면 2번째 매개변수로 넣어주면 됨
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 합 배열 선언
		long[] S = new long[N + 1];
		// 연산 대상이 되는 숫자들 입력받아 각각 구분
		st = new StringTokenizer(br.readLine());
		// 1. N개의 숫자로 합배열 생성 => 문제에서 인덱스 1번부터 사용
		for (int i = 1; i <= N; i++) {
			// 합배열 공식 이용하여 저장
			S[i] = S[i - 1] + Integer.parseInt(st.nextToken());
		}
		// 2. 연산 M번 반복
		for (int q = 0; q < M; q++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			System.out.println(S[j] - S[i - 1]);
		}
	}
}