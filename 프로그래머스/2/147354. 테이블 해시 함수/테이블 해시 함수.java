import java.util.Arrays;
import java.util.Comparator;

class Solution {
	public int solution(int[][] data, int col, int row_begin, int row_end) {
		// 1. 인덱스 조정
		col = col - 1;
		row_end = row_end - 1;
		row_begin = row_begin - 1;
		// 2. col번째 열 기준 오름차순 정렬
		// 2-1. 값이 동일하다면 찻반쩨 컬럼 기준 내림차순
		int finalCol = col;
		Arrays.sort(data, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o2[finalCol] == o1[finalCol])
					return o2[0] - o1[0];
				return o1[finalCol] - o2[finalCol];
			}
		});
		// 3. si 구하기(begin <= i <= end)
		int si[] = new int[row_end - row_begin + 1];
		for(int i = row_begin; i <= row_end; i++) {
			int sum = 0;
			int[] arr = data[i];
			for(int j = 0; j < data[0].length; j++) {
				// 문제에서는 i번째줄 -> 위에서 -1로 인덱스 조정(첫 줄 0번부터)
				// 따라서 i+1로 나눠줘야 함
				sum += (arr[j] % (i+1));
			}
			si[i - row_begin] = sum;
		}
		
		// 4. xor 연산 구하기
		return calXor(si);
	}

	private int calXor(int[] si) {
		int result = 0;
		for(int i : si) {
			if(i != 0) {
				result = result ^ i;
			}
		}
		return result;

	}
}