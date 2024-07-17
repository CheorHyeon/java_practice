import java.util.*;

class Solution {
	public int solution(int[][] targets) {
        // 미사일 1개만 날라온다 면 답은 1
        if(targets.length == 1) {
            return 1;
        }
		int answer = 0;
		// 미사일 종료 시간 기준 오름차순 정렬
		Arrays.sort(targets, (o1, o2) -> {
			if(o1[1] == o2[1]) {
				return o1[0] - o2[0];
			}
			return o1[1] - o2[1];
		});
		// 처음에 끝나는 시간 기준은 첫번째 미사일
		int endTime = targets[0][1];
		for(int i = 1; i < targets.length; i++) {
			// 다음 미사일 쏠 시간이 더 늦거나 같다면 해당 미사일은 일단 쏴야함
			if(endTime <= targets[i][0]) {
				endTime = targets[i][1];
				answer++;
			}
			// 다음 미사일 시작 시간보다 현재 미사일 종료 시간이 더 큰 경우에는 아직 존버가 가능
		}
		// 맨 마지막 미사일 쏴주기 처리
        // 존버를 해서 한방에 쏴주든가 존버 못하고 위 반복에서 맨 마지막 미사일은 남을 수도 있기 때문
        answer++;
		return answer;
	}
}