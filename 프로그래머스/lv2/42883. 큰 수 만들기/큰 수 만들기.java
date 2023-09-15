class Solution {
    public String solution(String number, int k) {
        // 1. k개의 숫자 제와 -> num - k 자리수
        // 2. 맨 뒤에서 부터 num - k 자리수를 만족하는 최소 위치를 찾음,
        // 2-1. 0 ~ 해당 인덱스까지 숫자 중 가장 큰 수
        // 2-2. num - k 자리수가 될 때까지 반복

        StringBuilder sb = new StringBuilder();

        int len = number.length();
        int idx = 0;    // 현재 위치

        // i, 숫자 len-k개를 뽑기위해 반복
        for(int i=0; i<len-k; i++) {
            char max = '0';
            // j 시작은 idx, 범
            // 첫번째 숫자는 0~k index 사이 존재
            for( int j=idx; j<=k+i; j++) {
                if(max < number.charAt(j)){
                    max = number.charAt(j);
                    idx = j+1;
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }
}