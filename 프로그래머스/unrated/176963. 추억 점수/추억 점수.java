import java.util.Map;
import java.util.HashMap;
class Solution {
	public int[] solution(String[] name, int[] yearning, String[][] photo) {
		// 1. map에 사람 : 점수 꼴로 넣기
		// 2. 사진별로 등장 인물에 해당하는 정수값 더해서 배열화
		// 3. 결과 반환

		int[] result = new int[photo.length];
		// 그리운 사람 인원만큼만
		Map<String, Integer> map = new HashMap<>(yearning.length);

		// 1. 맵 초기화
		for(int i=0; i<yearning.length; i++) {
			map.put(name[i], yearning[i]);
		}

		for(int i=0; i<photo.length; i++) {
			String[] tmp = photo[i];

			int sum = 0;
			for(int j=0; j<tmp.length; j++) {
				// getOrDefault : 해당 키 값 없을 때 기본 값
					sum += map.getOrDefault(tmp[j], 0);
			}

			result[i] = sum;
		}

		return result;
	}
}