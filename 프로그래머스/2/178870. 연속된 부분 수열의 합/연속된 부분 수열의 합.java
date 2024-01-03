import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(int[] sequence, int k) {
		// 1. left와 right 인덱스 지정 (left ~ right의 합 sum)
		// 2. sum이 k보다 작다면 right를 오른쪽으로 이동시켜 sum 증가
		// 3. sum이 k보다 크다면 left를 오른쪽으로 이동시켜 sum 감소
		// 4. sum이 k와 같다면 left, right 정보를 저장하는 객체를 저장
		// 4-1. 저장한 객체를 정렬하여 가장 거리가 짧고, 인덱스가 같은 것 출력
		int left = 0;
		int right = 0;
		int partitionSum = sequence[0]; // 부분 수열의 합

		int n = sequence.length;

		List<SubSequence> subs = new ArrayList<>();
		while (true) {
			// 합이 k라면 정답 후보
			if (partitionSum == k) {
				subs.add(new SubSequence(left, right));
			}
			// 둘 다 끝에 도달하면 종료
			if (left == n && right == n)
				break;
			// 만약 k보다 합이 작으면 오른쪽으로 한칸 이동, 같더라도 오른쪽 이동시킴
			if (partitionSum <= k && right < n) {
				right++;
				// 원소 추가 -> 합에 넣기
				if (right < n)
					partitionSum += sequence[right];
			}
			// k보다 큰 경우니깐 왼쪽거를 오른쪽으로 이동, 합계에서 빼주기
			else {
				if (left < n)
					partitionSum -= sequence[left++];
			}
		}
		subs.sort(SubSequence::compareTo);

		return new int[] {subs.get(0).left, subs.get(0).right};

	}
}

class SubSequence implements Comparable<SubSequence> {
	int left, right, length;

	public SubSequence(int left, int right) {
		this.left = left;
		this.right = right;
		this.length = right - left;
	}

	@Override
	public int compareTo(SubSequence o) {
		// 길이가 같다면 인덱스 앞쪽인 것부터
		if (this.length == o.length) {
			return this.left - o.left;
		}
		// 길이 오름차순(길이 짧은것부터)
		return this.length - o.length;
	}
}