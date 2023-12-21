import java.util.*;

class Solution {
	Map<String, String> recently;
	public String[] solution(String[] record) {
		List<String> result = new ArrayList<>();
		recently = new HashMap<>();
		// 1. Enter인 경우 Map에 최근 nickname 갱신
		for(int i=0; i<record.length; i++) {
			String rec = record[i];
			String[] splitRec = rec.split(" ");
			// 최근 닉네임 갱신
			if(splitRec[0].equals("Enter") || splitRec[0].equals("Change")) {
				recently.put(splitRec[1], splitRec[2]);
			}
		}
		// 2. record 순회하며 id에 따른 메세지 생성
		for(int i=0; i<record.length; i++) {
			String rec = record[i];
			String message = createMessage(rec);
			if(!message.equals("Not Message")) {
				result.add(message);
			}

		}
		return result.toArray(new String[0]);
	}
	/*
		기록에서 메세지 생성해주는 함수
	*/
	private String createMessage(String record) {
		String[] recordSplit = record.split(" ");
		String type = "";
		switch(recordSplit[0]) {
			case "Enter" -> type = "들어왔습니다.";
			case "Leave" -> type = "나갔습니다.";
		}
		// Change일 경우 메세지 생성하지 않으니 넘기기
		if(type.equals("")) {
			return "Not Message";
		}

		String nickname = recently.get(recordSplit[1]);
		return nickname + "님이 " + type;
	}
}