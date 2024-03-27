import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long mod = 1000000000;
        long D[] = new long[1000001]; // N명일 때 선물을 주고받을 경우의 수 저장
        D[1] = 0;
        D[2] = 1;
        for(int i = 3; i <= N; i++) {
            D[i] = (i - 1) * (D[i - 1] + D[i - 2]) % mod; // 점화식
        }
        System.out.println(D[N]);
    }
}