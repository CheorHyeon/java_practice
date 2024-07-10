class Solution {
	public long solution(int r1, int r2) {
		long answer = 0;
		for(int i = 1; i <= r2; i++) {
			// 경계 시작점 : r1 크기 전 이면 s의 위치가 해당 i좌표의 정수 부분
			long s = 0;
			if(i < r1) {
				s = (long)Math.ceil(Math.sqrt((Math.pow(r1, 2) - Math.pow(i, 2))));
			}
			// 경계 종료점 정수 : 두 원 사이 종료 정수부분
			long e = (long)Math.sqrt((Math.pow(r2, 2) - Math.pow(i, 2)));
			// 두 점 사이의 정수 개수 더해주기
			answer += e - s + 1;
		}
		return answer * 4;
	}
}