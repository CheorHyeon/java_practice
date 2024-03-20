import java.util.*;

public class Main {
	static ArrayList<Integer>[] tree;
	static boolean[] visited;
	static int answer = 0;
	static int deleteNode = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		tree = new ArrayList[N];
		visited = new boolean[N];
		int root = 0;
		for(int i = 0; i < N; i++) {
			tree[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			int p = sc.nextInt();
			if (p != -1) {
				tree[i].add(p);
				tree[p].add(i);
			}
			else {
				root = i;
			}
		}
		deleteNode = sc.nextInt();
		if(deleteNode == root) {
			System.out.println(0);
		}
		else {
			dfs(root);
			System.out.println(answer);
		}
	}
	static void dfs(int number) {
		visited[number] = true;
		int cNode = 0;
		for(int i : tree[number]) {
			// 삭제노드일 때 탐색 중지
			if(!visited[i] && i != deleteNode) {
				cNode++;
				dfs(i);
			}
		}
		// 자식 노드가 없을 때 리프노드
		if(cNode == 0) {
			answer++;
		}
	}
}