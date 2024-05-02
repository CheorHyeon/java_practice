import java.util.*;
public class Main {
    static long mod = 1_000_000_007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();
        long D[][][] = new long[101][101][101];
        D[1][1][1] = 1; // 빌딩 1개면 놓을 수 있는 경우의 수 1개
        for(int i = 2; i <= N; i++) {
            for(int j = 1; j <= L; j++) {
                for(int k = 1; k <= R; k++) {
                    // 가장 짧은 건물을 가운데에 넣는 경우 : i-2개 (가장 좌, 우 제외)
                    // 오른쪽에 1개를 추가하는 경우
                    // 왼쪽에 1개를 추가하는 경우
                    D[i][j][k] = (D[i - 1][j][k] * (i - 2) + D[i - 1][j][k - 1] + D[i - 1][j - 1][k]) % mod;
                }
            }
        }
        System.out.println(D[N][L][R]);
    }
}