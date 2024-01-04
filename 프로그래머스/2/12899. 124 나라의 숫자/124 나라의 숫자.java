class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();
        
        while(n > 0) {
            int mod = n % 3;
            n = n / 3;
            if(mod == 0) {
                mod = 4;
                n--;
            }
            sb.append(mod);
        }
        
        return sb.reverse().toString();
    }
}