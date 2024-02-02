import java.util.Arrays;

class Solution {
	public int solution(int[] people, int limit) {
		// 탈 수 있으면 보내고, 못타면 무거운 사람 보낸다.(이제 같이 탈 사람이 더 없음)
		// 못탔으면, 그 최소 몸무게 사람과 2번째로 무거운 사람을 합해서 또 비교
		// 한명이 남을때 min == max 이므로 조건 만족시켜서 종료시키기
        // 1. 사람 정렬하고 가장 무거운 사람과 제일 가벼운 사람과 합했을때 탈 수 있는지 검사
		Arrays.sort(people);
        // 보트 개수
		int answer = 0;
        // 투포인터 지점 설정
		int min = 0;
        int max = people.length -1;
        
        // 2. 투포인터 지점을 활용하여 두 사람의 무게 합이 한계이내인지 검사
        // 가벼운 사람을 가르키는 인덱스가 큰 경우는 모두 구한 경우
        // 같은 경우는 1명 남았을 경우
        while(min <= max) {
            // 보트 무게 한계점 이내 있다면 보내면 되니 다음사람으로
            if(people[min] + people[max] <= limit) {
                min++;
                max--;
                answer++;
            }
            // 한계점을 넘는다면 무거운사람만 보내기
            else if(people[min] + people[max] > limit) {
                max--;
                answer++;
            }
            // 한명만 남았을 경우
            else if(min == max) {
                answer++;
                min++;
            }
        }
        // 3. 보트의 수 반환
		return answer;
	}
}