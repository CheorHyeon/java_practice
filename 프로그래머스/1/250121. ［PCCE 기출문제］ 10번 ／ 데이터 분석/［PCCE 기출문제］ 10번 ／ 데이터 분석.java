import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
	public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
		List<Data> list = new ArrayList<>();
		// 1. Data객체로 변환
		for (int[] info : data) {
			list.add(new Data(info[0], info[1], info[2], info[3]));
		}
		// 2. 필터링
		List<Data> filterList = new ArrayList<>();
		for (Data a : list) {
			int filterData = 0;
			switch (ext) {
				case "code":
					filterData = a.code;
					break;
				case "date":
					filterData = a.date;
					break;
				case "maximum":
					filterData = a.maximum;
					break;
				default:
					filterData = a.remain;
					break;
			}
			if (filterData < val_ext) {
				filterList.add(a);
			}
		}

		// 3. 정렬
		Collections.sort(filterList, (o1, o2) -> {
			switch (sort_by) {
				case "code": {
					return o1.code - o2.code;
				}
				case "date": {
					return o1.date - o2.date;
				}
				case "maximum": {
					return o1.maximum - o2.maximum;
				}
				default: {
					return o1.remain - o2.remain;
				}
			}
		});

		// 4. 결과용 배열 생성
		int[][] result = new int[filterList.size()][4];
		for (int i = 0; i < filterList.size(); i++) {
			Data temp = filterList.get(i);
			result[i][0] = temp.code;
			result[i][1] = temp.date;
			result[i][2] = temp.maximum;
			result[i][3] = temp.remain;
		}

		// 5. 결과 반환
		return result;

	}
}

class Data {
	int code;
	int date;
	int maximum;
	int remain;

	public Data(int code, int date, int maximum, int remain) {
		this.code = code;
		this.date = date;
		this.maximum = maximum;
		this.remain = remain;
	}

}