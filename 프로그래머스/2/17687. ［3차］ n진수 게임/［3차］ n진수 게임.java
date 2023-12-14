import java.util.ArrayList;
import java.util.List;

class Solution {
	public String solution(int n, int t, int m, int p) {
		// 1. t x m x p 배열 생성
		List<String> arr = new ArrayList<>();
		int targetSize = t * m * p;
		int num = 0;
		// 2. targetSize 만큼만 자리 수 넣기
		while (arr.size() <= targetSize) {
			// 진법 전 숫자 모두 넣기
			// ex) 10진법 -> 0~9 일단 넣기
			// 16진법 -> A~F
			if (num < n) {
				if (num >= 10) {
					switch (num) {
						case 10 -> arr.add("A");
						case 11 -> arr.add("B");
						case 12 -> arr.add("C");
						case 13 -> arr.add("D");
						case 14 -> arr.add("E");
						case 15 -> arr.add("F");
					}
				} else {
					arr.add(num + "");
				}
			}
			// 진법에 맞게 num을 수정해서 넣기
			else {
				String change = changeNum(num, n);
				// 한자리씩 끊어서 넣어야 하니 앞에서부터 하나씩 넣기
				for (int i = 0; i < change.length(); i++) {
					arr.add(change.charAt(i) + "");
				}
			}
			// 숫자 증가
			num++;
		}
		// 3. p-1 부터 m개씩 건너띄는 인덱스 t개 추출
		StringBuilder answer = new StringBuilder();
		for (int i = p - 1; i < arr.size(); i = i + m) {
			// t개 까지만 저장이니 1개 적은것까지만 검사
			if (answer.length() < t) {
				answer.append(arr.get(i));
			} else {
				break;
			}
		}
		return answer.toString();
	}

	/*
		진법 변환
	 */
	private String changeNum(int target, int jin) {
		int mock = target / jin;
		int namogy = target % jin;
		String changeNamogy = numToString(namogy);
		if (mock != 0) {
			return changeNum(mock, jin) + changeNamogy;
		} else {
			return changeNamogy;
		}
	}

	/*
		나머지 숫자를 String으로 바꾸기
	 */
	private String numToString(int namogy) {
		String changeNum = "";
		switch (namogy) {
			case 10 -> changeNum = "A";
			case 11 -> changeNum = "B";
			case 12 -> changeNum = "C";
			case 13 -> changeNum = "D";
			case 14 -> changeNum = "E";
			case 15 -> changeNum = "F";
			default -> changeNum = namogy + "";
		}
		return changeNum;
	}
}