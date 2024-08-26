class Solution {
	public long solution(int[] sequence) {
		long answer = 0;

		int size = sequence.length;
		// a : 처음이 양수인 펄스 부분수열 적용
		int[] a = new int[size];
		// b : 처음이 음수인 펄스 부분수열 적용
		int[] b = new int[size];
		int n = 1;

		for (int i = 0; i < size; i++) {
			a[i] = sequence[i] * n;
			n *= -1;
			b[i] = sequence[i] * n;
		}

		long[] dpA = new long[size];
		long[] dpB = new long[size];

		dpA[0] = a[0];
		dpB[0] = b[0];
		answer = Math.max(dpA[0], dpB[0]);

		// 이전 연속 수열에 자기를 포함 시키거나 자기 자신 혼자 있는 경우 중 큰 수가 최선의 경우
		for (int i = 1; i < size; i++) {
			dpA[i] = Math.max(dpA[i - 1] + a[i], a[i]);
			dpB[i] = Math.max(dpB[i - 1] + b[i], b[i]);

			long max = Math.max(dpA[i], dpB[i]);
			answer = Math.max(answer, max);
		}
		
		return answer;
	}
}