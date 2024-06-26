import java.util.HashSet;
import java.util.Set;

class Solution {
	public int solution(String dirs) {
		int answer = 0;
		int x = 0, y = 0;
		Set<String> visited = new HashSet<>();

		for (char dir : dirs.toCharArray()) {
			int nx = x, ny = y;
			switch (dir) {
				case 'U':
					ny++;
					break;
				case 'D':
					ny--;
					break;
				case 'L':
					nx--;
					break;
				case 'R':
					nx++;
					break;
			}

			if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
				continue;
			}

			// 경로 지정
			String from = x + "," + y;
			String to = nx + "," + ny;

			// 방문한 경로에 있는지 검사 한번도 안간 길이기 때문에 위치 바꼈을때도 확인
			if (!visited.contains(from + "-" + to) && !visited.contains(to + "-" + from)) {
				visited.add(from + "-" + to);
				visited.add(to + "-" + from);
				answer++;
			}

			// 다음 위치로 경로 조정
			x = nx;
			y = ny;
		}

		return answer;
	}
}