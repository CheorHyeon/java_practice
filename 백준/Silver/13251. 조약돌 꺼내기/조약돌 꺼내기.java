import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int M, K, T;
        int D[] = new int[51];
        double probability[] = new double[51];
        double ans = 0.0;
        T = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 색 종류 수 입력받기
        st = new StringTokenizer(br.readLine());
        // 1. 색깔별 조약돌의 개수
        for(int i = 0; i < M; i++) {
            D[i] = Integer.parseInt(st.nextToken());
            T += D[i]; // 전체 조약돌의 개수
        }
        st = new StringTokenizer(br.readLine());
        // 2. K개의 조약돌 뽑기
        K = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++) {
            // 색깔별로 가진 공의 수가 뽑아야 할 조약돌의 수보다 많을 경우에만 확률 계산
            // 그래야 뽑은 전체 색이 K개를 만족시킬 수 있음
            if(D[i] >= K) {
                probability[i] = 1.0;
                for(int k = 0; k < K; k++) {
                    // K = 2 -> 5개 조약돌 색깔 확률 : 5/18 * 4/17
                    probability[i] *= (double) (D[i] - k) / (T - k);
                }
            }
            ans += probability[i];
        }
        // 3. 결과 출력
        System.out.println(ans);
    }
}