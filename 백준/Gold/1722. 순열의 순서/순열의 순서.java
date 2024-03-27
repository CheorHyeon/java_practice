import java.util.*;
public class Main {
	static boolean[] visited;
	static int[] arr;
	static long k;
	static List<Integer> answerList;
	static long ans = 1; // 2번 게임
	static long[] fac;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		fac = new long[21];
		fac[0] = 1;
		// 1. 20! 까지 미리 초기화 해두기
		for(int i = 1; i <= 20; i++) {
			fac[i] = i * fac[i-1];
		}

		N = sc.nextInt();
		int num = sc.nextInt();
		arr = new int[N+1]; // 찾는 순열 저장 배열
		visited = new boolean[N+1];
		answerList = new ArrayList<>();

		// 2-1. 1인 경우 K번째 수열 구하기
		if(num == 1) {
			k = sc.nextLong();
			findNumberArray();
			for(int number : answerList) {
				System.out.print(number + " ");
			}
		}

		// 2-2. 2인 경우 몇 번째 순열인지 찾기
		else if(num == 2) {
			// 수열 입력
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			// 몇 번째 수열인지 찾기
			findRank();
			// 결과 출력
			System.out.println(ans);
		}
	}

	public static void findNumberArray() {
		for(int i = 0; i < N; i++) {
			for(int j = 1; j <= N; j++) {
				if(visited[j]) continue; // 이미 사용한 숫자 패스
				if(k - fac[N-1-i] > 0) {
					k = k - fac[N-1-i];
				}
				else {
					answerList.add(j);
					visited[j] = true;
					break;
				}
			}
		}
	}

	public static void findRank() {
		for(int i = 0; i < N; i++) {
			for(int j = 1; j < arr[i]; j++) { // arr[i] 보다 작은 수 건너 띄어야 하니 한 자리수 고정 제외 팩토리얼 더하기
				if(visited[j] == false) {
					ans += fac[N-1-i];
				}
			}
			visited[arr[i]] = true;
		}
	}
}