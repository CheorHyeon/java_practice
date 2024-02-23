import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[10_000_001];
        // 1. 에레토스테네스의체 이용하여 소수 구하기
        for(int i = 2; i < A.length; i++) {
            A[i] = i;
        }
        for(int i = 2; i <= Math.sqrt(A.length); i++) {
            if(A[i] == 0)
                continue;
            // 배수 지우기
            for(int j = i + i; j < A.length; j = j + i) {
                A[j] = 0;
            }
        }
        int i = N;
        // 2. N이후 숫자부터 소수 & 펠린드롬 수 찾기
        while(true) {
            if(A[i] != 0) {
                int result = A[i];
                if(isPalindrome(result)) {
                    // 3. 출력
                    System.out.println(result);
                    break;
                }
            }
            // 다음 수 검사
            i++;
        }
    }
    public static boolean isPalindrome(int target) {
        String temp1 = String.valueOf(target);
        String temp2 = (new StringBuilder(temp1)).reverse().toString();
        if(temp1.equals(temp2)) {
            return true;
        }
        return false;
    }
}