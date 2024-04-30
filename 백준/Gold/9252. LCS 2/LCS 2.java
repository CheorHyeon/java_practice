import java.util.*;
import java.io.*;

public class Main {
    private static long[][] DP;
    private static ArrayList<Character> Path;
    private static char[] A;
    private static char[] B;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1. 두 문자열 입력받기
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();
        DP = new long[A.length + 1][B.length + 1];
        Path = new ArrayList<Character>();
        // 2. DP 점화식 도입
        for(int i = 1; i <= A.length; i++) {
            for(int j = 1; j <= B.length; j++) {
                // 문자열이 같다면 이전 기록 + 1
                if(A[i - 1] == B[j - 1]) {
                    DP[i][j] = DP[i-1][j-1] + 1; // 이전 대각선 값 + 1
                }
                // 문자열이 다르다면 둘 중 하나만 포함했을 때 큰 값과 동일한 결과
                else {
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]); 
                }
            }
        }
        // 3. 최대 LCS 출력
        System.out.println(DP[A.length][B.length]);
        // 4. LCS 문자 생성
        getText(A.length, B.length);
        // 5. LCS 문자 출력
        for(int i = Path.size() - 1; i >= 0; i--) {
            System.out.print(Path.get(i));
        }
    }
    private static void getText(int r, int c) {
        if(r == 0 || c == 0) return ;
        // 문자 같은 경우 -> 정답에 추가하고 왼쪽 대각선 위로 이동
        if(A[r - 1] == B[c - 1]){
            Path.add(A[r-1]);
            getText(r - 1, c - 1);
        }
        // 다르면 왼쪽과 위쪽 중 큰 수로 이동
        else {
            if(DP[r - 1][c] > DP[r][c - 1]) {
                getText(r - 1, c);
            }
            else {
                getText(r, c-1);
            }
        }
    }
}