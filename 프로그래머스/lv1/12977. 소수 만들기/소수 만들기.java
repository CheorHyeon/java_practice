class Solution {
    public int solution(int[] nums) {
        // 3중 for문으로 모든 경우의 수 구하기
        // 더한 수가 소수면 result 증가.
        int result = 0;
        for(int i=0; i<=nums.length-3; i++) {
            for(int j=i+1; j<=nums.length-2; j++){
                for(int k= j+1; k<=nums.length-1; k++) {
                    int tmp = nums[i] + nums[j] + nums[k] ;
                    // 에라토스테네스의 채 : 제곱근까지만 검사했을 때 나눠떨어지지 않으면 소수
                    int limit = (int)Math.sqrt((double)tmp);
                    // 소수인지 여부
                    boolean isDecimal = true;
                    for(int q=2; q<=limit; q++) {
                        if(tmp % q == 0) {
                            isDecimal = false;
                            break;
                        }
                    }
                    // 소수면 결과 1 증가
                    if(isDecimal) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}