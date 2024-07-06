class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
		long maxy = 0; // 최대로 가질 수 있는 y
		for (long x = 0; x <= d; x += k) {
			// 좌표 x와 최대 거리 d에 대하여 최대로 가질 수 있는 y를 구한다.
			maxy = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));
			// k배 단위로 y값을 가질 수 있으므로 k로 나눈 뒤 1을 더한다.
			answer += (maxy / k) + 1;
		}
        
        return answer;
    }
}