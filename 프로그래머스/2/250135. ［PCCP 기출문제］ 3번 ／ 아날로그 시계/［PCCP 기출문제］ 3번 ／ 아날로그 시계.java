import java.util.List;

class Solution {
	static class Time {
		int h, m, s;

		Time(int h, int m, int s) {
			this.h = h;
			this.m = m;
			this.s = s;
		}

		// 초로 변환된 시간으로 Time을 만들 수 있는 생성자 정의
		Time(int seconds) {
			this.h = seconds / 3600;
			this.m = (seconds % 3600) / 60;
			this.s = (seconds % 3600) % 60;
		}

		// 모든 시간을 초로 변환
		int toSeconds() {
			return h * 3600 + m * 60 + s;
		}

		// 각도를 계산해서 List 형태로 반환
		List<Double> getDegree() {
			Double hDegree = (h % 12) * 30d + m * 0.5d + s * (1 / 120d);
			Double mDgree = m * 6d + s * (0.1d);
			Double sDgree = s * 6d;

			return List.of(hDegree, mDgree, sDgree);
		}

	}

	public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
		int answer = 0;
		// 시작 시간, 종료 시간 초 단위로 변경
		int start = new Time(h1, m1, s1).toSeconds();
		int end = new Time(h2, m2, s2).toSeconds();

		for (int i = start; i < end; i++) {
			// 현재 시간이 i초일 때 각도
			List<Double> cnt = new Time(i).getDegree();
			List<Double> next = new Time(i + 1).getDegree();

			boolean hMatch = hourMatch(cnt, next);
			boolean mMatch = minuteMatch(cnt, next);

			if (hMatch && mMatch) {
				// 시침과 분침의 각도가 같다면 +1만 해줘야 함
				// 0, 12시와 같이 정각에 초침, 시침, 분침 모두 겹칠때는 1회 울린다는 조건
				if (Double.compare(next.get(0), next.get(1)) == 0)
					answer++;
					// 나머지는 두 시간 차이에 각각 시침과 분침이 따로 겹친것
				else
					answer += 2;
			}
			// 둘 중 하나만 겹친 경우
			else if (hMatch || mMatch)
				answer++;
		}

		// 위 로직은 시작 시간에 대한 검사를 하지 않음
		// 0시 또는 12시에 시작한다면 한번 겹치고 시작하는 것 이기 때문에 +1
		if (start == 0 || start == new Time(12, 0, 0).toSeconds())
			answer++;

		return answer;
	}

	/*
		분침(시침), 초침이 맞는지 검사하기 위해서는
		- 현재 시점 : 초침 < 분침(시침)
		- N초 후 : 초침 > 분침(시침)
		- 즉, 한번 역전했을 때 분명 겹치는 지점이 있었을 것으로 검사
		  - 왜 초침으로 검사하냐? 초침이 더 1초에 움직이는 각도가 분침(시침) 보다 빠름
	 */

	private boolean hourMatch(List<Double> cnt, List<Double> next) {

		// 현재 시침이 초침보다 크며, 다음 시간에 역전된 경우
		if (Double.compare(cnt.get(0), cnt.get(2)) > 0
			&& Double.compare(next.get(0), next.get(2)) <= 0) {
			return true;
		}

		// 초침이 354도에서 0도로 넘어갈 때 예외 케이스
		// - 59초에서 00초로 이동 시 0도로 변경 되기 때문
		// - 시침을 354와 비교하는 이유
		//   - 초침이 59초일때 354도
		//   - 시침은 59초일때 x도
		//     - 초침의 1초당 이동 속도 > 시침의 1초당 이동 속도
		//     - 같은 시간에 초침이 더 움직이기 때문에 시침이 초침보다 뒤에 있어야(커야) 정각이 됨
		if (Double.compare(cnt.get(2), 354) == 0
			&& Double.compare(cnt.get(0), 354) > 0) {
			return true;
		}
		return false;
	}

	private boolean minuteMatch(List<Double> cnt, List<Double> next) {
		// 현재 분침이 초침보다 크며, 다음 시간에 역전된 경우
		if (Double.compare(cnt.get(1), cnt.get(2)) > 0
			&& Double.compare(next.get(1), next.get(2)) <= 0) {
			return true;
		}

		// 초침이 354도에서 0도로 넘어갈 때 예외 케이스
		// - 59초에서 00초로 이동 시 0도로 변경 되기 때문
		// - 분침을 354와 비교하는 이유
		//   - 초침이 59초일때 354도
		//   - 분침은 59초일때 x도
		//     - 초침의 1초당 이동 속도 > 분침의 1초당 이동 속도
		//     - 같은 시간에 분침이 더 움직이기 때문에 분침이 초침보다 뒤에 있어야(커야) 정각이 됨
		if (Double.compare(cnt.get(2), 354) == 0
			&& Double.compare(cnt.get(1), 354) > 0) {
			return true;
		}
		return false;
	}

}