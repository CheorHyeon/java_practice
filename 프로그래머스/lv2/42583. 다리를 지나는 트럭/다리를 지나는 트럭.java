import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		// 다리 만들기
		Queue<Integer> bridge = new LinkedList<>();

		// 큐의 길이 늘리기
		// 0 : 해당 칸이 비어있음을 의미
		IntStream.range(0, bridge_length).forEach(i -> bridge.add(0));

		int seconds = 0;
		// 다리에 올려져 있는 무게
		int onBridgeWeight = 0;
		int truckIndex = 0;

		// 트럭이 모두 다 진입할 때 까지 반복
		while (truckIndex < truck_weights.length) {
			// 다리 끝에 있는 녀석을 꺼낸다
			onBridgeWeight -= bridge.poll();

			// 넣어야 하는 트럭의 무게
			int truckWeight = truck_weights[truckIndex];

			// 진입시킬 수 있는지 확인
			if(onBridgeWeight + truckWeight <= weight) {
				bridge.add(truckWeight);
				onBridgeWeight += truckWeight;
				// 집어 넣었으니 다음 인덱스 트럭이 대상임을 명시
				truckIndex++;
			}
			else {
				// 들어갈 수 없으면 0 삽입
				bridge.add(0);
			}

			seconds++;
		}

		// 아직 다리 위에 남아있는 트럭이 있다면 그것이 다리를 지날 때 까지 기다림
		while (onBridgeWeight > 0) {
			onBridgeWeight -= bridge.poll();
			seconds++;
		}

		return seconds;
	}
}