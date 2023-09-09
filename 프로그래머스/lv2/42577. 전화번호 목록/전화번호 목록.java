import java.util.HashMap;

class Solution {
	public boolean solution(String[] phone_book) {
		// 1. HashMap을 만든다.
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0; i<phone_book.length; i++) {
			map.put(phone_book[i], 1);
		}
		// 2. 모든 전화번호의 접두어가 HashMap에 있는지 확인한다.
		for(int i=0; i< phone_book.length; i++) {
			for(int j=1; j< phone_book[i].length(); j++) {
				// 마지막 문자까지 substring을 하면 무조건 존재하니깐
				// 접두사 까지인것 확인하기 위해 length-1까지만
				if(map.containsKey(phone_book[i].substring(0, j))){
					return false;
				}
			}
		}
		// 3. 여기까지 왔다면 접두어가 없다는 것
		return true;
	}
}