import java.util.Scanner;
public class Main {
    static int N, K;
    static int[][] D;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        D = new int[N+1][N+1];
        // 1. 배열 초기화
        for(int i = 0; i <= N; i++) {
            D[i][1] = i; // 5C1 => 5, 4C1 => 4
            D[i][0] = 1; // 5C0 => 1, 4C0 => 1
            D[i][i] = 1; // 5C5 -> 1, 4C4 -> 1
        }
        // 2.점화식 수행
        for(int i = 2; i <= N; i++) { // 배열 초기화에서 1개 가능한 모든 경우 구함 1C1, 1C0
            for(int j = 1; j < i; j++) { // 배열 초기화에서 [i][i]까진 이미 구했음
                D[i][j] = D[i-1][j] + D[i-1][j-1];
            }
        }
        // 3. 출력
        System.out.println(D[N][K]);
    }
}