import java.io.*;
import java.util.*;
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());
        br.close();
        long[] A = new long[10_000_001];
        // 1. 에레토스테네스의 체 이용한 최대 소수 범위 구하기
        for(int i = 2; i < A.length; i++) {
            A[i] = i;
        }
        // 제곱근 까지만 검사해도 가능
        for(int i = 2; i <= Math.sqrt(A.length); i++) {
            if(A[i] == 0)
                continue;
            // 배수 지우기
            for(int j = i + i; j < A.length; j = j + i) {
                A[j] = 0;
            }
        }
        int count = 0;
        // 2. 거의 소수 구하기
        for(int i = 2; i < 10_000_001; i++) {
            if(A[i] != 0) {
                long temp = A[i];
                // 거의 소수 조건 : A <= N^K <= B (k >= 2), long 범위 초과하는 경우 있음
                // A / N^k-1 <= N <= B / N^k-1 (k >= 2) => 2-1, 최소는 1제곱 부터 나눔
                // 따라서 처음에도 temp 그대로 나누기가 가능
                while((double)A[i] <= (double) max / (double)temp) {
                    if((double) A[i] >= (double) min / (double) temp) {
                        count++;
                    }
                    temp = temp * A[i];
                }
            }
        }
        // 3. 출력
        System.out.println(count);
    }
}