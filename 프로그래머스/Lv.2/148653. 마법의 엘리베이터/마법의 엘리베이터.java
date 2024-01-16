class Solution {
	public int solution(int storey) {
		String tmp = Integer.toString(storey);
		int[] arr = new int[tmp.length()];
		for (int i = 0; i < tmp.length(); i++) {
			arr[i] = tmp.charAt(i) - '0';
		}

		int answer = 0;

		for (int i = arr.length - 1; i >= 0; i--) {
			// 5보다 클 경우 올라감 처리
			if (arr[i] > 5) {
				answer += 10 - arr[i];
				// 가장 높은 자릿수의 경우 자릿수를 올리고나서 - 버튼을 눌러줘야함
				// ex) 6000 -> 1000을 4번 눌러서 올라가서 10,000 -> -10,000 버튼 클릭
				if (i == 0)
					answer++;
					// 가장 높은 자릿수가 아닌 경우 그 다음 숫자를 1 증가(자릿수 올림)
					// ex) 6 -> 1을 4번 눌러서 10층 이동 -> 10의 자리가 0에서 1로 변경
				else
					arr[i - 1]++;
			} 
			// 해당 자릿수가 5이면서 앞의 자리가 5이상이면 자리를 올려야 함
			else if (arr[i] == 5 && i > 0 && arr[i - 1] >= 5){
				arr[i-1] ++;
				answer += 5;
			}
			// 5이하일 경우 내려가는 것이 더 보석을 적게 사용
			else {
				answer += arr[i];
			}
		}
		return answer;
	}
}