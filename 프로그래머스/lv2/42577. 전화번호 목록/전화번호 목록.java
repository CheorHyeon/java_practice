import java.util.HashMap;

class Solution {
	public boolean solution(String[] phone_book) {
		// 1. 해시맵화
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0; i<phone_book.length; i++) {
			map.put(phone_book[i], 1);
		}

		// 2. 모든 전화번호의 접두어가 HashMap에 있는지 확인
		for(int i=0; i< phone_book.length; i++) {
			for(int j=1; j<phone_book[i].length(); j++) {
				if(map.containsKey(phone_book[i].substring(0, j)))
					return false;
			}
		}
		
		return true;
	}
}