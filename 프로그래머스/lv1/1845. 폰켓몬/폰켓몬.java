import java.util.*;
class Solution {
    public int solution(int[] nums) {
        // set에 하나씩 넣다가 set 크기가 N/2 되면 종료, 종 수를 리턴
        // 다 넣었는데 N/2가 안되면 그냥 set 크기 리턴
        
        int half = nums.length/2;
       Set<Integer> phone = new HashSet<>();
        
        for(int mon : nums) {
            if(phone.size() < half ) {
                phone.add(mon);
            }
            else{
                break;
            }
        }
        
        return phone.size();
    }
}