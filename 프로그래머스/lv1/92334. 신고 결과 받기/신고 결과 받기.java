import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Solution {
	public int[] solution(String[] id_list, String[] report, int k) {
		// 동일한 유저에 대한 신고 횟수는 1회 처리
		// k번 이상 신고된 유저는 정지, 신고한 모든 유저에게 정지 사실 메일로 발송
		// 모든 내용 취합 후 한꺼번에 정지 및 메일 발송
		// 1. 이용자 이름 : 신고자 명단 map 생성
		// 2. map 사이즈가 k 이상인 경우 이용 정지, 정지자에게 메일 발송

		// 1. 신고당한사람 : 신고자 map
		Map<String, HashSet<String>> reports = new HashMap<>();
		for (int j = 0; j < report.length; j++) {
			// 신고 정보 추출
			String[] reportInfo = report[j].split(" ");
			// 신고 결과 넣기
			reports.putIfAbsent(reportInfo[1], new HashSet<>());
			reports.get(reportInfo[1]).add(reportInfo[0]);
		}

		int[] result = new int[id_list.length];
		// 2. map 사이즈가 k 이상인 경우 대상자에게 메일 발송
		for (Map.Entry<String, HashSet<String>> entry : reports.entrySet()) {
			if (entry.getValue().size() >= k) {
				HashSet<String> mailSend = entry.getValue();
				for (int z = 0; z < id_list.length; z++) {
					if (mailSend.contains(id_list[z])) {
						result[z]++;
					}
				}
			}
		}
		return result;
	}
}