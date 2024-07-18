import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
	public int[] solution(long begin, long end) {
		List<Long> result = new ArrayList<>();
		// 1. 해당 위치에 나올 수 있는 수 구해서 배열에 넣기
		for(long position = begin; position <= end; position++ ) {
			// 첫번째 자리는 0 고정
			if(position == 1) {
				result.add(0L);
				continue;
			}
			// 1-1. 가능한 약수의 쌍 구하기
			List<long[]> pairs = genPairsAndOrderList(position);
			// 1-2. 약수가 1개일 경우 -> 1 x position 형태 -> 1 까는 경우의 수 뿐
			if(pairs.size() == 1) {
				result.add(1L);
				continue;
			}
			// 1-3. 약수가 2개 이상 -> 가장 큰 값을 저장
			result.add(pairs.get(0)[0]);
		}
		// 2. 반환하기
		return result.stream().mapToInt(Long::intValue).toArray();
	}

	private List<long[]> genPairsAndOrderList(long number) {
		List<long[]> result = new ArrayList<>();
		// 1. 1과 자기자신 쌍 넣고 시작
		result.add(new long[] {1, number});
		// 2. 2부터 제곱근까지 검사
		for(int i = 2; i <= Math.sqrt(number); i++) {
			// 길이 10억, 최대 숫자 1천만
			// -> 100 * 1천만 이 최대 니까, 나누는 수는 무조건 1천만보다 작음
			// -> 그래서 나눈 결과가 1천만 이하인지 확인
			if(number % i == 0) {
				// 나눠 떨어질때 앞에 큰수가 오도록
				long max = Math.max(number / i, i);
				long min = Math.min(number / i, i);
				if(max > 10_000_000) {
					result.add(new long[]{min, max});
				}
				else {
					result.add(new long[] {max, min});
				}
			}
		}
		// 1과 자기자신 뿐이면 그냥 반환
		if(result.size() == 1) {
			return result;
		}
		// 첫번째 수 기준 정렬 후 반환
		Collections.sort(result, (o1, o2) -> Math.toIntExact(o2[0] - o1[0]));
		return result;
	}
}