import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 1. 우선순위큐 정의
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> {
			int first_abs = Math.abs(o1);
			int second_abs = Math.abs(o2);
			// 절댓값이 같다면
			if(first_abs == second_abs) {
				return o1 - o2; // 절댓값이 같으면 오름차순(음수 앞으로)
			}
			else {
				return first_abs - second_abs; // 절댓값 오름차순(작은 수)
			}
		});
		for(int i = 0; i < N; i++) {
			int request = Integer.parseInt(br.readLine());
			// 2. 0이라면 큐에 있는것 출력하거나 큐가 비었다면 0출력
			if(request == 0) {
				if(pq.isEmpty()) {
					System.out.println("0");
				}
				else {
					System.out.println(pq.poll());
				}
			}
			// 3. 0이 아닌 숫자라면 큐에 넣기
			else {
				pq.add(request);
			}
		}
	}
}