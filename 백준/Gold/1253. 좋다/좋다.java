import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        long A[] = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 1. 입력값 배열 초기화
        for(int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        // 2. 배열 정렬
        Arrays.sort(A);
        // 3. 투 포인터 알고리즘 이용
        for(int k=0; k < N; k++) {
            long find = A[k];
            int start = 0;
            int end = N-1;
            while(start < end) {
                if(A[start] + A[end] == find) {
                    // 서로 다른 두 수의 합인지 체크(본인 포함 불가)
                    if(start != k && end !=k) {
                        // "좋아!" 외칠 횟수 증가시키고 다음 수 탐색
                        result++;
                        break;
                    }
                    else if(start == k) {
                        start++;
                    }
                    // end는 끝에서 앞으로 이동함
                    else if(end == k) {
                        end--;
                    }
                }
                else if(A[start] + A[end] > find) {
                    end--;
                }
                // A[start] + A[end] < find
                // 작으니까 합을 키워야하니깐 왼쪽부분의 인덱스를 증가
                else {
                    start++;
                }
                
            }
        }
        
        // 4. 결과 출력
        System.out.println(result);
    }
}