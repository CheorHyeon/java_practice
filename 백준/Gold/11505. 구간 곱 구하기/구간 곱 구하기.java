import java.util.*;
import java.io.*;

public class Main {
	static long[] tree;
	static int MOD = 1_000_000_007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int treeHeight = 0;
		int length = N;
		// 1. 리프노드에 데이터 저장할 최소 높이 구하기
		while (length != 0) {
			length /= 2;
			treeHeight++;
		}
		// 2. 2^k * 2 크기의 배열 선언
		int treeSize = (int) Math.pow(2, treeHeight + 1);
		int leftNodeStartIndex = treeSize / 2;
		tree = new long[treeSize + 1];
		for(int i = 1; i < tree.length; i++) {  // 곱셈이므로 초기값 1 설정
			tree[i] = 1;
		}
		// 3. 데이터를 리프노드에 입력하기
		for(int i = leftNodeStartIndex; i < leftNodeStartIndex + N; i++) {
			tree[i] = Long.parseLong(br.readLine());
		}
		// 4. 리프노드 마지막 부터 트리 채우기
		setTree(treeSize - 1);
		// 5. 질의 수행
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			long e = Long.parseLong(st.nextToken());
			if (a == 1) {
				changeVal(leftNodeStartIndex + s - 1, e);
			}
			else if (a == 2) {
				// 인덱스 조정
				s = s + leftNodeStartIndex - 1;
				e = e + leftNodeStartIndex - 1;
				System.out.println(getMul(s, (int) e));
			}
			else {
				return ;
			}
		}
		br.close();
	}
	// 곱셈 발생 시 MOD 연산
	private static long getMul(int s, int e) {
		long partMul = 1;
		while (s <= e) {
			if (s % 2 == 1) { // 오른쪽 노드 -> 부모노드가 구하고자 하는 범위 이전 노드 포함이기에 본인꺼 포함
				partMul = partMul * tree[s] % MOD;
				s++; // 다음 부모를 추가하기 위해 인덱스 증가
			}
			if(e % 2 == 0) { // 왼쪽 노드 -> 부모노드가 구하고자 하는 범위 이후 노드 포함 -> 본인꺼만 곱
				partMul = partMul * tree[e] % MOD;
				e--; // 이전 부모를 추가하기 위해 인덱스 감소
			}
			s = s/2;
			e = e/2;
		}
		return partMul;
	}

	private static void changeVal(int index, long val) {
		tree[index] = val;
		while (index > 1) { // 현재 노드의 양쪽 자식 노드를 찾아 곱하는 로직
			index = index / 2;
			tree[index] = (tree[index * 2] % MOD) * (tree[index * 2 + 1] % MOD) % MOD;
		}
	}

	private static void setTree(int i) {
		while (i != 1) {
			tree[i / 2] = tree[i / 2] * tree[i] % MOD;
			i--;
		}
	}
}