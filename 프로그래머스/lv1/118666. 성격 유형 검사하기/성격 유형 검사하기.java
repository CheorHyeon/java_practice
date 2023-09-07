import java.util.Arrays;

class Solution {
	public String solution(String[] survey, int[] choices) {
		// 선택지에서 고른 번호에 맞게 점수 부여
		// 점수 높은 유형 배치(같으면 사전순)

		// 1: 라이언(R), 튜브(T)
		int[] group1 = new int[2];
		// 2: 콘형(C), 프로도(F)
		int[] group2 = new int[2];
		// 3: 제이지(J), 무지형(M)
		int[] group3 = new int[2];
		// 4: 어피치(A), 네오형(N)
		int[] group4 = new int[2];

		for (int i = 0; i < survey.length; i++) {
			String su = survey[i];
			int[] result;
			// 결과 저장
			switch (su) {
				case "RT":
				case "TR": {
					result = group1;
				}
				break;
				case "CF":
				case "FC": {
					result = group2;
				}
				break;

				case "JM":
				case "MJ": {
					result = group3;
				}
				break;
				default: {
					result = group4;
				}
				break;
			}
			// 선택 결과
			int choice = choices[i];

			int su1Score = 0;
			int su2Score = 0;

			if(su.charAt(0) == 'R' || su.charAt(0) == 'C' || su.charAt(0) == 'J' || su.charAt(0) == 'A') {
				switch (choice) {
					case 1 -> su1Score += 3;
					case 2 -> su1Score += 2;
					case 3 -> su1Score += 1;
					case 5 -> su2Score += 1;
					case 6 -> su2Score += 2;
					case 7 -> su2Score += 3;
					default -> {
					}
				}
			}
			// 반대로 문항이 되어있다면, 반대 변수를 증가시키게
			else {
				switch (choice) {
					case 1 -> su2Score += 3;
					case 2 -> su2Score += 2;
					case 3 -> su2Score += 1;
					case 5 -> su1Score += 1;
					case 6 -> su1Score += 2;
					case 7 -> su1Score += 3;
					default -> {
					}
				}
			}

			// 하나가 0이여도 0 더해도 그대로니깐 그냥 더하기
			result[0] += su1Score;
			result[1] += su2Score;
		}

		StringBuilder type = new StringBuilder();

		// 그룹1 결과 보기
		if (group1[0] < group1[1]) {
			type.append("T");
		} else if (group1[0] > group1[1]) {
			type.append("R");
		} else if (group1[0] == group1[1]) {
			type.append(sortedAlpha("RT"));
		}

		// 그룹2 결과 보기
		if (group2[0] < group2[1]) {
			type.append("F");
		} else if (group2[0] > group2[1]) {
			type.append("C");
		} else if (group2[0] == group2[1]) {
			type.append(sortedAlpha("CF"));
		}

		// 그룹3 결과 보기
		if (group3[0] < group3[1]) {
			type.append("M");
		} else if (group3[0] > group3[1]) {
			type.append("J");
		} else if (group3[0] == group3[1]) {
			type.append(sortedAlpha("JM"));
		}

		// 그룹4 결과 보기
		if (group4[0] < group4[1]) {
			type.append("N");
		} else if (group4[0] > group4[1]) {
			type.append("A");
		} else if (group4[0] == group4[1]) {
			type.append(sortedAlpha("NA"));
		}
		
		return type.toString();
	}

	private String sortedAlpha(String rt) {
		char[] chars = rt.toCharArray();
		Arrays.sort(chars);
		return chars[0] + "";
	}
}