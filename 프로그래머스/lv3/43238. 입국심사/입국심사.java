import java.util.Arrays;
class Solution {
    public long solution(int n, int[] times) {
        // n명, time 각 심사대 걸리는 시간
        // 모든 사람이 걸리는 시간 최소
        long answer = 0;
        Arrays.sort(times);
        long left = 0;
        long right = times[times.length - 1] * (long) n; // 최악의 경우(가장 오래걸리는 사람한테 다)
        
        while(left <= right) {
            long mid = (left + right) / 2;
            long complete = 0;
            for (int i=0; i < times.length; i++) {
                //한 사람당 해당 시간에 처리할 수 있는 최대 사람의 수 
                complete += mid / times[i]; 
            }
            // 총 처리 인원이 n명이 안된다면 시간을 더 늘려서 검사해야 함
            if(complete < n) {
                left = mid + 1;
            }
            // 총 처리 인원이 n명 이상이라면 탐색 범위를 mid 전으로 줄임
            // 최소 시간을 찾아야 함! 오른쪽은 왼쪽으로 이동, 왼쪽은 오른쪽으로 이동하니
            // right가 left를 역전하면 종료
            else {
                right = mid -1;
                answer = mid; // n명 이상 처리가 가능한 시간이 정답, 범위를 줄여가면 최소값 찾기 가능
            }
        }
        return answer;
    }
}