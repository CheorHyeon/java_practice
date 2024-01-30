import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int[] A = new int[str.length()];
        // 1. 각 자리수 저장
        for(int i = 0; i < str.length(); i++) {
            A[i] = Integer.parseInt(str.substring(i, i+1));
        }
        // 2. 선택정렬
        for(int i = 0; i < str.length(); i++) {
            int max = i;
            for(int j = i+1; j < str.length(); j++) {
                // 2-1. 정렬 안된 부분에서 최대값을 찾음
                if(A[j] > A[max]) {
                    max = j;
                }
            }
            // 2-2. 가장 큰 값과 위치 변경
            if(A[i] < A[max]) {
                int temp = A[i];
                A[i] = A[max];
                A[max] = temp;
            }
        }
        
        // 3. 출력
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i]);
        }
    }
}