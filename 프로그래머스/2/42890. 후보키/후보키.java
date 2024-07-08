import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Solution {
	int rowSize;
	int colSize;
	Comparator<Integer> comp = new Comparator<Integer>() {
		int countBits(int n) {
			int ret = 0;
			while (n != 0) {
				if((n & 1) != 0) ++ret;
				n = n >> 1;
			}
			return ret;
		}
		@Override
		public int compare(Integer o1, Integer o2) {
			int x = countBits(o1);
			int y = countBits(o2);
			if(x > y) {
				// 양수 리턴 : 처음값이 더 큼을 의미
				return 1;
			}
			// 음수 리턴 : 오른쪽값이 더 큼을 의미
			else if (x < y)
				return -1;
			else
				return 0;
		}
	};
	boolean check (String[][] relation, int subSet) {
		// 모든 조합에 대해 값 비교
		for(int a = 0; a < rowSize - 1; a++) {
			for( int b = a + 1; b < rowSize; b++) {
				boolean isSame = true;
				for(int k = 0; k < colSize; k++) {
					// k번째 비트를 키는것과 같다.
					// k번째 비트가 0이라면 해당 속성은 고려 대상이 아님
					if((subSet & 1 << k) == 0) continue;
					if(!relation[a][k].equals(relation[b][k])) {
						isSame = false;
						break;
					}
				}
				// 한번도 다른 점이 없었다 -> 모든 속성이 같다는 이야기
				// 두 튜플이 구분이 안되기 때문에 유일성을 만족하지 않음
				if(isSame) return false;
			}
		}
		return true;
	}
	public int solution(String[][] relation) {
		int answer = 0;
		rowSize = relation.length;
		colSize = relation[0].length;
		// 1. 유일성 만족하는지 확인
		List<Integer> candidates = new LinkedList<>();
		for(int i = 1; i < 1 << colSize; i++) {
			if(check(relation, i)) {
				candidates.add(i);
			}
		}

		// 2. 속성의 개수가 적은 순으로 정렬
		Collections.sort(candidates, comp);

		// 3. 속성을 꺼냈을 때 이 속성을 포함하는 나머지 후보키를 모두 지워주면 됨
		while (candidates.size() != 0) {
			int n = candidates.remove(0);
			++answer;

			for(Iterator<Integer> it = candidates.iterator(); it.hasNext(); ) {
				int c = it.next();
				if((n & c) == n) {
					it.remove();
				}
			}

		}
		return answer;
	}
}