class Solution {
	public int solution(int[] a) {
		int answer = 0;
		int len = a.length;
		// 풍선 1개 혹은 2개라면 모두 생존 가능
		if (a.length <= 2)
			return len;
		/*
			- 특정 i번째 위치에서 왼쪽에 a[i] 보다 작은 수가 있거나 오른쪽에 a[i] 보다 작은 수가 있을 때 a[i]는 무조건 터진다.
			- 이 경우를 효과적으로 보기 위해 각 i번째 위치에 왔을 때 까지의 풍선의 최소값을 배열에 각각 구해둔다.
			- i번째의 왼쪽인 i-1, 오른쪽인 i + 1 번째 수와 i번째 수를 비교한다.

		 */
		int[] leftMin = new int[len];
		int[] rightMin = new int[len];

		// 왼쪽부터 최소값
		int min = a[0];
		for (int i = 0; i < len; i++) {
			if (a[i] < min) {
				min = a[i];
			}
			leftMin[i] = min;
		}

		// 오른쪽부터 최소값
		min = a[len - 1];
		for (int i = len - 1; i >= 0; i--) {
			if (a[i] < min) {
				min = a[i];
			}
			rightMin[i] = min;
		}

		// 각 풍선에 대해 안터지는지 확인
		for (int i = 0; i < len; i++) {
			// 0일때는 왼쪽에 없으므로 오른쪽에 다 크거나 아니면 하나만 작으면 아에 안터질 수도 있기에 가능함
			// len - 1일때는 오른쪽에 풍선이 없으므로 왼쪽 풍선들이 다 크거나 하나만 작으면 안터질 수 있어 가능
			if (i == 0 || i == len - 1) {
				answer++;
				continue;
			}

			boolean leftCheck = (leftMin[i - 1] < a[i]);
			boolean rightCheck = (i == len - 1 || rightMin[i + 1] < a[i]);

			// 두 조건을 모두 만족한다면 반드시 터질 수 밖에 없으니 둘 다 false인 경우만
			if (!(leftCheck && rightCheck)) {
				answer++;
			}
		}

		// 답 반환
		return answer;
	}
}