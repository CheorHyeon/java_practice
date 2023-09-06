class Solution {
	public int solution(String s) {
		// 1. String을 순회하면서 X의 개수와 X가 아닌 문자의 개수가 같아지는 지점을 찾고 분리
		// 2. 반복 하다가, 두 횟수가 다른 상태에서 읽을 글자가 없다면 지금까지 읽은 문자열 분리하고 종료
		
		String X = s.charAt(0)+"";
		char[] chars = s.toCharArray();
		
		int countX = 0;
		int countOther = 0;
		// 총 결과
		int countResult = 0;
		for(int i=0; i<chars.length; i++) {
			if((chars[i]+"").equals(X)) {
				countX++;
			}
			
			else {
				countOther++;
			}
			// 개수가 같아지는 순간 문자 고치기
			if(countX == countOther) {
				countResult++;
				countX = 0;
				countOther = 0;
				if(i+1 != chars.length) {
					X = chars[i+1] + "";
				}
			}
			// 마지막까지 읽었을때도 개수가 다르다면 그냥 그 자체로 분리니깐 결과 +1
			if(i == chars.length - 1) {
				if(countX != countOther) {
					countResult++;
				}
			}
		}
		
		return countResult;
	}
}