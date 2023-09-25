class Solution {
    boolean solution(String s) {
       char[] c = s.toCharArray();
        int result = 0;
        
        for(int i=0; i<c.length; i++) {
            if(c[i] == '(')
                result++;
            else
                result--;
            
            if(result < 0)
                return false;
        }
        
        if(result !=0)
            return false;
        
        return true;
    }
}