class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        long[] cntWeight = new long[1001];
        long[] cntMul = new long[4001];
        
        for(int i=0; i < weights.length; i++) {
            // 현재 사람의 몸무개를 가진 사람이 있었는지 검사
            long temp = cntWeight[weights[i]];
            int m2 = weights[i] * 2;
            int m3 = weights[i] * 3;
            int m4 = weights[i] * 4;
            // 같은 몸무게를 가진 사람이 있었다면
            if(temp > 0) {
                // 그 사람 수만큼 짝을 이룰 수 있어 더하기
                // ex) 현재 : 100 / 이전에 100 : 1명 -> 나랑 그사람 1짝 가능
                answer += temp;
                // 몸무게 2, 3, 4배와 같은 사람의 수 추가하기
                // 배수와 같은 사람과도 짝이 n개 생기니깐 그대로 더하기
                // 몸무게가 같은 사람의 수만큼은 이전에 더해줬으니 제외
                answer += cntMul[m2] - temp;
                answer += cntMul[m3] - temp;
                answer += cntMul[m4] - temp;
            }
            // 같은 몸무게를 가진 사람이 없었다면 배수인 관계인 사람 수만큼 덧셈
            else {
                answer += cntMul[m2];
                answer += cntMul[m3];
                answer += cntMul[m4];
            }
            // 배열에 카운트 더하기
            cntWeight[weights[i]]++;
            cntMul[m2]++;
            cntMul[m3]++;
            cntMul[m4]++;
        }
        return answer;
    }
}