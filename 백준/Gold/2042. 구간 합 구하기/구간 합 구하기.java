import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수
		int M = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
		int K = Integer.parseInt(st.nextToken()); // 구간 합을 구하는 횟수
		int treeHeight = 0;
		int length = N;
		// 1. 리프노드가 위치할 최소 높이 구하기
		while (length != 0) {
			length = length / 2;
			treeHeight++;
		}
		// 2. 해당 높이로 트리 구조 저장할 배열 사이즈를 구하기
		int treeSize = (int)Math.pow(2, treeHeight + 1); // 2^k * 2 가 트리의 크기
		int leftNodeStartIndex = treeSize / 2; // 2^k 부터 인덱스 시작
		tree = new long[treeSize]; // 0번 index 사용x

		// 3. 데이터를 리프노드에 입력 받기
		// 항상 풀로 찬 노드가 아님 ! ex) 데이터 5개 -> 2^3 = 8개 리프노드 배정
		// 이 중 5개만 받아야 하니 +N(데이터 개수) 인덱스 까지만
		for (int i = leftNodeStartIndex; i < leftNodeStartIndex + N; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}

		// 4. 리프노드의 마지막 인덱스를 넘겨 트리 완성하기
		// 마지막 리프노드 인덱스는 N-1 => 2^3 => 8 => 8 ~ 15 : 8개
		// 마지막 리프노드 인덱스 넘기기
		setTree(treeSize - 1);
		// 5. 질의 수행하기 (1 : 값 변경, 2 : 진행)
		// 인덱스 변경하기 : 질의 인덱스 + (2^k - 1) => 1, 3 => 1~3 숫자니깐 8~10, 2^3 - 1
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			long e = Long.parseLong(st.nextToken());
			// 값 변경
			if (a == 1) {
				chageVal(s + leftNodeStartIndex - 1, e);
			}
			// 부분합
			else if (a == 2) {
				s = s + leftNodeStartIndex - 1;
				e = e + leftNodeStartIndex - 1;
				System.out.println(getSum(s, (int)e));
			} else {
				return;
			}
		}
		br.close();
	}

	private static long getSum(int s, int e) {
		long partSum = 0;
		while (s <= e) {
			if (s % 2 == 1) { // 자신의 부모가 부분합에 포함되지 않는 인덱스를 포함한 합(이전)
				partSum = partSum + tree[s];  // 자신을 선택하고
				s++; // 다음 노드의 부모로 향하기 위해 인덱스 조정
			}

			if (e % 2 == 0) { // 자신의 부모가 부분합에 포함되지 않는 인덱스를 포함한 합(이후)
				partSum = partSum + tree[e];
				e--; // 이전 노드의 부모로 향하기 위해 인덱스 조정
			}
			s = s / 2;
			e = e / 2;
		}
		return partSum;
	}

	private static void chageVal(int index, long val) { // 값 변경 함수
		long diff = val - tree[index];
		// 부모로 가면서 합계 변경해주기
		while (index > 0) {
			tree[index] = tree[index] + diff;
			index = index / 2; // 부모 인덱스 가리키기
		}
	}

	private static void setTree(int i) {
		while (i != 1) {
			tree[i / 2] += tree[i];
			i--;
		}
	}
}