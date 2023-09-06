import java.util.ArrayList;
import java.util.List;

class Solution {
	public String solution(int[] numbers, String hand) {
		// 1. 숫자 번호를 배열로 표기
		// 2. 1, 4, 7 일 경우 왼쪽으로 표기하고, 현재 왼손 위치 갱신
		// 2-1. 3, 6, 9일 경우 오른쪽으로 표기하고, 현재 오른쪽 위치 갱신
		// 2-2. 2, 5, 8, 0 일때 두 손가락의 위치에서 더 가까운 위치
		// - 거리가 같다면 왼/오른손잡이 활용

		// 현재 왼, 오 위치
		// #,*은 누를 일이 없으니 10, 12로 초기화
		// 0은 11로
		int currentLeftHand = 10;
		int currentRightHand = 12;

		StringBuilder sb = new StringBuilder();

		List<Integer> left = new ArrayList<>();
		left.add(1);
		left.add(4);
		left.add(7);

		List<Integer> right = new ArrayList<>();
		right.add(3);
		right.add(6);
		right.add(9);

		for (int i = 0; i < numbers.length; i++) {
			// 0은 11로 취급
			if(numbers[i] == 0) {
				numbers[i] = 11;
			}
			if (left.contains(numbers[i])) {
				currentLeftHand = numbers[i];
				sb.append("L");
			} else if (right.contains(numbers[i])) {
				currentRightHand = numbers[i];
				sb.append("R");
			} else {
				// 0이면 그냥 자기 누르면됨
				// 1 또는 3 차이가 나면 거리가 1로 가까움
				// 2, 4, 6 차이가 나면 거리가 2
				// 5. 7, 9 차이 나면 거리 3
				// 10, 8 차이가 나면 거리 4
				int leftDistance = Math.abs(numbers[i] - currentLeftHand);
				int rightDistance = Math.abs(numbers[i] - currentRightHand);

				if(leftDistance == 0) {
					// 둘다 해당 번호에 있으면 주 손으로 누르면 됨
					if(rightDistance == 0) {
						switch (hand) {
							case "left" -> {
								sb.append("L");
								currentLeftHand = numbers[i];
							}
							default -> {
								sb.append("R");
								currentRightHand = numbers[i];
							}
						}
					}
					// 왼손에 이미 그 버튼이 있으니 누르면 됨
					else {
						sb.append("L");
						currentLeftHand = numbers[i];
					}
				}
				// 오른손만 해당 버튼에 있다면
				else if(rightDistance == 0) {
					sb.append("R");
					currentRightHand = numbers[i];
				}
				// 왼손이 1 또는 3 차이가 난다면
				else if (leftDistance == 1 || leftDistance == 3) {
					// 왼, 오 둘다 1 또는 3 차이가 난다면 왼/오른손잡이
					if (rightDistance == 1 || rightDistance == 3) {
						switch (hand) {
							case "left" -> {
								sb.append("L");
								currentLeftHand = numbers[i];
							}
							default -> {
								sb.append("R");
								currentRightHand = numbers[i];
							}
						}
					}
					// 아니면 왼손이 제일 가까운 경우
					else {
						sb.append("L");
						currentLeftHand = numbers[i];
					}
				}
				// 왼손이 거리가 1이 아니고 오른손이 1이라면 오른손이 가까운 것
				else if(rightDistance == 1 || rightDistance == 3) {
					sb.append("R");
					currentRightHand = numbers[i];
				}
				// 2, 4, 6 차이나면 거리가 2
				else if (leftDistance == 2 || leftDistance == 4 || leftDistance == 6) {
					// 왼, 오 둘다 2, 4, 6 차이가 난다면 왼/오른손잡이
					if (rightDistance == 2 || rightDistance == 4 || rightDistance == 6) {
						switch (hand) {
							case "left" -> {
								sb.append("L");
								currentLeftHand = numbers[i];
							}
							default -> {
								sb.append("R");
								currentRightHand = numbers[i];
							}
						}
					}
					// 아니면 왼손이 제일 가까운 경우
					else {
						sb.append("L");
						currentLeftHand = numbers[i];
					}
				}

				// 왼손이 거리가 2가 아니고 오른손이 2이라면 오른손이 가까운 것
				else if(rightDistance == 2 || rightDistance == 4 || rightDistance == 6) {
					sb.append("R");
					currentRightHand = numbers[i];
				}

				// 5, 7, 9 차이나면 거리가 3
				else if (leftDistance == 5 || leftDistance == 7 || leftDistance == 9) {
					// 왼, 오 둘다 5, 7, 9 차이가 난다면 왼/오른손잡이
					if (rightDistance == 5 || rightDistance == 7 || rightDistance == 9) {
						switch (hand) {
							case "left" -> {
								sb.append("L");
								currentLeftHand = numbers[i];
							}
							default -> {
								sb.append("R");
								currentRightHand = numbers[i];
							}
						}
					}
					// 아니면 왼손이 제일 가까운 경우
					else {
						sb.append("L");
						currentLeftHand = numbers[i];
					}
				}

				// 왼손이 거리가 3가 아니고 오른손이 3이라면 오른손이 가까운 것
				else if(rightDistance == 5 || rightDistance == 7 || rightDistance == 9) {
					sb.append("R");
					currentRightHand = numbers[i];
				}

				// 10, 8 차이나면 거리가 4
				else if (leftDistance == 10 || leftDistance == 8) {
					// 왼, 오 둘다 10, 8 차이가 난다면 왼/오른손잡이
					if (rightDistance == 10 || rightDistance == 8) {
						switch (hand) {
							case "left" -> {
								sb.append("L");
								currentLeftHand = numbers[i];
							}
							default -> {
								sb.append("R");
								currentRightHand = numbers[i];
							}
						}
					}
					// 아니면 왼손이 제일 가까운 경우
					else {
						sb.append("L");
						currentLeftHand = numbers[i];
					}
				}

				// 왼손이 거리가 4가 아니고 오른손이 4이라면 오른손이 가까운 것
				else if(rightDistance == 5 || rightDistance == 7 || rightDistance == 9) {
					sb.append("R");
					currentRightHand = numbers[i];
				}
			}
		}

		return sb.toString();
	}
}