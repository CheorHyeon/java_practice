import java.util.*;

class Solution {
    public String solution(String s) {
        String[] sArr = s.split(" ");
        int[] arr = new int[sArr.length];
        // 1. 문자 숫자배열로 변환
        for(int i = 0; i < sArr.length; i++) {
            arr[i] = Integer.parseInt(sArr[i]);
        }
        
        // 2. 정렬
        Arrays.sort(arr);
        
        // 3. 결과 반환
        return arr[0] + " " + arr[arr.length - 1];
    }
}