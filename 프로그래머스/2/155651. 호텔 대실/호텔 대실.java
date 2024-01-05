import java.time.Duration;
import java.time.LocalTime;
import java.util.PriorityQueue;

class Solution {
	public int solution(String[][] book_time) {
		int currentRoom = 0;
		int minRoom = 0;

		PriorityQueue<Integer> inTimePQ = new PriorityQueue<>();
		PriorityQueue<Integer> outTimePQ = new PriorityQueue<>();

		// 1. 예약, 체크아웃 손님 시간 큐에 넣기
		for (String[] book : book_time) {
			inTimePQ.offer(transferMinutes(book[0]));
			// 나가는 시간에 + 10분해야(청소 시간) 해당 방을 재사용 할 수 있음
			outTimePQ.offer(transferMinutes(book[1]) + 10);
		}

		// 예약손님 확인하며 가장 빨리 나가는 시간 전에 온사람들 모두 방 줘버리기
		while (!inTimePQ.isEmpty()) {
			// 2. 나가는 시간 전에 들어온 사람은 방 무조건 새로 줘야 함
			int currentOutTime = outTimePQ.poll();
			// 가장 빨리 나갈 사람보다 들어오려는 사람 있을 경우 방 주기
			while (!inTimePQ.isEmpty() && inTimePQ.peek() < currentOutTime) {
				inTimePQ.poll();
				currentRoom++;
				// 최고 빨리 나가는 사람이 나가기 전에 배정한 최대 만큼은 최소한 배정해야 함
				minRoom = Math.max(minRoom, currentRoom);
			}
			// 나갈 시간 전에 들어오는 사람 방 배정 완료했으니 나갈 시간에 현재 방 나가기 처리
			currentRoom--;
		}
		return minRoom;
	}

	/*
		00시부터 몇분이 지난 시간인지 반환하는 메서드
		ex) 01:00 => 60분 지남
	 */
	private Integer transferMinutes(String time) {
		LocalTime time1 = LocalTime.of(0, 0);
		LocalTime time2 = LocalTime.parse(time);
		Duration duration = Duration.between(time1, time2);
		return (int)duration.toMinutes();
	}
}