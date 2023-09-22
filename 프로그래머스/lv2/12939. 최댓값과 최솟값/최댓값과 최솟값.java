import java.util.*;
class Solution {
    public String solution(String s) {
        String[] tmp = s.split(" ");
        
        int[] intChange = new int[tmp.length];
        for(int i=0; i<tmp.length; i++) {
            intChange[i] = Integer.parseInt(tmp[i]);
        }
        
        Arrays.sort(intChange);
        
        return intChange[0] + " " + intChange[intChange.length - 1] + "";
    }
}