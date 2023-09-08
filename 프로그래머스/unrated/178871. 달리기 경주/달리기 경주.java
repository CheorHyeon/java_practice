import java.util.HashMap;
import java.util.Map;

class Solution {
	public String[] solution(String[] players, String[] callings) {
		// 1. 등수 저장하는 Map 생성
		// 2. 부른 선수의 키를 찾고 등수가 1등(0)이 아니라면 이전 등수 선수와 교체

		// 1. 등수 저장하는 Map 생성
		Map<String, Integer> rank = new HashMap<>();
		for(int i=0; i<players.length; i++) {
			String player = players[i];
			rank.put(player, i);
		}

		// 2. 부른 선수
		for(String c : callings) {
			Integer preRanking = rank.get(c);
			// 1등이 아니면 변경
			if(preRanking > 0) {
				String tmp = players[preRanking-1];
				players[preRanking-1] = c;
				players[preRanking] = tmp;
				// 맵 정보 변경
				rank.put(tmp, preRanking);
				rank.put(c, preRanking-1);
			}
		}

		return players;
	}
}