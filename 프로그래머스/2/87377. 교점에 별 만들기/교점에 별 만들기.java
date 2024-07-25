import java.util.*;

class Coordinate {
	long x;
	long y;

	Coordinate(long x, long y) {
		this.x = x;
		this.y = y;
	}
}
class Solution {
	public String[] solution(int[][] line) {
		// 교점 저장 좌표 리스트
		List<Coordinate> cList = new ArrayList<>();
		// 교점 저장하기
		for(int i = 0; i < line.length - 1; i++) {
			for(int j = i + 1; j < line.length; j++) {

				long a = line[i][0], b = line[i][1], e = line[i][2],
					c = line[j][0], d = line[j][1], f = line[j][2];

				// 교점이 무한한 경우(두 직선 일치)는 없다 했으니 0이 아닌경우만
				// 평행일 수도 있지만 평행이면 교점이 없음
				if((a * d - b * c) != 0) {
					double x = (double) (b * f - e * d) / (a * d - b * c);
					double y = (double) (e * c - a * f) / (a * d - b * c);
					// 둘 다 정수일 경우
					if(x % 1 == 0 && y % 1 == 0) {
						cList.add(new Coordinate((long) x, (long) y));
					}
				}
			}
		}
		// 교점의 최소, 최대값 찾기
		Coordinate min = new Coordinate(Long.MAX_VALUE, Long.MAX_VALUE);
		Coordinate max = new Coordinate(Long.MIN_VALUE, Long.MIN_VALUE);

		for(Coordinate c : cList) {
			long cx = c.x, cy = c.y;

			if(cx < min.x) {
				min.x = cx;
			}

			if(cy < min.y) {
				min.y = cy;
			}

			if(cx > max.x) {
				max.x = cx;
			}

			if(cy > max.y) {
				max.y = cy;
			}
		}

		// 격자판 크기 산출 및 배열 선언
		int width = (int)(max.x - min.x + 1);
		int height = (int)(max.y - min.y + 1);

		char[][] arr = new char[height][width];

		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				arr[i][j] = '.';
			}
		}
		// 교점 찍기
		for(Coordinate c : cList) {
			arr[(int)(max.y - c.y)][(int)(c.x - min.x)] = '*';
		}
		// 답변배열 생성 후 격자판 문자열 변환 저장
		String[] answer = new String[height];
		for(int i = 0; i < answer.length; i++) {
			answer[i] = new String(arr[i]);
		}

		return answer;
	}
}