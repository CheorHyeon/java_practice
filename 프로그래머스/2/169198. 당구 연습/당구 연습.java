class Solution {
	public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];

		for(int i = 0; i < balls.length; i++) {
			int targetX = balls[i][0];
			int targetY = balls[i][1];

			int curlen, len = Integer.MAX_VALUE;

			// 좌로 치기
			if(!(startY == targetY && startX > targetX)) {
				curlen = getDistance(startX, startY, targetX * -1, targetY);
				len = Math.min(curlen, len);
			}

			// 우로 치기
			if(!(startY ==  targetY && startX < targetX)) {
				curlen = getDistance(startX, startY, m + (m - targetX), targetY);
				len = Math.min(curlen, len);
			}
			
			// 위로 치기
			if(!((startX == targetX) && startY < targetY)) {
				curlen = getDistance(startX, startY, targetX, n + (n - targetY));
				len = Math.min(curlen, len);
			}
			
			// 아래로 치기
			if(!(startX == targetX && startY >= targetY)) {
				curlen = getDistance(startX, startY, targetX, targetY * -1);
				len = Math.min(curlen, len);
			}
			
			answer[i] = len;
		}

		return answer;
	}

	public int getDistance(int x1, int y1, int x2, int y2) {
		return (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}