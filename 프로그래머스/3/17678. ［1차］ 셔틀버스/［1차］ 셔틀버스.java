import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	int m;
public String solution(int n, int t, int m, String[] timetable) {
		this.m = m;
		String[] answer = new String[2];
		// 1. n과 t로 버스 시간 추출하여 map 생성
		Map<String, List<String>> busTimeWaitPeopleList = genBusWaitMap(n, t);

		// 2. 마지막 셔틀 시간 이후 도착자 제외하기 위한 시간 추출(23:59에는 집에 간다 했으니)
		String lastBusStartTime = busTimeWaitPeopleList.keySet()
			.stream()
			.sorted(Collections.reverseOrder())
			.limit(1)
			.collect(Collectors.joining());

		// 3. 도착 예정 시간별로 정렬하여 탈 수 있는 시간별 버스 배치
		lineUpCrew(busTimeWaitPeopleList, lastBusStartTime, timetable);

		// 4. 가장 마지막 버스 시간부터 순회하며 탈 수 있는 가장 마지막 버스 크루 도착 시간 -1
		// 4-1. 가장 마지막 버스 시간
		List<String> busTimeListSortedByStartTime = busTimeWaitPeopleList.keySet()
			.stream()
			.sorted(Collections.reverseOrder())
			.collect(Collectors.toList());
		// 4-2. 마지막 버스 부터 m명 이하인 버스 찾아서 그 크루 시간보다 1분 빠르게
		for (String busTime : busTimeListSortedByStartTime) {
			List<String> people = busTimeWaitPeopleList.get(busTime);
			// 아무도 없다면 해당 시간에 도착하면 가장 마자믹에 셔틀탐
			// m명 꽉차지 않았다면 해당 버스 출발시간에 줄서도됨
			if (people.isEmpty() || people.size() < m) {
				return busTime;
			}
			// m명 꽉차있다면 가장 마지막 사람 시간 -1
			if (people.size() == m) {
				String lastCrewWaitTime = busTimeWaitPeopleList.get(busTime)
					.stream()
					.sorted(Collections.reverseOrder())
					.limit(1)
					.collect(Collectors.joining());

				String[] time = lastCrewWaitTime.split(":");
				if (!time[1].equals("00")) {
					answer[0] = Integer.parseInt(time[0]) + "";
					answer[1] = (Integer.parseInt(time[1]) - 1) + "";
				} else {
					answer[1] = "59";
					answer[0] = (Integer.parseInt(time[0]) - 1) + "";
				}
				break;
			}

		}

		// 5. 시간 반환
		return (Integer.parseInt(answer[0]) < 10 ? "0" + answer[0] : answer[0]) + ":" + (
			Integer.parseInt(answer[1]) < 10 ? "0" + answer[1] : answer[1]);
	}

	private void lineUpCrew(Map<String, List<String>> busTimeWaitPeopleList, String lastBusStartTime,
		String[] timetable) {
		int lastTimeTransMin = transStringToMin(lastBusStartTime);

		// 1. 가장 마지막 버스 이후에 도착한 크루들 제외하고 시간 순 정렬
		List<String> sortedCrew = Arrays.stream(timetable)
			.sorted()
			.filter(time -> {
				int timeMin = transStringToMin(time);
				if (timeMin > lastTimeTransMin) {
					return false;
				}
				return true;
			})
			.collect(Collectors.toList());

		// 2. 버스 시간 가져와서 시간 순 정렬
		List<String> busTimeList = busTimeWaitPeopleList.keySet().stream().sorted().collect(Collectors.toList());
		// 크루들 도착 시간과 버스 출발 시점과 비교
		for (String crew : sortedCrew) {
			int crewMin = transStringToMin(crew);
			for (String busTime : busTimeList) {
				int busTimeMin = transStringToMin(busTime);
				// 가능한 빠른 버스에 배치
				if (busTimeMin >= crewMin) {
					List<String> busTimeWaitList = busTimeWaitPeopleList.get(busTime);
					if (busTimeWaitList.size() < m) {
						busTimeWaitList.add(crew);
						busTimeWaitPeopleList.put(busTime, busTimeWaitList);
						break;
					}
				}
			}
		}
	}

	private int transStringToMin(String time) {
		String[] timeArr = time.split(":");
		return Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
	}

	private Map<String, List<String>> genBusWaitMap(int count, int time) {
		// 1. map 생성
		Map<String, List<String>> map = new HashMap<>();
		map.put("09:00", new ArrayList<>());

		// 2. 시간 별 map 생성
		int startHour = 9;
		int startMin = 0;
		for (int i = 1; i < count; i++) {
			startMin = startMin + time;
			if (startMin >= 60) {
				startHour++;
				startMin -= 60;
			}
			String hourString = startHour + "";
			String minString = startMin + "";
			if(startHour < 10) hourString = "0" + hourString;
			if(startMin < 10) minString = "0" + minString;
			map.put((hourString + ":" + minString), new ArrayList<>());
		}

		return map;
	}
}