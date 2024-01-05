import java.util.*;

class Solution {
	List<String> answerList = new ArrayList<>();
	Map<String, Integer> hashMap = new HashMap<>();

	public String[] solution(String[] orders, int[] course) {

		// 1. 각 Order 정렬 : 각 Order는 오름차순 정렬 결과여야 함
		for(int i = 0; i < orders.length; i++) {
			char[] arr = orders[i].toCharArray();
			Arrays.sort(arr);
			orders[i] = String.valueOf(arr);
		}

		// 2. 각 order를 기준으로 courseLength 만큼의 조합 만들기
		for(int courseLength : course) {
			// 조합 생성
			for(String order : orders) {
				combination("", order, courseLength);
			}
			// 3. 가장 많은 조합 answer에 저장
			if(!hashMap.isEmpty()) {
				List<Integer> countList = new ArrayList<>(hashMap.values());
				int max = Collections.max(countList);

				// 2가지 이상 나오는 조합이 있다면 최대값에 맞는 조합 추가
				if(max > 1) {
					for(String key : hashMap.keySet()) {
						if(hashMap.get(key) == max){
							answerList.add(key);
						}
					}
				}
				// 조합 비워주기
				hashMap.clear();
			}
		}
		
		Collections.sort(answerList);
		String[] answer = new String[answerList.size()];
		for(int i = 0; i < answer.length; i++) 
			answer[i] = answerList.get(i);
		return answer;
		
		// return answerList.stream()
		// 	.sorted()
		// 	.map(String::valueOf)
		// 	.toArray(String[]::new);
	}

	private void combination(String order, String others, int count) {
		// 탈출 조건
		if(count == 0) {
			// 조합의 수를 증가 시키기
			hashMap.put(order, hashMap.getOrDefault(order, 0) + 1);
			return ;
		}

		// 0부터 length까지 조합
		for(int i = 0; i < others.length(); i++)
			combination(order + others.charAt(i), others.substring(i+1), count - 1);
	}
}