import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] D = new long[N+1][2];
        D[1][1] = 1; // 1자리 이친수는 1가지
        D[1][0] = 0; // 1은 1이므로 0이 들어가는 경우 없음
        for(int i = 2; i <= N; i++) {
            D[i][0] = D[i-1][0] + D[i-1][1]; // 0은 1이나 0으로 끝날때 모두 붙일 수 있음
            D[i][1] = D[i-1][0];  // 1은 0으로 끝날 경우에만 붙일 수 있음
        }
        System.out.println(D[N][0] + D[N][1]);
    }
}