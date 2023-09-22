class Solution {
    public String solution(String s) {
      String answer = "";
        
        String[] arr = s.split(" ");
        for(int i=0; i<arr.length; i++) {
            String now = arr[i];
            
            // 공백이라면 길이가 0
            // 연속된 공백 문자도 분할 기준으로 간주 -> 공백 문자 들어감
            // 공백 연속되었다는 의미이니 공백 추가
            if(now.length() == 0){
                answer += " ";
            }
            
            else {
                answer += now.substring(0, 1).toUpperCase();
                answer += now.substring(1).toLowerCase();
                answer += " ";
            }
        }
        // 마지막 문자가 공백이라면 공백 그대로 반환
        if(s.charAt(s.length() - 1) == ' ') {
            return answer;
        }
        return answer.trim();
    }
}