import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	public int solution(int n, int m, int[] section) {
	    // 시작할 곳을 section에서 가져온다.
        // 페인트 한번에 칠할때 끝나는 지점을 구하고, section의 요소를 반복하며 해당 범위면 그대로
        // 범위를 넘으면 새로 꺼낸 section을 start로 진행
		int start = section[0] - 1;
        int end = start + m -1;
        
        int painted = 1;

		for(int a : section) {
            if(a-1 > end) {
                start = a-1;
                end = start + m -1;
                painted++;
            }
        }
        
        return painted;
	}
}