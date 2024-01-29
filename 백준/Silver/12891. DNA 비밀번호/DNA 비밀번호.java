import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 지켜야할 규칙 저장하는 배열
	static int checkArr[];
	// 현재 추출한 부분문자열의 규칙
	static int myArr[];
	// 조건을 만족하는 문자의 개수를 나타내는 변수
	static int checkSecret;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
		// 임의로 만든 문자열의 길이
		int S = Integer.parseInt(st.nextToken());
		// 비밀번호로 사용할 부분 문자열의 길이
		int P = Integer.parseInt(st.nextToken());
		// 만족하는 비밀번호 수
		int Result = 0;
		// 임의의 문자열 데이터
		char A[] = new char[S];
		checkArr = new int[4];
		myArr = new int[4];
		checkSecret = 0;
		A = bufferedReader.readLine().toCharArray();
		st = new StringTokenizer(bufferedReader.readLine());
		// 각 문자별로 몇번 나와야하는지 입력 받은 데이터 저장
		for (int i = 0; i < 4; i++) {
			checkArr[i] = Integer.parseInt(st.nextToken());
			// 0번이라면 이미 조건 만족이니 ++
			if (checkArr[i] == 0) {
				checkSecret++;
			}
		}
		// 1. 초기 P 부분 문자열 처리
		for (int i = 0; i < P; i++) {
			Add(A[i]);
		}
		if (checkSecret == 4)
			Result++;
		// 2. 슬라이딩 윈도우 처리 부분
		for (int i = P; i < S; i++) {
			// 빠지는 부분
			int j = i - P;
			// 새로 추가되는 부분 추가
			Add(A[i]);
			Remove(A[j]);
			if (checkSecret == 4)
				Result++;
		}
		System.out.println(Result);
		bufferedReader.close();

	}

	/*
		부분 문자열에서 제거하는 메서드
	 */
	private static void Remove(char c) {
		switch (c) {
			case 'A':
				if (myArr[0] == checkArr[0])
					checkSecret--;
				myArr[0]--;
				break;
			case 'C':
				if (myArr[1] == checkArr[1])
					checkSecret--;
				myArr[1]--;
				break;
			case 'G':
				if (myArr[2] == checkArr[2])
					checkSecret--;
				myArr[2]--;
				break;
			case 'T':
				if (myArr[3] == checkArr[3])
					checkSecret--;
				myArr[3]--;
				break;
		}
	}

	/*
		부분 문자열에서 문자의 개수를 추가하는 메서드
	 */
	private static void Add(char c) {
		switch (c) {
			case 'A':
				myArr[0]++;
				if (myArr[0] == checkArr[0])
					checkSecret++;
				break;
			case 'C':
				myArr[1]++;
				if (myArr[1] == checkArr[1])
					checkSecret++;
				break;
			case 'G':
				myArr[2]++;
				if (myArr[2] == checkArr[2])
					checkSecret++;
				break;
			case 'T':
				myArr[3]++;
				if (myArr[3] == checkArr[3])
					checkSecret++;
				break;
		}
	}
}