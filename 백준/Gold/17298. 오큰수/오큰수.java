import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n]; // 수열 배열
        int[] ans = new int[n]; // 오큰수 배열
        String[] str = br.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(str[i]); // 수열 초기화
        }
        Stack<Integer> stack = new Stack<>();
        // 처음에 스택이 항상 비워있기에 최초값 push
        stack.push(0);
        
        for(int i = 1; i < n; i++) {
            // 스택이 비어 있지 않고 현재 수열이 스택 top 인덱스가 가리키는 수열보다 클 경우
            // 우큰수 갱신
            while(!stack.isEmpty() && A[stack.peek()] < A[i]) {
                ans[stack.pop()] = A[i];
            }
            // 신규 데이터 push(해당 데이터도 우큰수를 구해야 하니 인덱스 삽입)
            stack.push(i);
        }
        // 우큰수가 존재하지 않는 인덱스들 -1 대입
        while(!stack.isEmpty()) {
            ans[stack.pop()] = -1;
        }
        // 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < n; i++) {
            bw.write(ans[i] + " ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
        
        
    }
}