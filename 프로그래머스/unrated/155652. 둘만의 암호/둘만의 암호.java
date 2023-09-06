class Solution {
	public String solution(String s, String skip, int index) {
		// 문자열 s의 각각 문자를 1개씩 증가시키고, skip에 포함된 문자거나 z보다 커지면 a로 변경을 index만큼 반복
		char[] chars = s.toCharArray();
		char[] skipArray = skip.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			int add = index;
			char tmp = chars[i];
			while (add != 0) {
				// 하나 이동시킴
				tmp++;
				// 더했을 때 z를 넘어가면 a부터 시작인 것으로변경
				if (tmp > 'z') {
					tmp = 'a';
				}
				// 이동 시켰을 때 해당 문자가 skip할 문자면 증가시키기
				while (skip.contains(tmp + "")) {
					tmp++;
					// 더했을 때 z를 넘어가면 a부터 시작인 것으로변경
					if (tmp > 'z') {
						tmp = 'a';
					}
				}
				add--;
			}
			chars[i] = tmp;
		}
		return new String(chars);
	}
}