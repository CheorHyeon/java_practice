import java.util.*;
import java.io.*;

public class Main {
	static int[] parent;
	static int N, sum;
	static PriorityQueue<lEdge> queue;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		queue = new PriorityQueue<>();
		// 1. 총 랜선 길이, 엣지 그래프 그리기
		for(int i = 0; i < N; i++) {
			char[] tempc = br.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				int temp = 0;
				if(tempc[j] >= 'a' && tempc[j] <= 'z') {
					temp = tempc[j] - 'a' + 1;
				}
				else if(tempc[j] >= 'A' && tempc[j] <='Z') {
					temp = tempc[j] - 'A' + 27;
				}
				// 총 랜선 길이 저장
				sum = sum + temp;
				// 자기 자신 연결 아니고 연결할 수 있다면 랜선 목록에 추가
				// 엣지 그래프 저장
				if(i != j && temp != 0) {
					queue.add(new lEdge(i, j, temp));
				}
			}
		}
		// 2. 대표 노드 초기화
		parent = new int[N];
		for(int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		// 3. 최소신장트리 알고리즘 수행
		int useEdge = 0;
		int result = 0;
		while (!queue.isEmpty()) {
			lEdge now = queue.poll();
			if(find(now.s) != find(now.e)) {
				union(now.s, now.e);
				result = result + now.v;
				useEdge++;
			}
		}
		// 4. N-1개로 연결되었으면 전체 랜선 - 연결 랜선 으로 최대 기부 랜선 구하기
		if(useEdge == N - 1) {
			System.out.println(sum - result);
		}
		else {
			System.out.println(-1);
		}
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a != b) {
			parent[b] = a;
		}
	}

	public static int find(int a) {
		if(a == parent[a]) {
			return a;
		}
		else {
			return parent[a] = find(parent[a]);
		}
	}
}

class lEdge implements Comparable<lEdge> {
	int s, e, v;
	lEdge (int s, int e, int v) {
		this.s = s;
		this.e = e;
		this.v = v;
	}
	@Override
	public int compareTo(lEdge o) {
		return this.v - o.v;
	}
}