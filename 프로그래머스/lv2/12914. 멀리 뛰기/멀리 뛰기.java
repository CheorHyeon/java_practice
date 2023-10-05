class Solution {
   
    public long solution(int n) {
        // 1. n번째 칸 까지 가는 경우 : n-1 칸에서 1칸 점프 or n-2칸에서 2칸 점프
        // arr[n] = arr[n-2] + arr[n-1];
        // 2. 1234567로 나눈 경우를 더한 뒤 1234567로 나눠서 리턴
        // 피보나치 수열과 같은 점화식 -> 94또는 95번째부터 long의 범위가 넘치기에 나머지로 연산함
        long[] arr = new long[2001];
        
        // 1칸은 1칸만 가는 경우 1개
        arr[1] = 1;
        // 2칸은 1, 1 과 2칸 2개의 경우
        arr[2] = 2;
        
        for(int i = 3; i <=n; i++) {
            arr[i] = (arr[i-2] % 1234567) + (arr[i-1] % 1234567);
        }
        
        return arr[n] % 1234567;
      
    }
    
}