import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 카드 묶음 수 저장
		PriorityQueue<Long> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			long data = sc.nextLong();
			pq.add(data);
		}
		long data1 = 0;
		long data2 = 0;
		long sum = 0;
        // 우선순위큐에서 가장 작은 카드 수를 먼저 정렬하고 다시 우선순위큐에 넣음
		while(pq.size() > 1) {
			data1 = pq.poll();
			data2 = pq.poll();
			sum += data1 + data2;
			pq.add(data1 + data2);
		}
		System.out.println(sum);
	}

}