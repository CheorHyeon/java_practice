import java.util.Arrays;

class Solution {
	public int solution(int[] citations) {
		// n편 중 h번 이상 인용된 논문이 h편 이상, 나머지 논문이 h번 이하 인용 -> h의 최대값
		// 1, 정렬 하고 최대 인용된 수를 구함
		// 2. 기준이 1일때 1이상인 인용 수 / 이하인 인용수 , 1이상 ~ 최대H까지 반복
		// 2-1. 논문수 - 인용수
		// 3. 탐색하면서 인용 수가 이하 인용수 이상일때까지 인덱스 증가, 멈추면 해당 숫자가 인덱스

		// 정렬
		Arrays.sort(citations);

		// 최대 인용된 수
		int n = citations[citations.length-1];

		int nonMoonCount = citations.length;

		// 인용횟수 이상 저장 배열
		int[] resultArr = new int[n+1];
		int[] cantResultArr = new int[n+1];

		for(int i=1; i< resultArr.length; i++) {
			int count = 0;
			for(int j=0; j<citations.length; j++) {
				// 인용 횟수를 각각 탐색하며 i번째 이상 인용 했다면 횟수 증가
				if(citations[j] >=i) {
					count++;
				}
			}
			// i번 이상 인용된 논문 수
			resultArr[i] = count;
			// 전체 논문 수 - i번 이상 인용된 논문 수 : i번 이하 인용수
			cantResultArr[i] = nonMoonCount - count;
		}

		int hIndex = 0;

		for(int i=1; i< resultArr.length; i++) {
			// H-Idex : h번 이상 인용된 논문이 h번 이상 & 나머지 논문이 h번 이하라면 갱신
			// 최대 돌때까지 
			if(resultArr[i] >= i && cantResultArr[i] <= i) {
				hIndex = i;
			}
			// 위 조건이 맞지 않기 시작하면 이후부터는 다 아니니 종료
			else {
				break;
			}
		}
		return hIndex;
	}
}