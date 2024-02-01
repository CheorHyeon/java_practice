import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int M = in.nextInt();
        int N = in.nextInt();
        int[] A = new int[N+1];
        for(int i = 2; i <= N; i++) {
            A[i] = i;
        }
        for(int i=2; i <= (int)Math.sqrt(N); i++) {
            // 소수이면 pass
            if(A[i] == 0) continue;
            // 배수 검사(2i 부터 시작 -> 3i -> 4i ...)
            for(int j = i + i; j <= N; j = j + i) {
                A[j] = 0;
            }
        }
        // 시작 숫자부터 소수 아닌것 탐색
        for(int i = M; i <= N; i++) {
            if(A[i] !=0) {
                System.out.println(A[i]);
            }
        }
    }
}