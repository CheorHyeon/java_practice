import java.util.*;
class Solution {
    public int solution(int[][] sizes) {
        int[] max = new int[sizes.length];
        int[] min = new int[sizes.length];
        
        for(int i = 0; i < sizes.length; i++) {
            int[] size = sizes[i];
            if(size[0] >= size[1]) {
                max[i] = size[0];
                min[i] = size[1];
            }
            else {
                max[i] = size[1];
                min[i] = size[0];
            }
        }
        
        Arrays.sort(max);
        Arrays.sort(min);
        
        return max[max.length - 1] * min[min.length - 1];
    }
}