import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        // 1. 배열 초기화
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // dp[i] : i번째 입력 시 될 수 있는 최대 합
        int[] dp = new int[N];
        // 제일 첫번째 입력값은 자기 자신이 최대 값
        dp[0] = arr[0];
        // 2. dp 실행 
        int max = dp[0];
        for(int i = 1; i < N; i++) {
            // 이전까지 최대에 더한것 vs 자기 자신
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]); 
            max = Math.max(max, dp[i]);  // 최대값 갱신
        }
        // 3. 출력
        System.out.println(max);
        
    }
}