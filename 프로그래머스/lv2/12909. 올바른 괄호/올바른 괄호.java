class Solution {
    boolean solution(String s) {
        char[] tmp = s.toCharArray();
        
        int result = 0;
        for(int i=0; i<tmp.length; i++) {
            if(tmp[i] == '(') {
                result++;
            }
            else result--;
            
            // 닫는거 먼저 나오거나 닫는게 더 많아지면 바로 false
            if(result < 0)
                return false;
        }
        
        // 다 돌았는데 결과가 0이 아니라는 것은 여는게 더 많은 경우 -> false
        if(result != 0) {
            return false;
        }

        return true;
    }
}