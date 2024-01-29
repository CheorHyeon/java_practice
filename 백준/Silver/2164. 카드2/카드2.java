import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList<>();
		int N = Integer.parseInt(br.readLine());
		// 카드 큐에 저장
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		// 카드가 1장 남을때까지 반복
		while (queue.size() > 1) {
			// 첫 카드 버리고
			queue.poll();
			// 그 다음 카드를 맨 뒤로 넣는다.
			queue.add(queue.poll());
		}
		System.out.println(queue.poll());
	}

}