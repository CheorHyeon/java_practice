import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	public int[] solution(String today, String[] terms, String[] privacies) {
		// 1. terms를 map 형태 저장
		// 2. 개인정보 만료일을 수정
		// 3. today와 비교

		Map<String, Integer> type = new HashMap<>();
		// 1. map형태 저장
		for (int i = 0; i < terms.length; i++) {
			String[] term = terms[i].split(" ");
			String key = term[0];
			int value = Integer.parseInt(term[1]);
			// 타입별로 유효기간 저장
			type.put(key, value);
		}

		String[] todayArray = today.split("\\.");
		int[] todaySplit = new int[3];
		for (int k = 0; k < 3; k++) {
			// 일, 월, 년도 순 정렬
			todaySplit[k] = Integer.parseInt(todayArray[2 - k]);
		}

		// 삭제할 개인정보 결과
		List<Integer> resultList = new ArrayList<>();

		// 2. 만료일 수정
		for (int i = 0; i < privacies.length; i++) {
			String[] privacy = privacies[i].split(" ");
			String privacyType = privacy[1];

			// 만료기간 가져오기
			int last = type.get(privacyType);

			String[] date = privacy[0].split("\\.");
			int[] dateSplit = new int[3];

			for (int k = 0; k < 3; k++) {
				// 일, 월, 년도 순 정렬
				dateSplit[k] = Integer.parseInt(date[2 - k]);
			}

			dateSplit[1] += last;
			dateSplit[0]--;
			// 일자 갱신했을 때 0일이면 그 전달 28일로 수정
			if (dateSplit[0] == 0) {
				dateSplit[1]--;
				dateSplit[0] = 28;
			}
			// 연도 갱신
			while (dateSplit[1] > 12) {
				dateSplit[2]++;
				dateSplit[1] -= 12;
			}

			boolean personal = false;

			// 만료 년도가 지났으면 바로 그냥 삭제
			if (dateSplit[2] < todaySplit[2]) {
				personal = true;
			}
			// 만료 년도가 같으면
			else if (dateSplit[2] == todaySplit[2]) {
				// 만료 월보다 오늘 월이 더 크다면 바로 삭제
				if (dateSplit[1] < todaySplit[1]) {
					personal = true;
				}
				// 월이 같으면 일자 비교
				else if (dateSplit[1] == todaySplit[1]) {
					// 만료 일이 지났으면 바로 삭제
					if (dateSplit[0] < todaySplit[0]) {
						personal = true;
					}
				}
				// 위 조건 외에는 모두 삭제 대상이 아니기에 수정 x
			}

			if (personal) {
				resultList.add(i + 1);
			}
		}

		return resultList.stream().mapToInt(Integer::intValue).toArray();
	}
}