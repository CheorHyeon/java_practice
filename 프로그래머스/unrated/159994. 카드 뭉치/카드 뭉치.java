class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
       // goal을 순회하며 cards1의 차례, cards2의 차례의 단어와 같은지 비교
        // 같으면 다음꺼 진행
        // 다르면 break;
        boolean result = true;
        
        int index1 = 0;
        int index2 = 0;
        
        for(int i=0; i<goal.length; i++) {
            if(index1 < cards1.length && goal[i].equals(cards1[index1])) {
                index1++;
                continue;
            }
            if(index2 < cards2.length && goal[i].equals(cards2[index2])) {
                index2++;
                continue;
            }
            
            else {
                result = false;
                break;
            }
        }
        
        if(result) {
            return "Yes";
        }
        
        return "No";
    }
}