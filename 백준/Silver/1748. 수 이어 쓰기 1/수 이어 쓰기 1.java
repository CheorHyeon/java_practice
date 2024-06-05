import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 1. 10미만일땐 반환
        if(N < 10 ) {
            System.out.println(N);
            return;
        }
        
        // 2. 10부터는 아래 부분
        int totalCount = 11;
        int numberCount = 2; // 각 숫자별로 몇자리인지 나타냄. 10부터 시작 -> 2자릿수
        int criteria = 100; // 10의 제곱형태로 나눠지면 자릿수가 바뀜 ex) 10 % 10 -> 2자리, 100 % 100 -> 3자리
        for(int i = 11; i <= N; i++) {
            if(i % criteria == 0) {
                numberCount += 1; // 10의 제곱수로 나눠질때면 자릿수 변경 처리
                criteria = criteria * 10; // 다음 n+1의 제곱 기준 나타냄
            }
            totalCount += numberCount; // 현재 숫자의 자릿수를 더해준다.
        }
        
        // 3. 출력
       System.out.println(totalCount);
    }
}