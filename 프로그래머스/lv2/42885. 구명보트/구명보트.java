import java.util.Arrays;

class Solution {
	public int solution(int[] people, int limit) {
		// 사람 정렬하고 가장 무거운 사람과 제일 가벼운 사람과 합했을때 탈 수 있는지 검사
		// 탈 수 있으면 보내고, 못타면 무거운 사람 보낸다.(이제 같이 탈 사람이 더 없음)
		// 못탔으면, 그 최소 몸무게 사람과 2번째로 무거운 사람을 합해서 또 비교
		// 한명이 남을때 min == max 이므로 조건 만족시켜서 종료시키기
		Arrays.sort(people);

		int answer = 0;

		int min = 0;

		for(int max = people.length - 1; min <=max; max--) {
			if(people[min] + people[max] <= limit) {
				min++;
			}
			answer++;
		}

		return answer;
	}
}