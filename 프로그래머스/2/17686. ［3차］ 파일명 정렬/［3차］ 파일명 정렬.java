import java.util.Arrays;
import java.util.Comparator;

class Solution {
	public String[] solution(String[] files) {
		Arrays.sort(files, new filesComparator());
		return files;
	}
}

class filesComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		// Head 추출
		String head1 = s1.split("[0-9]")[0].toUpperCase();
		String head2 = s2.split("[0-9]")[0].toUpperCase();
		int compareHead = head1.compareTo(head2);
		/*
			정렬 기준 1 : 헤드 기준 사전 순 정렬
			정렬 기준 2 : 헤드가 같다면 number의 숫자 순 정렬
			정렬 기준 3 : 1, 2가 같다면 순서 유지

			- 헤드 차이가 0 : 헤드가 같다는 의미 -> 정렬 기준 2, 3 고려
			  - 정렬 기준 2 : number 비교
			  - 정렬 기준 3 : number도 같으면 0이니깐 동일함을 반환함

			- 헤드 차이가 0이 아니라면 : 헤드가 다르단 의미 -> 정렬 기준 1 고려
		 */
		return compareHead == 0 ? getNumber(s1.substring(head1.length())) - getNumber(s2.substring(head2.length())) :
			compareHead;
	}

	/*
		헤드 이후에 Number 부분 추출, 0~9999 연속으로 붙어있으니 길이는 5 미만
		숫자인지 Character.isDigit() 메서드로 확인
	 */
	public int getNumber(String s) {
		StringBuilder result = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c) && result.length() < 5) {
				result.append(c);
			} else
				break;
		}
		return Integer.parseInt(result.toString());
	}
}