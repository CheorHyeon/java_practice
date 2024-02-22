import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] A = new int[N][2];
        // 1. 회의 시간 저장
        for(int i = 0; i < N; i++) {
            A[i][0] = sc.nextInt();
            A[i][1] = sc.nextInt();
        }
        // 2. 회의 정렬
        Arrays.sort(A, (int[] o1, int[] o2) -> {
            // 종료 시간이 같다면 시작시간이 빠른 순
            if(o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            // 종료 시간 빠른 순
            return o1[1] - o2[1];
        });
        // 3. 탐색
        int count = 0;
        int end = -1;
        for(int i = 0; i < N; i++) {
            // 회의 시작 시간이 이전 회의의 종료 시간 이후라면 회의 가능
            if(A[i][0] >= end) {
                count++;
                end = A[i][1];
            }
        }
        // 4. 출력
        System.out.println(count);
    }
}