import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws Exception {
		// N개의 재료를 순서대로 쌓는다.
		// 맛 : 재료의 맛의 전체 합
		// 맛의 정도가 높은것을 기준으로 위랑 아래로 갈 수록 맛의 정도가 감소해야 완벽한 햄버거
		// 가장 높은것의 인덱스를 구하고, 아래나 위에 해당하는 맛의 정도가 점점 감소하는지 체크
		// 끝까지 가면 합 출력, 중간에 아니면 0 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 재료의 수
		String input = br.readLine();
		// 맛의 정도
		String degree = br.readLine();
		int[] degreeNumber = new int[Integer.parseInt(input)];

		String[] s = degree.split(" ");

		for (int i = 0; i < s.length; i++) {
			degreeNumber[i] = Integer.parseInt(s[i]);
		}

		int max = 0;
		int maxIndex = -1;

		for (int i = 0; i < degreeNumber.length; i++) {
			if (degreeNumber[i] > max) {
				max = degreeNumber[i];
				maxIndex = i;
			}
		}
		// 아래쪽 검사
		boolean underSuccess = true;
		for (int i = maxIndex; i >= 1; i--) {
			// 아래로 가면서 순차적으로 작아지는지 검사
			// 아니면 값 false로 업데이트
			if (!(degreeNumber[i - 1] <= degreeNumber[i])) {
				underSuccess = false;
				break;
			}
		}
		// 위쪽 검사
		boolean upSuccess = true;
		for (int i = maxIndex; i < degreeNumber.length - 1; i++) {
			// 위로 가면서 순차적으로 작지는지 검사
			// 아니면 값 false로 업데이트
			if (!(degreeNumber[i + 1] <= degreeNumber[i])) {
				upSuccess = false;
				break;
			}
		}
		// 위나 아래 중 하나라도 실패 시 맛이 완벽하지 않음
		if (upSuccess == false || underSuccess == false) {
			System.out.println("0");
		}
		// 완벽한 맛이라면 맛의 합 출력
		else {
			int sum = 0;
			for (int tmp : degreeNumber) {
				sum += tmp;
			}
			System.out.println(sum);
		}
	}
}