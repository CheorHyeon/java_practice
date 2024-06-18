import java.util.LinkedList;
import java.util.Queue;

class Solution {
	// 최초로 P를 만난 지점에서 갈 수 있는 위치 + 최초 P를 만난 지점과의 거리
    // ※ 갈 수 있는 위치 : 칸막이가 아니거나 방문하지 않은 경우
    // ※ 방문 : 큐에 넣을 때 방문 처리 
	class Point {
		int row, col, dist;

		Point(int r, int c, int d) {
			row = r;
			col = c;
			dist = d;
		}
	}

	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, -1, 1};

	public int[] solution(String[][] places) {
		int[] answer = new int[5];
		// 1. 각 대기방마다 조건 만족하는지 검사
		for (int i = 0; i < 5; i++) {
			if (check(places[i])) {
				answer[i] = 1;
			} else
				answer[i] = 0;
		}
		// 2. 결과 반환
		return answer;
	}

	// map을 받아서 문제의 조건을 만족하는지 검사하는 메서드
	boolean check(String[] place) {
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				if (place[r].charAt(c) == 'P') {
                    // 해당 라인에 여러명이 있더라도 한명이라도 규칙 미준수 시 false
					if (bfs(place, r, c) == false)
						return false;
				}
			}
		}
        // 여기 온것은 규칙 만족
		return true;
	}

	boolean bfs(String[] place, int row, int col) {
		boolean[][] visited = new boolean[5][5];

		Queue<Point> q = new LinkedList<>();
		visited[row][col] = true;
		q.add(new Point(row, col, 0));
		while (!q.isEmpty()) {
			Point curr = q.remove();

			if (curr.dist > 2)
				continue; // 거리 2 넘으면 진행 이유가 없음
			// 거리 2 이하에서 다른 응시자를 만났으니 false
			if (curr.dist != 0 && place[curr.row].charAt(curr.col) == 'P') {
				return false;
			}

			for (int i = 0; i < 4; i++) {
				int nr = curr.row + dx[i];
				int nc = curr.col + dy[i];
				if (nr < 0 || nr > 4 || nc < 0 || nc > 4)
					continue;
				// 큐에 이미 들어갔다면 스캔 필요 없음
				if (visited[nr][nc])
					continue;
				// X면 갈 수 없음
				if (place[nr].charAt(nc) == 'X')
					continue;
				visited[nr][nc] = true;
				q.add(new Point(nr, nc, curr.dist + 1));

			}
		}
		return true;
	}
}