import java.util.Arrays;

public class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int left = 0;
        int right = distance;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            int removeRock = 0;
            int before = 0;
            
            for(int rock : rocks) {
                // 돌과 기준점사이 거리가 mid보다 작으면 해당 돌 제거해줘야 거리가 늘어남
                if(rock - before < mid) {
                    removeRock++;
                    continue;
                }
                // mid 이상이라면 해당 돌 제거하지 말고 해당 돌 기준으로 다른 돌 사이 거리 재탐색
                before = rock;
            }
            // 마지막 점과 제거하지 않은 돌의 거리가 mid 미만이라면 돌 제거
            if(distance - before < mid) {
                removeRock++;
            }
            // 제거한 돌이 n개 이상 -> 돌을 덜 제거해야 함
            // 돌을 덜 제거하기 위해서는 최소 거리를 줄여줘야 함
            // 그러기 위해 다음 반복에서 mid = left + right / 2의 값을 줄여야 함
            // right의 범위를 조절
            if(removeRock > n) {
                right = mid -1;
                continue;
            }
            // 제거한 돌이 n개 이하 -> 돌을 더 제거해야 함
            // 돌을 더 제거하기 위해 최소 거리를 증가시켜 줘야 함
            // 그러기 위해 다음 반복에서 mid = left + right / 2의 값을 증가시켜야 함
            // left의 범위를 조절, n개 제거했더라도, 거리를 좀 더 증가시켰을때도 가능할 수도 있음(문제 예제처럼 2개 제거인 상황 여러개 중 거리 4가 최대)
            left = mid +1;
            // 만일 돌 n개 제거했다면, 그 중 최대값이 문제에서 구하는 가장 큰 최솟값
            answer = Math.max(answer, mid);

        }
        return answer;
    }
}