import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long[] S = new long[N]; // 합배열
        long[] C = new long[M]; // 나머지 카운트
        long answer = 0;
        S[0] = sc.nextInt();
        // 1. 합배열 생성
        for(int i = 1; i < N; i++) {
            S[i] = S[i-1] + sc.nextInt();
        }
        // 2. 합 배열의 모든 값에 % 연산 수행하기
        // (A + B) % C = ((A % C) + (B % C)) % C 라는 공식 이용
        // 문제 지문에 연속된 구간(부분합) : S[j] - S[i] => i+1 ~ j번째 구간의 합 의미
        // (S[j] - S[i]) % M = 0 -> ((S[j] % M) + (S[i] % M) ) % M == 0 (주어진 문제를 수식으로,
        // 연속된 구간의 합이 M으로 나눠떨어진다는 뜻을 위 공식에 적용시킴)
        // S[j] % M == S[i] % M 도 성립합을 알 수 있음. 따라서 S 구한 원소에 M으로 나눠준다.
        for(int i = 0; i < N; i++) {
            int reminder = (int) (S[i] % M);
            // 만일 합배열의 값이 0이라면, 0~ i번째까지의 연속된 배열의 합이 M으로 나눠떨어진다는 뜻
            if(reminder == 0) answer++;
            C[reminder]++;
        }
        
        // 3. 나머지가 같은 요소들의 조합을 추가로 더해준다.
        // ex) S[1] = 0, S[3] = 0, S[4] = 0
        // 1~3 구간의 합, 1~4 구간의합, 3~4 구간의합도 나머지가 0임을 보장하니 3개중 2개 추출 => 조합 공식 이용
        for(int i = 0; i < M; i++) {
            if(C[i] > 1) {
                // 나머지가 같은 인덱스 중 2개를 뽑는 경우의 수 더하기
                answer = answer + (C[i] * (C[i] - 1) / 2);
            }
        }
        
        // 4. 답 출력
        System.out.println(answer);
    }
}