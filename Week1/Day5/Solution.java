import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] temp = input.split(" ");

		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);

		String input2 = br.readLine();
		String[] temp2 = input2.split(" ");

		int[] originNumber = new int[N];
		for (int i = 0; i < temp2.length; i++) {
			originNumber[i] = Integer.parseInt(temp2[i]);
		}

		int[] countArray = new int[originNumber.length];
		for (int i = 0; i < countArray.length; i++) {
			countArray[i] = Integer.bitCount(originNumber[i]);
		}

		List<Integer> list = Arrays.stream(countArray).boxed().collect(Collectors.toList());
		List<Integer> sortedList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

		List<Integer> result = new ArrayList<>();

		while (sortedList.size() > 0) {
			Integer tmpCount = sortedList.get(0);
			int countSame = 0;

			for (int j = 0; j < sortedList.size(); j++) {
				if (sortedList.get(j).equals(tmpCount)) {
					countSame++;
				}
			}

			// 1의 개수가 같은게 2개 이상 -> 큰 순으로 저장 필요
			if (countSame >= 2) {
				List<Integer> sameIndex = new ArrayList<>();

				// 같은 개수의 인덱스 저장용
				for (int k = 0; k < list.size(); k++) {
					if (tmpCount.equals(list.get(k))) {
						sameIndex.add(k);
					}
				}

				// 같은 인덱스에 해당하는 값을 넣고 내림차순 정렬
				List<Integer> orderNumber = new ArrayList<>();
				for (int i = 0; i < sameIndex.size(); i++) {
					orderNumber.add(originNumber[sameIndex.get(i)]);
				}
				orderNumber.sort(Collections.reverseOrder());

				for (int i = 0; i < orderNumber.size(); i++) {
					result.add(orderNumber.get(i));
				}
				// 요소 하나를 가진 컬렉션화 하여 삭제
				sortedList.removeAll(Collections.singleton(tmpCount));
			}
			// 같은게 1개다 -> 1의 개수가 같은게 1개만 존재
			else if(countSame == 1){
				Integer a = sortedList.get(0);

				// 결과에 해당하는 값 넣기
				for (int i = 0; i < list.size(); i++) {
					if (a.equals(list.get(i))) {
						result.add(originNumber[i]);
						break;
					}
				}
				// 해당 요소 검사했으니 삭제
				sortedList.removeAll(Collections.singleton(tmpCount));
			}
		}

		System.out.println(result.get(K - 1));
	}
}