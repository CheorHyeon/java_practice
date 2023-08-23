import java.util.Scanner;

class Main {
	public static void main(String[] args) throws Exception {
		// bandage : 1, medicine : 7, painkiller : 14
		// 현재 수치 N, 0으로 줄이기 위한 최소 개수
		// 1. 아이디어
		// - N이 0이 될때까지 현재 남은 N을 가장 많이 줄일 수 있는 아이템 사용
		// - 아이템 사용 개수를 1 증가
		// - 아이템이 각각 배수로 증가하기에, 가장 큰거를 사용해야 가장 최소로 사용할 수 있음.
		// 2. 자료형
		// - 치료 약 : int / 통증 수치 : 10^9 => 1,000,000,000 (10억 int 가능) / 치료 : 1로 10억번 -> 10억 int
		Scanner scanner = new Scanner(System.in);
		// 통증 입력
		int N = scanner.nextInt();
		// 가장 효과 좋은 것 부터 해야하므로 오름차순 초기화
		int[] medicine = new int[] {14, 7, 1};
		// 약의 수
		int count = 0;

		if (N >= 14) {
			// 필요한 약의 개수
			count += N / medicine[0];
			// 치료 후 남은 양
			N = N % medicine[0];
		}
		if (N >= 7) {
			count += N / medicine[1];
			N = N % medicine[1];
		}
		// 여기 온거면 짤짤이
		count += N / medicine[2];

		System.out.println(count);
	}
}