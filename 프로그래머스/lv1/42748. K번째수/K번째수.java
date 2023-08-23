import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
	public int[] solution(int[] array, int[][] commands) {
		// commands에서 하나씩 꺼내서 i j k 값 가져오기
		// Arrays.copyOfRange 함수로 복사한 배열 생성 후 정렬
		// k 번째 값을 결과 리스트에 넣고 배열화 하여 반환

		List<Integer> list = new ArrayList<>();
		for(int[] command : commands) {
            int i = command[0] -1;
            int j = command[1];
            int k = command[2]-1;
			int[] tmp = Arrays.copyOfRange(array, i, j);
            Arrays.sort(tmp);
			list.add(tmp[k]);
		}

		return list.stream().mapToInt(Integer::intValue).toArray();
	}
}