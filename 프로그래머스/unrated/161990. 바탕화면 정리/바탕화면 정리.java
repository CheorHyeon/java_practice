class Solution {
	public int[] solution(String[] wallpaper) {
		// 최소가 되려면 가장 처음나온 '#'과 가장 마지막에 나오는 '#' 위치로 선택
		// 1. String 각 문자를 검사하고 '#' 이면 최대, 최소값 갱신
		// 2. 최소, 최대+1 점 리턴

		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;

		int maxX = 0;
		int maxY = 0;

		for (int i = 0; i < wallpaper.length; i++) {
			for (int j = 0; j < wallpaper[i].length(); j++) {
				if (wallpaper[i].charAt(j) == '#') {
					minX = Math.min(minX, j);
					minY = Math.min(minY, i);
					
					maxX = Math.max(maxX, j);
					maxY = Math.max(maxY, i);
				}
			}
		}

		return new int[] {minY, minX, maxY + 1, maxX + 1};
	}
}