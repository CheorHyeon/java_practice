class Solution {
    public int solution(int a, int b, int n) {
        // n개 병에서, 처음에 a개를 가져다 준다.
        // 그러면 b병 만큼을 다시 줄꺼고(마시면 빈병 되니깐)
        // 최종적으로는 b병 만큼 교환해서 먹은 셈이 됨
       int count = 0;
        while(n-a >=0) {
            n = n-a;
            n = n+b;
            count +=b;
        }
        
        return count;
    }
}