import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(int[] lottos, int[] win_nums) {
		// 1. lottos 및 win_nums를 오름차순 정렬
		// 2. lottos의 0을 해당 당첨번호와 같은 숫자로 넣음(최고 순위)
		// 2-1. lottos의 0을 그대로 나둠(어차피 다른 숫자니깐 최저 순위)
		// 3. 배열 탐색하며 같은 숫자의 개수를 세고 등수로 나눔

		Arrays.sort(lottos);
		Arrays.sort(win_nums);

		List<Integer> lottosList = Arrays.stream(lottos).boxed().collect(Collectors.toList());
		List<Integer> win_numsList = Arrays.stream(win_nums).boxed().collect(Collectors.toList());
		
		int count = 0;
		
		for(int i=0; i<win_numsList.size(); i++) {
			if(lottosList.contains(win_numsList.get(i))) {
				lottosList.remove(win_numsList.get(i));
				count++;
			}
		}

		// 최고 순위(0이 다 맞는 경우) 개수
		int correctNumCase1 = count;
		// 최저 순위(0이 다 안맞는 경우) 개수
		int correctNumCase2 = count;
		
		// 최고 순위면 다 맞을때, 최저면 다 안맞으니깐 증가 안하기
		for(int i=0; i<lottosList.size(); i++) {
			if(lottosList.get(i).equals(0))
				correctNumCase1++;
		}
		
		int max = 0;
		int worst = 0;
		
		switch (correctNumCase1) {
			case 6 -> max = 1;
			case 5 -> max = 2;
			case 4 -> max = 3;
			case 3 -> max = 4;
			case 2 -> max = 5;
			default -> max = 6;
		}

		switch (correctNumCase2) {
			case 6 -> worst = 1;
			case 5 -> worst = 2;
			case 4 -> worst = 3;
			case 3 -> worst = 4;
			case 2 -> worst = 5;
			default -> worst = 6;
		}

		return new int[] {max, worst};
	}
}