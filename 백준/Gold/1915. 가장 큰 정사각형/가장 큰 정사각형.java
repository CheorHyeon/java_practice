import java.util.*;

public class Main {
    public static void main(String[] args) {
        long[][] D = new long[1001][1001];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long max = 0;
        for(int i = 0; i < n; i++) {
            // 1. 각 행 데이터 입력 받기
            String mline = sc.next();
            for (int j = 0 ; j < m; j++) {
                D[i][j] = Long.parseLong(String.valueOf(mline.charAt(j)));
                // 2. 1행부터 DP 수행 -> 1이면 수행 / 0이면 0
                if(D[i][j] == 1 && j > 0 && i > 0) {
                    D[i][j] = Math.min(D[i - 1][j - 1], Math.min(D[i - 1][j], D[i][j - 1])) + D[i][j];
                }
                if(max < D[i][j]) {
                    max = D[i][j];
                }
            }
        }
        // 3. 정사각형 넓이 출력
        System.out.println(max * max);
    }
}