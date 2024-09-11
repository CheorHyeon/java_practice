class Solution {
	int[] wallet;
	int[] bill;

	public int solution(int[] wallet, int[] bill) {
		this.wallet = wallet;
		this.bill = bill;
		int answer = 0;
		// 1. 지폐가 들어갈 수 있을때까지 접기
		while (!canInTheWallet()) {
			// 큰 쪽의 길이 반으로 줄이기
			if (bill[0] > bill[1]) {
				bill[0] = bill[0] / 2;
			} else {
				bill[1] = bill[1] / 2;
			}
			// 접은 횟수 증가
			answer++;
		}

		// 2. 답 출력
		return answer;
	}

	/*
		지갑에 들어갈 수 있는지 체크하는 메서드
	 */
	private boolean canInTheWallet() {
		int walletMax = Math.max(wallet[0], wallet[1]);
		int walletMin = Math.min(wallet[0], wallet[1]);

		int billMax = Math.max(bill[0], bill[1]);
		int billMin = Math.min(bill[0], bill[1]);

		// 지갑 길이의 짧은쪽에 지폐의 짧은 부분, 긴쪽에 지폐의 긴 부분 중 하나라도 크기가 안맞으면 불가능
		if (billMax > walletMax || walletMin < billMin) {
			return false;
		}
		return true;
	}
}