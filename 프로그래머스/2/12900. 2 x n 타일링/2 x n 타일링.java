class Solution {
    public int solution(int n) {
        // 타일을 그림 그려보면 이&이전 단계와 이전단계를 합해서 만드는 방법으로 구성됨
        // f(n) = f(n-1) + f(n-2) 규칙
        // 3 이하면 결과는 본인이니깐 본인 반환
        if(n <= 3) {
            return n;
        }
        // 1. 사이즈 n일때 만드는 방법의 수 배열 생성
        int[] result = new int[n+1];
        // 3 이하는 자기 자신이 정답이므로 초기화
        for(int i = 1; i <= 3; i++) {
            result[i] = i;
        }
        // 2. 각각의 경우의 수 구해서 배열에 넣기(넣기 전 숫자로 나누기)
        for(int i = 4; i < result.length; i++) {
            result[i] = (result[i-2] + result[i-1]) % 1_000_000_007;
        }
        // 3. n번째 방법 반환하기
        return result[n];
    }
}