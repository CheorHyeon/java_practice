import java.util.Scanner;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        for(int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        Arrays.sort(A);
        int M = sc.nextInt();
        for(int i = 0; i < M; i++) {
            boolean find = false;
            int target = sc.nextInt();
            // 이진 탐색 시작
            int start = 0;
            int end = A.length - 1;
            // 가장 마지막으로 찾을 경우는 start와 end가 같은 mid일때
            // 그 경우에도 target과 대소비교하여 start나 end의 값을 변경하기 때문에 반복문 조건 적절
            while(start <= end) {
                int mid_index = (start + end) / 2;
                int mid_value = A[mid_index];
                if(mid_value > target) {
                    end = mid_index - 1;
                }
                else if (mid_value < target) {
                    start = mid_index + 1;
                }
                else {
                    find = true;
                    break;
                }
            }
            if(find) System.out.println(1);
            else System.out.println(0);
        }
    }
}