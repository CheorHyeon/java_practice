import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String input = bufferedReader.readLine();
		int N = Integer.parseInt(input.split(" ")[0]);
		int K = Integer.parseInt(input.split(" ")[1]);

		int[] coins = new int[N];

		for (int i = 0; i < coins.length; i++) {
			coins[i] = Integer.parseInt(bufferedReader.readLine());
		}
		int[] sorts = Arrays.stream(coins)
			.boxed()
			.sorted(Collections.reverseOrder())
			.mapToInt(Integer::intValue)
			.toArray();

		int count = 0;

		for (int coin : sorts) {
			count += K / coin;
			K = K % coin;
		}
		System.out.println(count);
	}
}