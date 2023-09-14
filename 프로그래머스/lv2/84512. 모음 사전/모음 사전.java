import java.util.ArrayList;
import java.util.List;
class Solution {
    String[] arr;
    List<String> list;
    public int solution(String word) {
        int answer = 0;
        
        list = new ArrayList<>();
        arr = new String[]{"A", "E", "I", "O", "U"};
        
        recursion(word, "", 0);
        
        for(int i=0; i< list.size(); i++) {
            if(list.get(i).equals(word)) {
                answer = i;
                break;
            }
        }
        return answer;
    }
    
    // 단어 끝까지 들어갔다가 끝에서 바꿔가며 탐색
    // 마지막 재귀 호출 당시 len은 4였으므로 다시 돌아올때 4로 고정
    void recursion(String word, String str, int depth) {
        list.add(str);
        
        if(depth == 5) {
            return ;
        }
        
        for(int i=0; i< arr.length; i++) {
            recursion(word, str+arr[i], depth + 1);
        }
    }
}