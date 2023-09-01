import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Main {
	public static class Fruit implements Comparable<Fruit> {
		long slice;
		long fullness;

		public long getSlice() {
			return this.slice;
		}

		public long getFullness() {
			return this.fullness;
		}

		Fruit(long slice, long fullness) {
			this.slice = slice;
			this.fullness = fullness;
		}

		@Override
		public int compareTo(Fruit o) {
			return Long.compare(this.fullness, o.fullness);
		}
	}

	public static void main(String[] args) throws Exception {
		// 1. P1, C1 담는 class 생성(개수, 1조각 당 포만감)
		// 2. 포만감 순으로 정렬한 set 생성
		// 2-1. 포만감과 같은 class 리스트생성
		// 2-2. 구매 가능하면 구매
		// 3. 통으로 구매 불가능 하면 여러 조각 구매
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		long K = Long.parseLong(input[1]);
		long result = 0;
		List<Fruit> list = new ArrayList<>();
		// 리스트 생성
		for (int i = 0; i < N; i++) {
			String[] tmpInput = br.readLine().split(" ");
			long slice = Long.parseLong(tmpInput[0]);
			long fullness = Long.parseLong(tmpInput[1]);
			list.add(new Fruit(slice, fullness / slice));
		}

		// 포만감 1순위 정렬 기준, 같으면 slice가 낮은거(그래야 가성비가 좋으니 최선의 선택)
		list = list.stream()
			.sorted(Comparator.comparingLong(Fruit::getFullness).reversed()
				.thenComparing(Comparator.comparingLong(Fruit::getSlice)))
			.collect(Collectors.toList());

		for (Fruit a : list) {
			// 통으로 살 수 있는지 검사
			if (K >= a.slice) {
				result += a.fullness * a.slice;
				K -= a.slice;
			}
			// 살 수 있는것 모두 통으로 사고 났는데 돈이 남았다면 조각 최대한 구매
			else if (K > 0) {
				long count = a.getSlice();
				// 한 종류의 과일을 1개를 초과하여 사지 못하는 것이지, 조각은 여러개 구입 가능함
				while (K > 0 && count != 0) {
					result += a.fullness;
					K--;
					count--;
				}
			}
		}

		System.out.println(result);
	}
}