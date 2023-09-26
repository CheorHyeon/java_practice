class Solution {
    public int solution(int n) {
        // 1. 특정 숫자를 기준으로 다음 숫자 더하고 더했을 때 되는지 확인
        // ex) 1 + ... = n ? / 2 + ..... = n? / ...
        // 연속돤 자연수들로 표현이기에 1씩 증가시켜가면서 확인
        int answer = 0;
        for(int i=1; i<=n; i++) {
            int sum = 0;
            for(int j = i; j<=n; j++) {
                // 1 + 2 + 3 + 4 ...
                sum+=j;
                
                // 같아지는 순간이 있으면 답이므로 개수 증가시키고 종료
                if(sum == n) {
                    answer++;
                    break;
                }
                
                // 커지면 답이 안되니깐 종료
                else if(sum > n) {
                    break;
                }
            }
        }
        return answer;
    }
}