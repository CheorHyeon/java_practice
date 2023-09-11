import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
	int length;
	String[][] copyTickets;
	boolean[] checked;
	List<String> list = new ArrayList<>();

	public String[] solution(String[][] tickets) {
		// 1. dfs 탐색 -> 출발지 티켓으로부터 도착할 수 있는 것 모두 꼬리물기로 끝까지 가봄
		// 2. 전체 갈 수 있으면 사전순 정렬하고, 가장 첫번째 경로 반환

		this.length = tickets.length;
		this.copyTickets = tickets;
		this.checked = new boolean[length];

		dfs(0, "ICN", "ICN");

		Collections.sort(list);

		return list.get(0).split(",");
	}

	public void dfs(int depth, String start, String path) {
		// 깊이가 배열 길이 - 1이라면 티켓 모두 소진
		// 이 경우가 티켓 모두 소진, 탈출 조건에 부합하지 않은 dfs 메서드들은 어차피 for문만 돌다 종료
		if (depth == length) {
			list.add(path);
			return;
		}
		else {
			for(int i=0; i<length; i++) {
				// 방문하지 않았으며 다음 여행 경로 출발지를 찾아줌
				// A라는 국가에서 B, C 갈 수 있을 때 각 경로에서 끝까지 가봄
				// - A방문 표시하고 B로감, B에서 갈 수 있는곳 ...
				// - A방문 표시하고 C로감, C에서 갈 수 있는곳 ...
				if(!checked[i] && start.equals(copyTickets[i][0])) {
					checked[i] = true;
					dfs(depth + 1, copyTickets[i][1], path + "," + copyTickets[i][1]);
					// 다른 곳으로 갔다가 해당 국가를 들리는 경우가 답일 수도 있으니 다 돌고나서는 방문 대상으로 하기
					checked[i] = false;
				}
			}
		}
	}
}