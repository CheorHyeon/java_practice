import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
	public int solution(int[] nums) {
		// 몬스터 중 중복 최소화 한 마리를 데려감 -> 중복 제거
		// 중복 제거를 했을 때 데려가려는 몬스터보다 많다면 데려갈 몬스터 전부 가져갈 수 있음
		// 데려가려는 몬스터보다 적다면, 적은 수만큼 + 중복 으로 데려갈 수 있음

		// 데려갈 몬스터 수
		int count = nums.length / 2;
		
		Set<Integer> numsSet = new HashSet<>(Arrays.stream(nums).boxed().collect(Collectors.toSet()));
		
		if(numsSet.size() >= count) {
			return count;
		}
		// 최대한 다른 몬스터를 가져가는 것이니, 중복을 빼고 다른 종 수만 리턴해도 값은 같음.
        // 실제로 가져가는 몬스터 수는 다른 종족 수 + 중복 몬스터 추가
		return numsSet.size();
	}
}