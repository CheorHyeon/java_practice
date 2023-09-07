class Solution {
	public String solution(String new_id) {
		// 1단계
		String answer = "";
		new_id = new_id.toLowerCase();

		// 2단계
		answer = new_id.replaceAll("[^a-z0-9-_.]", "");

		// 3단계
		answer = answer.replaceAll("\\.+", ".");

		// 4단계
		answer = answer.replaceAll("^[.]|[.]$", "");

		// 5단계
		if (answer.isEmpty()) {
			answer = "a";
		}

		// 6-1단계
		if (answer.length() >= 16) {
			answer = answer.substring(0, 15);
			// 6-2단계
			answer = answer.replaceAll("[.]$", "");
		}

		// 7단계
		while (answer.length() < 3) {
			answer += answer.charAt(answer.length() - 1);
		}

		return answer;
	}
}