import java.util.*;
import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);
    }
    
    public static void dfs(int number, int jarisu) {
        // 종료 조건 : N자리수의 숫자일 시 종료, 소수가 맞다면 출력하고 종료
        if(jarisu == N) {
            if(isPrime(number)) {
                System.out.println(number);
            }
            return ;
        }
        for(int i = 1; i < 10; i++) {
            // 짝수는 소수가 아니기에 넘어가기
            if(i % 2 == 0) {
                continue;
            }
            if(isPrime(number * 10 + i)) {
                dfs(number * 10 + i, jarisu + 1);
            }
        }
    }
    
    static boolean isPrime(int num) {
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) 
                return false;
        }
        return true;
    }
}