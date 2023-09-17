import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	// 사각형 끝 점 좌표 저장 객체
	static class Rect {
		int x1, x2, y1, y2;

		public Rect(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	int x, y;
	List<Rect> recList;
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, -1, 1};

	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		int answer = 0;
		// 길이 2배 해줘야 하니 점 2배 증가했기에 map 크기 증가
        // 크기를 101이 아닌 102로 하는 이유는, 상하좌우 검색할 때 100번 위치에서 101번 검사할 수도 있음
		int[][] map = new int[102][102];
		// 사각형 형성 끝점의 리스트
		this.recList = new ArrayList<>();
		// 사각형 형성하는 끝점 리스트에 추가
		for (int i = 0; i < rectangle.length; i++) {
			// 시작점
			int sx = rectangle[i][0] * 2;
			int sy = rectangle[i][1] * 2;
			// 끝점
			int ex = rectangle[i][2] * 2;
			int ey = rectangle[i][3] * 2;

			// 사각형 내부 및 경계 -1로 채워서 사각형 그리기
			for (int y = sy; y <= ey; y++) {
				for (int x = sx; x <= ex; x++) {
					map[y][x] = -1;
				}
			}
			recList.add(new Rect(sx, sy, ex, ey));
		}

		return bfs(map, characterX * 2, characterY * 2, itemX * 2, itemY * 2);

	}

	int bfs(int[][] map, int x, int y, int tx, int ty) {
		Queue<int[]> q = new LinkedList<>();
		// 시작점 및 이동 횟수 저장
		q.add(new int[] {x, y, 1});
		// 방문 표시
		map[y][x] = 1;
		while (!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0];
			int py = p[1];
			int move = p[2];

			// 타겟(목표 지점) 도달 시 종료
			if (px == tx && py == ty) {
				// 좌표의 길이를 2배 늘렸으니 2로 나눔
				return (move / 2);
			}

			for (int i = 0; i < 4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				// 사각형 이면서 테두리일 경우 방문 가능
				// 만일 map[ny][nx] 값이 0이면 사각형이 x, 1이상이면 방문한 곳으로 제외
				// -1이 사각형 내부일 수도 있으니 경계인지 확인
				if (map[ny][nx] < 0 && isBoundary(nx, ny)) {
					map[ny][nx] = move + 1; // 움직인 횟수 갱신
					q.add(new int[] {nx, ny, move + 1});
				}
			}
		}
		// 못가는 경우 -1반환
		return -1;
	}

	boolean isBoundary(int x, int y) {
		// 만들어지는 사각형 전부를 검색함
		// 만일 검사하는 점이 경계가 아니라면 바로 종료
		for (Rect r : recList) {
			if (r.x1 < x && r.y1 < y && r.x2 > x && r.y2 > y) {
				return false;
			}
		}
		return true;
	}

}