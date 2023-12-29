class Solution {
	public long[] solution(long[] numbers) {
		int len = numbers.length;
		long[] answer = new long[len];
		for (int i = 0; i < len; i++) {
			// 짝수 : 숫자 +1
			if (numbers[i] % 2 == 0) {
				answer[i] = numbers[i] + 1;
			}
			// 홀수 : 01을 최초로 발견한 곳을 10으로 바꿔주는 것이 가장 최솟값
			// 01 -> 10 하기 위해서 기존 1을 빼주고 기존 0을 1로 바꾸기 위해 아래의 식
			// number - 2^cnt-1 + 2^cnt => number + 2^cnt-1
			// ex) 01111 -> 10111로 변경 => cnt : 4
			else {
				long number = numbers[i];
				int cnt = 0;
				// 1이 몇개 연속되는지 찾기
				while (number > 0) {
					if (number % 2 == 1)
						cnt++;
					else
						break;
					number /= 2;
				}
				answer[i] = numbers[i] + (long)Math.pow(2, cnt - 1);
			}
		}
		return answer;
	}
}