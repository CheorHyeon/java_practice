import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bufferedReader.readLine());
		int[] A = new int[N];
		// 수열 채우기
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(bufferedReader.readLine());
		}
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		// 오름차순 수
		int num = 1;
		boolean result = true;
		// 만들어야 하는 수열 만들수 있는지 검사
		for(int i = 0; i < A.length; i++) {
			// 수열의 숫자 추출
			int su = A[i];
			// 수열의 숫자가 연속된 숫자보다 크다면
			if(su >= num) {
				// 같아질 때 까지 스택 추가
				while (su >= num) {
					stack.push(num++);
					sb.append("+\n");
				}
				// 같아졌을 때 꺼내기
				stack.pop();
				sb.append("-\n");
			}
			// 수열의 수 < 연속된 숫자 => 이러면 이제 스택에서 꺼냈을때 딱 맞아야 함
			else {
				int n = stack.pop();
				if(n != su) {
					System.out.println("NO");
					result = false;
					break;
				}
				else {
					sb.append("-\n");
				}
			}
		}
		if(result)
			System.out.println(sb.toString());
		bufferedReader.close();
	}
}