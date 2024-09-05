import java.util.*;

class Solution {
	Map<String, Person> personMap = new HashMap<>();

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		// 1. 계층 관계 객체 생성
		for (int i = 0; i < enroll.length; i++) {
			String name = enroll[i];
			String referralName = referral[i];
			Person person = new Person();
			person.name = name;
			if (!referralName.equals("-")) {
				person.parentName = referralName;
			}
			personMap.put(name, person);
		}
		// 2. 판매 금액 보면서 금액 배분
		for (int i = 0; i < seller.length; i++) {
			String sellerName = seller[i];
			int sellMoney = amount[i] * 100;
			Person target = personMap.get(sellerName);
			// 1~100 개 팔았으니 일단 무조건 90퍼로 먹고들어감
			target.money += (int)(sellMoney * 0.9);
			// 부모가 있으면 10퍼 넘기기, 만약 없는 녀석들이라면 그냥 본사에 헌납한 것으로 고려 x
			if (target.parentName != null) {
				tenPercent(target.parentName, (int)(sellMoney * 0.1));
			}
		}
		// 3. 결과 반환
		int[] resultArr = new int[enroll.length];
		for (int i = 0; i < enroll.length; i++) {
			resultArr[i] = personMap.get(enroll[i]).money;
		}
		return resultArr;
	}

	private void tenPercent(String parentName, int incentive) {
		while (true) {
			// 1. 부모 찾기
			Person parent = personMap.get(parentName);
			// 2. 90% 먹고
			int nextParentParent = (int)(incentive * 0.1);
			parent.money += (incentive - nextParentParent);
			// 3-1. 만약 1원 미만의 돈을 상위에 넘기는 셈이라면 종료시키기
			// 부모가 없으면 -> 자기가 마지막이니 만약 넘길 돈이 있어도 그냥 센터에만 주면되니 그냥 함수 끝
			if (nextParentParent == 0 || parent.parentName == null) {
				break;
			}
			// 3-2. 부모가 있으면서 계속 분배금 분배 가능하면 재귀 호출(값 갱신)
			parentName = parent.parentName;
			incentive = nextParentParent;
		}
	}

}

class Person {
	String name;
	int money;
	String parentName;
}