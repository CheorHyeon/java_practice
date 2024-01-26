import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1. 첫번째 줄 숫자 갯수 받기
        String N = br.readLine();
        int n = Integer.valueOf(N);
        // 2. 2번째 줄 총 숫자 받아 char형 배열로 변환
        String sNum = br.readLine();
        char[] cNum = sNum.toCharArray();
        int sum = 0;
        // 3. 해당 char형 변수 숫자로 변환하여 더하기
        for(char c : cNum) {
            sum += c - '0';
        }
        System.out.println(sum);
    }
}