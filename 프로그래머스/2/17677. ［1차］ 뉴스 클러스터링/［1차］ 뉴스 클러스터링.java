import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public int solution(String str1, String str2) {
		// 대소문자 구분 없게 하기 위함
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		// 1. str1, 2의 다중 집합
		List<String> multipleSet1 = new ArrayList<>();
		List<String> multipleSet2 = new ArrayList<>();

		// 다중집합 추출용 배열
		char[] str1CharArr = str1.toCharArray();
		for (int i = 0; i < str1CharArr.length - 1; i++) {
			char target1 = str1CharArr[i];
			char target2 = str1CharArr[i + 1];
			if (((target1 >= 'a' && target1 <= 'z'))) {
				if (((target2 >= 'a' && target2 <= 'z'))) {
					String elementSet1 = String.valueOf(target1) + String.valueOf(target2);
					System.out.println("1번 : " + elementSet1);
					multipleSet1.add(elementSet1);
				}
			}
		}
		char[] str2CharArr = str2.toCharArray();
		for (int i = 0; i < str2CharArr.length - 1; i++) {
			char target1 = str2CharArr[i];
			char target2 = str2CharArr[i + 1];
			if (((target1 >= 'a' && target1 <= 'z'))) {
				if (((target2 >= 'a' && target2 <= 'z'))) {
					String elementSet2 = String.valueOf(target1) + String.valueOf(target2);
					System.out.println("2번 : " + elementSet2);
					multipleSet2.add(elementSet2);
				}
			}
		}
		multipleSet1 = multipleSet1.stream().sorted().collect(Collectors.toList());
		multipleSet2 = multipleSet2.stream().sorted().collect(Collectors.toList());

		// 공집합일 경우 유사도 1이기에 바로 리턴
		if(multipleSet1.isEmpty() && multipleSet2.isEmpty()) {
			return 65536;
		}

		List<String> interSectionList = new ArrayList<>();
		int smallStrartIndex = 0;
		if (multipleSet1.size() >= multipleSet2.size()) {
			for (int i = 0; i < multipleSet1.size(); i++) {
				String tmp = multipleSet1.get(i);
				for (int j = smallStrartIndex; j < multipleSet2.size(); j++) {
					String smallTmp = multipleSet2.get(j);
					// 교집합 추가 및 작은쪽 다음꺼 가르키도록
					if (tmp.equals(smallTmp)) {
						interSectionList.add(tmp);
						// 다음꺼부터 검사하면 됨(이미 정렬 해둬서 다음꺼의 교집합은 이 이후부터)
						smallStrartIndex = j+1;
						break;
					}
				}
			}
		} else {
			for (int i = 0; i < multipleSet2.size(); i++) {
				String tmp = multipleSet2.get(i);
				for (int j = smallStrartIndex; j < multipleSet1.size(); j++) {
					String smallTmp = multipleSet1.get(j);
					// 교집합 추가 및 작은쪽 다음꺼 가르키도록
					if (tmp.equals(smallTmp)) {
						interSectionList.add(tmp);
						// 다음꺼부터 검사하면 됨(이미 정렬 해둬서 다음꺼의 교집합은 이 이후부터)
						smallStrartIndex = j+1;
						break;
					}
				}
			}
		}
		// 교집합 개수
		int intersectionSize = interSectionList.size();
		// 교집합이 아닌 원소의 수
		int notIntersectionSet1Count = multipleSet1.size();
		int notIntersectionSet2Count = multipleSet2.size();
		// 교집합 개수만큼 빼주기 -> 중복 해당되는 것들은 교집합 만큼만 삭제됨
		for(String interSection : interSectionList) {
			if(multipleSet1.contains(interSection)){
				notIntersectionSet1Count--;
			}
			if(multipleSet2.contains(interSection)){
				notIntersectionSet2Count--;
			}
		}

		// 3. 자카드 유사도 * 65536 결과를 int로 반환
		return (int)(((double) intersectionSize / (notIntersectionSet2Count + notIntersectionSet1Count + intersectionSize)) * 65536);
	}
}