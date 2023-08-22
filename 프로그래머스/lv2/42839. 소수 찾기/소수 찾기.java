import java.util.HashSet;

class Solution {
	HashSet<Integer> set = new HashSet<>();

	public void recursive(String com, String others) {
		if (!com.equals("")) {
			set.add(Integer.parseInt(com));
		}

		for (int i = 0; i < others.length(); i++) {
			recursive(com + others.charAt(i), others.substring(0, i) + others.substring(i + 1));
		}
	}

	public int solution(String numbers) {
		int count = 0;
		// 모든 조합을 정수화 -> Set에 넣어서 중복 제거(재귀)
		recursive("", numbers);
		// 순회하며 소수인지 판별, 소수 시 count 1증가
		for (int a : set) {
			// 에라토스테네스의 체 (어떤 수가 소수인 지 보려면 제곱근 까지 나눠봐도 된다)
			if(isPrime(a)) {
				count++;
			}
		}
		return count;
	}

	private boolean isPrime(int number) {
		if(number == 0 || number == 1)
			return false;
		
		if(number == 2)
			return true;
		
		int limit = (int)Math.sqrt(number);
		
		for(int i=2; i<=limit; i++) {
			if(number % i == 0)
				return false;
		}
		
		return true;
	}
}