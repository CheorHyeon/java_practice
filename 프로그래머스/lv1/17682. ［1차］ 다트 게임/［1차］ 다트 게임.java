class Solution {
	public int solution(String dartResult) {
		// 0 ~ 10점 3번
		// 영역 당첨 시 1, 2, 3제곱(원 점수에)
		// 스타상 -> 해당 점수와 바로 전에 얻은 점수를 각 2배
		// 아차상 -> 마이너스
		// 첫 번째 스타상 : 첫 점수만 2배
		// 스타상 중첩 가능 -> 4배
		// 스타상 + 아차상 -> -2배
		// 1. 각 던진 문자열 추출
		// 1-2. 각각 점수 계산
		// 2. 최종 합
		char[] chars = dartResult.toCharArray();

		// game2 인덱스 추출
		int game2Start = 0;
		if (chars[1] == '0') {
			for (int i = 2; i < chars.length; i++) {
				if (chars[i] >= '0' && chars[i] <= '9') {
					game2Start = i;
					break;
				}
			}
		}
		// 1자리 점수라면 다음 숫자 나오는 인덱스 찾기
		else {
			for (int i = 1; i < chars.length; i++) {
				if (chars[i] >= '0' && chars[i] <= '9') {
					game2Start = i;
					break;
				}
			}
		}
		
		int game3Start = 0;
		// 두번째가 0이다 -> 10점
		if (chars[game2Start + 1] == '0') {
			for (int i = game2Start + 2; i < chars.length; i++) {
				if (chars[i] >= '0' && chars[i] <= '9') {
					game3Start = i;
					break;
				}
			}
		}
		// 1자리 점수라면 다음 숫자 나오는 인덱스 찾기
		else {
			for (int i = game2Start + 1; i < chars.length; i++) {
				if (chars[i] >= '0' && chars[i] <= '9') {
					game3Start = i;
					break;
				}
			}
		}
		// 게임 1 결과
		String game1Result = dartResult.substring(0, game2Start);
		String game2Result = dartResult.substring(game2Start, game3Start);
		String game3Result = dartResult.substring(game3Start);

		int game1 = 0;
		int game2 = 0;
		int game3 = 0;
		// 10점까지니깐 1번 값이 0이면 10
		if (game1Result.charAt(1) == '0') {
			game1 = 10;
			switch (game1Result.charAt(2)) {
				case 'D' -> game1 = (int)Math.pow(game1, 2);
				case 'T' -> game1 = (int)Math.pow(game1, 3);
				default -> {
				}
			}
			// 옵션이 있는 경우
			if (game1Result.length() == 4) {
				switch (game1Result.charAt(3)) {
					case '*' -> game1 = game1 * 2;
					default -> game1 = game1 * -1;
				}
			}
		} else {
			game1 = Integer.parseInt(game1Result.substring(0, 1));
			switch (game1Result.charAt(1)) {
				case 'D' -> game1 = (int)Math.pow(game1, 2);
				case 'T' -> game1 = (int)Math.pow(game1, 3);
				default -> {
				}
			}
			// 옵션이 있는 경우
			if (game1Result.length() == 3) {
				switch (game1Result.charAt(2)) {
					case '*' -> game1 = game1 * 2;
					default -> game1 = game1 * -1;
				}
			}
		}

		// 10점까지니깐 1번 값이 0이면 10
		if (game2Result.charAt(1) == '0') {
			game2 = 10;
			switch (game2Result.charAt(2)) {
				case 'D' -> game2 = (int)Math.pow(game2, 2);
				case 'T' -> game2 = (int)Math.pow(game2, 3);
				default -> {
				}
			}
			// 옵션이 있는 경우
			if (game2Result.length() == 4) {
				switch (game2Result.charAt(3)) {
					case '*' -> {
						game2 = game2 * 2;
						game1 = game1 * 2;
					}
					default -> game2 = game2 * -1;
				}
			}
		} else {
			game2 = Integer.parseInt(game2Result.substring(0, 1));
			switch (game2Result.charAt(1)) {
				case 'D' -> game2 = (int)Math.pow(game2, 2);
				case 'T' -> game2 = (int)Math.pow(game2, 3);
				default -> {
				}
			}
			// 옵션이 있는 경우
			if (game2Result.length() == 3) {
				switch (game2Result.charAt(2)) {
					case '*' -> {
						game2 = game2 * 2;
						game1 = game1 * 2;
					}
					default -> game2 = game2 * -1;
				}
			}
		}

		// 10점까지니깐 1번 값이 0이면 10
		if (game3Result.charAt(1) == '0') {
			game3 = 10;
			switch (game3Result.charAt(2)) {
				case 'D' -> game3 = (int)Math.pow(game3, 2);
				case 'T' -> game3 = (int)Math.pow(game3, 3);
				default -> {
				}
			}
			// 옵션이 있는 경우
			if (game3Result.length() == 4) {
				switch (game3Result.charAt(3)) {
					case '*' -> {
						game3 = game3 * 2;
						game2 = game2 * 2;
					}
					default -> game3 = game3 * -1;
				}
			}
		} else {
			game3 = Integer.parseInt(game3Result.substring(0, 1));
			switch (game3Result.charAt(1)) {
				case 'D' -> game3 = (int)Math.pow(game3, 2);
				case 'T' -> game3 = (int)Math.pow(game3, 3);
				default -> {
				}
			}
			// 옵션이 있는 경우
			if (game3Result.length() == 3) {
				switch (game3Result.charAt(2)) {
					case '*' -> {
						game3 = game3 * 2;
						game2 = game2 * 2;
					}
					default -> game3 = game3 * -1;
				}
			}
		}

		return game1 + game2 + game3;
	}
}