import java.io.*;
import java.util.Scanner;
class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N;
		int T;
		int M;
		int[] task;
		// task 개수 받기
		N = sc.nextInt();
		task = new int[N];
		// 시작 시간 받기
		T = sc.nextInt();

		M = sc.nextInt();
		// 일 처리 시간
		for(int i=0; i<N; i++) {
			task[i] = sc.nextInt();
		}
		
		// 일 처리 진행
		// 60분이 넘으면 시간 +1
		for(int i=0; i<N; i++) {
			M = M + task[i];
			if(M >= 60) {
				// 시간 계속 갱신
				while(M >=60) {
					T++;
					M = M - 60;
				}
			}
	// 24시가 넘었다면 24시를 빼서 0시부터 시작하는 것으로 변경
			if(T>=24) {
				T = T-24;
			}
		}

		System.out.printf("%d %d", T, M);
	}
}