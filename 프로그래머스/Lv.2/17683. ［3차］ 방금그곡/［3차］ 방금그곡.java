import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
	String[] musicinfos;
	Map<String, String> musicStartTime = new HashMap<>();

	public String solution(String m, String[] musicinfos) {
		this.musicinfos = musicinfos;
		String answer = null;
		// 1. Map 생성 => Key : 노래, Value : 재생된 악보
		/*
			1-1. 재생된 악보가 m의 길이보다 작으면 Map에 추가하지 않음(기억하는 멜로디가 아님)
			1-2. m이 포함되어 있지 않다면 Map에 추가하지 않음
		 */
		Map<String, List<String>> playedMelody = playMusicResult(m);
		// 1-3. Map size가 0이라면 (None) 반환
		if (playedMelody.isEmpty()) {
			return "(None)";
		}
		// 2. Map size가 1이라면 해당 노래 반환
		if (playedMelody.size() == 1) {
			return playedMelody.keySet().stream().collect(Collectors.toList()).get(0);
		}
		// 2-1. Map size가 2 이상이라면 아래 조건에 따라 반환
		/*
			 1순위 : 재생 시간 가장 긴것(Value 길이 가장 긴 것)
			 2순위 : 1순위 만족하는 노래가 여러개인 경우 먼저 시작한 노래
		 */
		answer = choiceBestMelody(playedMelody);

		return answer;
	}

	/*
		정답 후보들 중 최선의 멜로디 반환하기
		1순위 : 재생 시간 가장 긴것(Value 길이 가장 긴 것)
		2순위 : 1순위 만족하는 노래가 여러개인 경우 먼저 시작한 노래
	 */
	private String choiceBestMelody(Map<String, List<String>> playedMelody) {
		List<String> result = new ArrayList<>();
		List<String> keySet = playedMelody.keySet()
			.stream()
			// 길이 순 내림차순 정렬
			.sorted((o1, o2) -> playedMelody.get(o2).size() - playedMelody.get(o1).size())
			.collect(Collectors.toList());
		int longestMin = playedMelody.get(keySet.get(0)).size();
		for (String key : keySet) {
			List<String> s = playedMelody.get(key);
			if (s.size() != longestMin) {
				break;
			}
			// 가장 긴 길이와 같은 문자열 다 모아두기
			result.add(key);
		}

		// 1순위 : 가장 긴 길이(오래 연주)한 것이 1곡이라면 해당 곡 제목 반환
		if (result.size() == 1) {
			return result.get(0);
		}

		// 2순위 : 가장 빨리 시작한 곡 반환
		return result.stream()
			.sorted(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					o1 = musicStartTime.get(o1);
					o2 = musicStartTime.get(o2);
					String[] splitO1 = o1.split(":");
					String[] splitO2 = o2.split(":");
					// Integer의 기본 comparable은 오름차순으로 양수이면 자리를 바꿈
					// 7, 5 => 5, 7로 만들기 위함
					if (Integer.parseInt(splitO1[0]) - Integer.parseInt(splitO2[0]) == 0) {
						return Integer.parseInt(splitO1[1]) - Integer.parseInt(splitO2[1]);
					}
					return Integer.parseInt(splitO1[0]) - Integer.parseInt(splitO2[0]);
				}
			})
			.collect(Collectors.toList())
			.get(0);
	}

	/*
		"방금그곡" 서비스에서 찾고자 하는 멜로디가 포함된 음악 목록을 반환하는 메서드
		반환 타입 = Key : 음악 제목 / Value : 연주된 멜로디
	 */
	public Map<String, List<String>> playMusicResult(String targetMelody) {
		Map<String, List<String>> result = new HashMap<>();
		for (String musicinfo : musicinfos) {
			// 1. 정보 추출
			String[] musicinfoArr = musicinfo.split(",");
			String startTime = musicinfoArr[0];
			String endTime = musicinfoArr[1];
			String songName = musicinfoArr[2];
			String musicalNote = musicinfoArr[3];

			// 2. 시간 추출(분)
			Duration between = Duration.between(LocalTime.parse(startTime), LocalTime.parse(endTime));
			long minDiff = between.toMinutes();
			// 3. 연주된 악보 생성
			List<String> play = new ArrayList<>();
			List<String> musicalNoteArr = noteToStringList(musicalNote);
			int idx = 0;
			int z = 1;
			while (minDiff > 0) {
				if (idx == musicalNoteArr.size()) {
					idx = 0;
				} else {
					play.add(musicalNoteArr.get(idx++));
					minDiff--;
				}
			}
			// 4. Map에 추가될 수 있는지 검증하고 조건 만족 시 추가
			// 4-1. 연주한 것이 더 길어야 기억하는 음과 비교가 가능
			List<String> transTargetMelody = noteToStringList(targetMelody);

			if (play.size() >= transTargetMelody.size()) {
				// 4-2. 연주된 것에 기억하는 멜로디가 포함되어있어야 추가
				// 2번째 인자의 리스트가 첫번째 리스트에서 연속으로 존재하는지 확인하는 메서드
				if (Collections.indexOfSubList(play, transTargetMelody) != -1) {
					result.put(songName, play);
					// 여러 정답 중 재생 시간이 같은 경우 빨리 시작한 노래 찾기 위한
					// 시작 시간 Map 저장
					musicStartTime.put(songName, startTime);
				}
			}
		}
		return result;
	}

	/*
		악보를 String 배열로 변환해주는 메서드
		#이 붙은 멜로디도 합해준다.
	 */
	private List<String> noteToStringList(String musicalNote) {
		char[] chars = musicalNote.toCharArray();
		List<String> result = new ArrayList<>();
		for (int i = 0; i < chars.length; i++) {
			// C# 등 # 같이 붙여주기
			if (chars[i] == 'C' || chars[i] == 'D' || chars[i] == 'F' || chars[i] == 'G' || chars[i] == 'A') {
				if (i + 1 < chars.length && chars[i + 1] == '#') {
					result.add(String.valueOf(chars[i]) + chars[i + 1]);
					i++;
					continue;
				}
			}
			result.add(String.valueOf(chars[i]));
		}
		return result;
	}
}