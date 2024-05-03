import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // dp[N][L][R] : N개 수열을 수행했고, 왼쪽 L 오른쪽 R에 있을 때 최소 누적 힘
        int dp[][][] = new int[100_001][5][5];
        // mp[i][j] : 한 발을 i에서 j로 이동할 때 드는 힘 미리 저장
        // 자기자신 : 1, 중앙 -> 다른지점 : 2, 인접한 곳 : 3, 반대방향 : 4
        int mp[][] = {
            {0, 2, 2, 2, 2},
            {2, 1, 3, 4, 3},
            {2, 3, 1, 3, 4},
            {2, 4, 3, 1, 3},
            {2, 3, 4, 3, 1}
        };
        // 1. dp배열 초기화 : 충분히 큰수로
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                for(int k = 0; k < 100_001; k++)
                    dp[k][i][j] = 400_000_000;
        // 초기값은 0으로 초기화
        dp[0][0][0] = 0;
        
        // 2. n개 수열동안 진행
        int n = 0;
        int N = 1; // 현재 진행한 수열 개수(구해야 할 수열)
        while (true) {
            n = sc.nextInt();
            // 0을 만나면 종료
            if(n == 0) {
                break;
            }
            // 왼발 고정 -> 오른발 옮기면서 최소값으로 갱신
            for(int l = 0; l < 5; l++) {
                // 현재 오른발을 움직여 n의 위치로 옮기는 경우인데, 옮기는 위치가 l과 같으면
                // 양발이 있는 경우라 패스
                if(n == l)
                    continue;
                for(int r = 0; r < 5; r++) {
                    // 오른발을 옮겨 현재 모습이 됐을 때 최소 힘 저장
                    // 전 위치의 오른발이 j + (j->n 이동 힘)
                    dp[N][l][n] = Math.min(dp[N - 1][l][r] + mp[r][n], dp[N][l][n]);
                }
            }
            
            // 오른발 고정 -> 왼발 옮기면서 최소값으로 갱신
            for(int r = 0; r < 5; r++) {
                // 현재 왼발을 움직여 n의 위치로 옮기는 경우인데, 옮기는 위치가 r과 같으면
                // 양발이 있는 경우라 패스
                if(n == r) {
                    continue;
                }
                for(int l = 0; l < 5; l++) {
                    // 왼발을 옮겨 현재 모습이 됐을 때 최소 힘
                    // 전 위치의 왼발 l + (l -> n 이동 힘)
                    dp[N][n][r] = Math.min(dp[N - 1][l][r] + mp[l][n], dp[N][n][r]);
                }
            }
            // N개 수열 진행 완료했으니 다음 루프때 증가
            N++;
        }
        // 마지막때 N + 1 값을 갖고있기에 1을 빼준다.
        N--;
        
        // 3. dp[N][][] 중 최소값 찾기
        // 지나간 경로만 값이 MAX값이 아니기때문에 MAX가 아닌것 중 최소를 찾으면 됨
        // 왼발 or 오른발 고정 == n 위치일땐 continue했으니 아마 그부분만 제외될듯
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                min = Math.min(min, dp[N][i][j]);
            }
        }
        // 4. 출력
        System.out.println(min);
        
    }
}