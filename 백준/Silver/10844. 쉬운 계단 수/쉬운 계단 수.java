import java.util.*;

public class Main {
	static long[][] dp;
	static long mod = 1_000_000_000;
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new long[N + 1][11];
		
		//1. 배열 초기화
		for(int i = 1; i < 10; i++) {
			dp[1][i]=1;
		}
		
		// 2. dp 저장
		for(int i = 2; i <= N; i++) {
			 for(int j = 0; j < 10; j++) {
				 if(j == 0) {
					 dp[i][j] = dp[i-1][1] % mod;
				 }
				 else {
					 dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
				 }
			 }
		}
		// 3. 출력
		System.out.println(Arrays.stream(dp[N]).sum() % mod);
		sc.close();
	}
}