import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	public int[] solution(int[] fees, String[] records) {
		// fees : 기본 시간, 기본 요금, 단위 시간, 단위 요금
		// records : 차량별 입출입기록
		List<Integer> resultList = new ArrayList<>();
		// 1. records에서 차량 번호별 누적 시간 Map 구하기
		Map<Integer, Integer> minutes = new HashMap<>();
		for (int i = 0; i < records.length; i++) {
			String record = records[i];
			String[] info = record.split(" ");
			// 출차 기록이면 넘어가기
			if (info[2].equals("OUT")) {
				continue;
			}
			// 차 번호 추출
			Integer num = Integer.parseInt(info[1]);
			// 출차 기록을 찾기
			String inTime = info[0];
			String outTime = findOutTime(records, num, i);
			
			// In, out 시간차를 구하기
			long diff = betweenMinByStr(inTime, outTime);
			
			// 하루동안 누적 출차 기록 Map
			Integer timeDiff = minutes.getOrDefault(num, 0) + (int)diff;
			minutes.put(num, timeDiff);
		}
		// 2. map의 key를 차량 번호 순으로 정렬
		List<Integer> keyInt = minutes.keySet().stream()
			.sorted()
			.collect(Collectors.toList());
		// 3. 차 번호로 순서대로 순회하며 각각 계산하여 배열에 넣기
		for (Integer key : keyInt) {
			// 차량번호로 시간 읽어와서 금액 구하기
			Integer getTime = minutes.get(key);
			Integer price = calPrice(getTime, fees);
			// 리스트에 넣기
			resultList.add(price);
		}
		// 4. 결과 반환
		return resultList.stream()
			.mapToInt(Integer::intValue)
			.toArray();
	}

	/*
		요금 정보와 총 시간 정보를 입력받아 주차 요금 계산 메서드
	 */
	private Integer calPrice(Integer getTime, int[] fees) {
		// 금액 계산
		// 기본 시간 제외 (기본료)
		Integer baseDiff = getTime - fees[0] < 0 ? 0 : getTime - fees[0];
		// 기본료 + 시간 * 단위시간 금액(짤짤이 시간 기본 시간 치환을 위해 나머지가 0이 아니면 1로 올림)
		int result = fees[1] + ((baseDiff / fees[2]) + (baseDiff % fees[2] > 0? 1 : 0)) * fees[3];
		return result;
	}

	/*
		출차 기록에서 출입 번호에 해당하는 차 출차 기록 찾기 메서드
	 */
	private String findOutTime(String[] records, Integer num, int inIndex) {
		String result = null;
		for (int j = inIndex + 1; j < records.length; j++) {
			String target = records[j];
			String[] targetInfo = target.split(" ");
			// 차 번호가 같은녀석 만나면 바로 시간 추출하고 종료
			if (num.equals((Integer.parseInt(targetInfo[1])))) {
				result = targetInfo[0];
				break;
			}
		}
		// 출차 기록이 없다면
		if (result == null) {
			result = "23:59";
		}
		
		return result;
	}

	/*
		String을 받아 시간으로 변환하고 두 시간 차이를 분으로 반환하는 메서드
	 */
	private long betweenMinByStr(String inTime, String outTime) {
		LocalTime time1 = LocalTime.parse(inTime);
		LocalTime time2 = LocalTime.parse(outTime);

		Duration duration = Duration.between(time1, time2);
		return duration.toMinutes();
	}
}