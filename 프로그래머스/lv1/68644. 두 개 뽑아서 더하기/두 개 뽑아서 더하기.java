import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

class Solution {
	public int[] solution(int[] numbers) {
		// 가능한 모든 합의 조합 배열을 set -> 중복 없이
		// 정렬
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<numbers.length; i++) {
			for(int j=i+1; j<numbers.length; j++) {
				set.add(numbers[i] + numbers[j]);
			}
		}
		
		return set.stream().sorted().mapToInt(Integer::intValue).toArray();
	}
}