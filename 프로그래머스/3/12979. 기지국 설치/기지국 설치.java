class Solution {
	public int solution(int n, int[] stations, int w) {
		int answer = 0;

		// 전파가 전달되지 않는 시작 아파트
		int start = 1;

		/*
			기지국들을 돌며 전파가 시작되지 않는 구간 찾기
			- start : (처음 1시작 제외) 이전 기지국의 "오른쪽" 전파 전달 되지 않는 아파트
			- end : 현재 기지국의 "왼쪽" 전파가 전달되지 않는 아파트
			- start가 현재 기지국의 왼쪽 전파가 전달되지 않는 구간보다 작거나 같으면(start <= end)
			구간의 기지국 개수를 구해준다.
		 */

		/* 다음 구간을 위해 start에 현재 기지국의 오른쪽 전파가 전달되지 않는 구간 아파트를 */
		// 기지국 전파 도달 구간 길이
		int possible = 2 * w + 1;
		for (int num : stations) {
			int end = num - (w + 1); // 현재 기지국의 왼쪽 전파가 닿지 않는 위치
			// 현재 기지국의 왼쪽 전파가 전달되지 않는 구간보다 작거나 같은 경우에만 기지국 추가 설치
			if (start <= end) {
				// 전파 전송되지 않는 총 구간의 길이
				int l = end - start + 1;
				answer += getMinimumKigikukCnt(l, possible);
			}
			// 현재 기지국으로 일단 다 커버 했으니 다음 기지국 보기 위한 start 조정
			// start : 이전 기지국의 오른쪽 전파 범위 도달하지 않는 시작 지점
			// 만일 현재 기지국이 끝까지 커버했다면 인덱스를 아파트 끝 지점의 다음 지점으로 조정하여
			// 반복문 종료 시 마지막 아파트 검사 안하게
			start = num + (w + 1) <= n ? num + (w + 1) : n + 1;
		}

		// 기존 설치한 기지국 커버를 다 마치고 맨 마지막 뒷부분 커버
		// start가 n이라면 맨 마지막 아파트 커버가 안된 것
		if (start <= n) {
			int lastLength = n - start + 1;
			answer += getMinimumKigikukCnt(lastLength, possible);
		}

		return answer;
	}

	private int getMinimumKigikukCnt(int length, int possible) {
		int cnt = 0;
		// 구간의 길이가 기지국 설치 시 도달하는 길이로 나눠 떨어질 경우 해당 몫의 개수만큼만
		// ex) 10의 길이가 전파 필요, 기지국 1개 설치 시 5 도달 -> 최소 2개 설치로 해결 가능
		if (length % possible == 0)
			cnt += (length / possible);
			// 나눠 떨어지지 않는 경우 추가로 1개 더 설치하여 해결
			// ex) 10의 길이가 전파 필요, 기지국 1개 설치 시 4 도달 -> 2개 설치해서 8충당, 나머지 2는 1개 추가 설치로 충당
		else
			cnt += ((length / possible) + 1);
		return cnt;
	}
}