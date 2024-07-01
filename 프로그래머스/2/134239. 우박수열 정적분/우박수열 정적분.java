import java.util.ArrayList;
import java.util.List;

class Solution {
	public double[] solution(int k, int[][] ranges) {
		// 1. 우박수열 구하기
		List<long[]> list = new ArrayList<>();
		list.add(new long[] {0, k}); // 최초 숫자 넣기(넓이 구할때 쓰기 위함)
		int totalCnt = 0; // 우박수열의 총 수
		while(k > 1) {
			if(k % 2 == 0) {
				k /= 2;
			}
			else {
				k = k * 3 + 1;
			}
			totalCnt++;
			list.add(new long[] {totalCnt, k});
		}
		// 2. 구간별 넓이 구하기
		double[] areaArr = new double[totalCnt];
		for(int i = 0; i < areaArr.length; i++) {
			long[] firstDot = list.get(i);
			long[] secondDot = list.get(i+1);

			// 사다리꼴의 넓이 : (윗변 + 아랫변) * 높이(1이라 생략) / 2
			double area = (firstDot[1] + secondDot[1]) / 2.0;
			areaArr[i] = area;
		}
		double[] result = new double[ranges.length];
		// 3. 범위별로 결과 구하기
		for(int i = 0; i < ranges.length; i++) {
			int[] range = ranges[i];
			int start = range[0];
			// n - b => b : 양수형태
			int end = totalCnt - Math.abs(range[1]);
			if(start > end) {
				result[i] = -1;
				continue;
			}
			double areaSum = 0;
			for(int idx = start; idx < end; idx++) {
				areaSum += areaArr[idx];  
			}
			result[i] = areaSum;
		}
		// 4. 결과 반환
		return result;
	}
}