import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
	String[][] mapsArr;
	boolean[][] visited;
	List<Integer> stayDays = new ArrayList<>();

	int[] dx = new int[] {0, 0, -1, 1};
	int[] dy = new int[] {1, -1, 0, 0};

	public int[] solution(String[] maps) {
		visited = new boolean[maps.length][maps[0].length()];
		// 2차원 map으로 변환
		mapsArr = new String[maps.length][maps[0].length()];
		for (int i = 0; i < maps.length; i++) {
			String tmp = maps[i];
			char[] tmpArr = tmp.toCharArray();
			for (int j = 0; j < tmpArr.length; j++) {
				mapsArr[i][j] = tmpArr[j] + "";
			}
		}

		// 1. 맵을 한칸씩 탐색하며 숫자를 찾는다.
		for (int y = 0; y < mapsArr.length; y++) {
			for (int x = 0; x < mapsArr[0].length; x++) {
				// 방문한 적이 없고 숫자라면 섬이니깐 연관된 섬을 찾기
				if (visited[y][x] == false && (!mapsArr[y][x].equals("X"))) {
					// 2. 숫자의 상, 하, 좌, 우를 검사하고 총합을 List에 넣는다.
					getMaxStayDay(x, y);
				}
			}
		}

		// 3. 리스트의 사이즈가 0이라면 -1을 반환한다.
		if (stayDays.size() == 0) {
			return new int[] {-1};
		}

		return stayDays.stream()
			.sorted()
			.mapToInt(Integer::intValue)
			.toArray();
	}

	/*
		최대 식량 일수 구하기, 인접한 섬들의 식량 합을 구해서 리스트에 넣어주는 메서드
	 */
	private void getMaxStayDay(int startX, int startY) {
		int maxStayDays = 0;
		// x, y 좌표 저장
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		// 현재 위치 방문 표시
		qx.add(startX);
		qy.add(startY);
		visited[startY][startX] = true;
		while (!qx.isEmpty()) {
			int x = qx.poll();
			int y = qy.poll();
			maxStayDays += Integer.parseInt(mapsArr[y][x]);
			for (int i = 0; i < dx.length; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				// 방문하지 못하면 다른 인접한 섬 확인
				if (cantSearchMapPosition(nextX, nextY)){
					continue;
				}
				if (!visited[nextY][nextX] && (!(mapsArr[nextY][nextX].equals("X")))) {
					qx.add(nextX);
					qy.add(nextY);
					visited[nextY][nextX] = true;
				}
			}
		}

		stayDays.add(maxStayDays);
	}
	/*
		검사할 녀석의 좌표가 map의 범위를 초과하여 탐험할 수 없는지 반환하는 메서드
		- true : 탐색 불가
		- false : 탐색 가능
	 */

	private boolean cantSearchMapPosition(int targetX, int targetY) {
		// 범위를 초과한 경우
		if(targetY >= visited.length || targetX >= visited[0].length)
			return true;
		if (targetY < 0 || targetX < 0)
			return true;

		return false;
	}
}