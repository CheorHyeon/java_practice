import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(int n, long k) {
	List<Integer> numbers = new ArrayList<>();
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        // 팩토리얼 초기화 및 숫자 리스트 초기화
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            numbers.add(i);
        }

        k--; // 0부터 시작하는 인덱스로 조정
        int[] answer = new int[n];
        int index = 0;

        while (n > 0) {
            long section = factorial[n - 1];
            int selected = (int) (k / section);
            answer[index++] = numbers.get(selected);
            numbers.remove(selected);
            k %= section;
            n--;
        }

        return answer;
    }
}