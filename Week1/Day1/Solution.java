import java.io.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		// 입력값 공백으로 분리
		String[] split = input.split(" ");
		// W와 R 추출
		int W = Integer.parseInt(split[0]);
		int R = Integer.parseInt(split[1]);
		// 계산 결과에서 소수점을 버리는 거니, 계산에는 정확하게
		float result = W * (1 + (float)R/30);
		System.out.println((int)Math.floor(result));
	}
}