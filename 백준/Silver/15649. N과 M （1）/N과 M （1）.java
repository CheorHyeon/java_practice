import java.io.*;
import java.util.*;

class Main {
	public static int N;
	public static int M;
	public static boolean[] isUsed;
	public static PriorityQueue<String> pq = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isUsed = new boolean[N + 1];

		// 2. 재귀함수 호출하여 모든 경우의수 탐색
		recursive(0, "");

		// 3. 결과 출력
		while (pq.size() > 1) {
			System.out.println(pq.poll().trim());
		}
		System.out.print(pq.poll().trim());
	}

	private static void recursive(int depth, String choice) {
		// 종료 조건
		if(depth == M) {
			// 우선순위큐에 넣어서 자동 정렬
			pq.add(choice);
			return;
		}
		// 동작
		for(int i = 1; i < isUsed.length; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				recursive(depth + 1, choice + " " + i);
				isUsed[i] = false;
			}
		}
	}
}