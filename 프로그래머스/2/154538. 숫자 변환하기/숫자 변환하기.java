class Solution {
    private static final int MAX = Integer.MAX_VALUE;
    
    public int solution(int x, int y, int n) {
        // 1. x ~ y까지 1씩 증가하며 해당 숫자를 만들 수 있는지 검사하는 배열 생성
        // 2. 각 숫자를 만들 수 있는 최소 방법의 수를 찾음
        // i번째 숫자는 => i/2, i/3, i-n 번째 위치와 비교 => ex) i/2 * 2 = i => (i/2 방법 + 1회)
        int answer = 0;
        
        int[] dp = new int[x+y];
        // 0 ~ x-1까지는 방법이 없음(뺴는 연산이 없으니)
        // x번째는 자기 자신이니 0번 연산
        for(int i = x + 1; i < y + 1; i++) {
            int countMod2 = MAX, countMod3 = MAX, countAddN = MAX, minCount;
            
            // x 미만인 숫자들은 나올 수 없는 숫자이기에 고려하지 않음
            if(isDivided(i, 2) && isGreaterThanOrEqualTo(x, i/2)) countMod2 = dp[i / 2];
            if(isDivided(i, 3) && isGreaterThanOrEqualTo(x, i/3)) countMod3 = dp[i / 3];
            if(isGreaterThanOrEqualTo(x, i-n)) countAddN = dp[i - n];
            
            // 최소 횟수 추출
            minCount = Math.min(countMod2, countMod3);
            minCount = Math.min(minCount, countAddN);
            
            // i/2, i/3, i-n 최소 방법이 있다면 각 경우에 1번을 추가 연산하면 되니 +1해서 최소값 갱신
            // MAX인 경우는 i/2, i/3, i-n을 만들 수 없는 경우이므로, i번째 수도 만들지 못함
            dp[i] = (minCount < MAX) ? minCount + 1 : MAX;
        }
        
        // y를 만들 수 없다면 -1 반환
        answer = (dp[y] < MAX) ? dp[y] : -1;
        
        return answer;
    }
    /*
        주어진 숫자가 해당 숫자의 배수인지 검사하는 메서드
    */
    private boolean isDivided(int number, int mod) {
        if(number / mod > 0 && number % mod == 0) 
            return true;
        else
            return false;
    }
    
    /*
        해당 숫자가 목표 숫자 이상인지 검사
    */
    private boolean isGreaterThanOrEqualTo(int number, int target) {
        if(target >= number)
            return true;
        else
            return false;
    }
}