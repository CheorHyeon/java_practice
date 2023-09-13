import java.util.*;
import java.util.stream.Collectors;

class Solution {
	public String solution(int[] numbers) {
		String[] numStrs = new String[numbers.length];

		for(int i=0; i<numbers.length; i++) {
			numStrs[i] = numbers[i] + "";
		}
		// 모든 숫자를 비교함, 두 비교 대상의 숫자의 조합으로 비교하여 큰 수가 앞
		// 람다식에서 들어온 순서 반대로 적으면 내림차순
		Arrays.sort(numStrs, (o1, o2) -> (o2+o1).compareTo(o1+o2));

        // 0으로 시작하면 값은 0이니깐 0 반환
		if(numStrs[0].equals("0")) return "0";
        
		return Arrays.stream(numStrs).collect(Collectors.joining());
	}
}