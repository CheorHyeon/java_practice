import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[10001];
        // 1. 데이터 입력받기
        for(int i = 1; i <= N; i++) {
            A[Integer.parseInt(br.readLine())]++;
        }
        br.close();
        // 2. 순회하며 배열의 값이 0이 아닌 배열 출력하기
        for(int i = 1; i < A.length; i++) {
            int tmp = A[i];
            if(tmp != 0) {
                for(int j = 1; j <= tmp; j++) {
                    bw.write(i + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }
}