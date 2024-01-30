import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] S = new int[N];
        // 배열에 숫자 넣기
        for(int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        // 삽입 정렬
        for(int i = 1; i < N; i++) {
            int insert_point = i;
            int insert_value = A[i];
            for(int j = i - 1; j >= 0; j--) {
                // 최초로 타겟이 더 큰곳이 나오면 그 나온 위치 다음 위치가 삽입 위치
                if(A[j] < A[i]) {
                    insert_point = j + 1;
                    break;
                }
                // 여기까지 안온건 다 A[j]가 크다는 뜻이니 0번째 인덱스에 삽입
                if(j == 0) {
                    insert_point = 0;
                }
            }
            // 삽입할 위치 전까지 shift
            for(int j = i; j > insert_point; j--) {
                A[j] = A[j-1];
            }
            A[insert_point] = insert_value;
        }
        S[0] = A[0];
        // i번째 사람이 인출에 걸린 총 시간(대기 + 작업)
        for(int i = 1; i < N; i++) {
            S[i] = S[i-1] + A[i];
        }
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += S[i];
        }
        System.out.println(sum);
    }
}