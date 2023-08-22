import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String count = br.readLine();
		int sum = 0;
		// 각각의 수식 계산
		for(int i=0; i<Integer.parseInt(count); i++) {
			String tmp = br.readLine();
			if(tmp.contains("+")) {
				String[] split = tmp.split(" \\+ ");
				sum += Integer.parseInt(split[0]) + Integer.parseInt(split[1]);
			}
			else if(tmp.contains("-")) {
				String[] split = tmp.split(" - ");
				sum += Integer.parseInt(split[0]) - Integer.parseInt(split[1]);
			}
			else if(tmp.contains("/")) {
				String[] split = tmp.split(" / ");
				int divideResult = (int)Math.floor((Float.parseFloat(split[0]) / Float.parseFloat(split[1])));
				sum += divideResult;
			}
			else if(tmp.contains("*")) {
				String[] split = tmp.split(" \\* ");
				sum += Integer.parseInt(split[0]) * Integer.parseInt(split[1]);
			}
		}
		System.out.println(sum);
	}
}