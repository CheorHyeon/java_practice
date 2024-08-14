import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        boolean[] used = new boolean[A.length];
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int AIdx = 0;
        for(int i = 0; i < B.length; i++) {
            // B를 앞에서부터 보며 이길때만 체크
            // 가장 작은수로 이기는 경우니깐 이 경우가 최선
            if(AIdx < A.length && A[AIdx] < B[i] && !used[i]) {
                answer++;
                AIdx++;
                used[i] = true;
            }            
        }
        
        return answer;
    }
}