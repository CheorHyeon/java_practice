import java.util.Arrays;

class Solution {
	public int solution(int[][] routes) {
		// 나가는 시간을 기준으로 오름차순 정렬
		// 카메라의 위치를 나갈때 기준으로 잡고, 나가기 전에 다른차가 들어오면 해당 자리에 설치 필요 없음
		// 만일 위의 사례가 안맞으면 현제 차 나갈때쯤 새로 설치

		Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
		int count = 0;

		int cam = Integer.MIN_VALUE;

		for(int i=0; i < routes.length; i++) {
			int[] route = routes[i];
			// 현재 차 캠의 위치보다 뒤에 있다면 설치
			if(route[0] > cam){
				// 차 나갈 위치에 설치하니 위치 갱신
				cam = route[1];
				count++;
			}
		}
		return count;
	}
}