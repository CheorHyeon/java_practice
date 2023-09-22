class Solution {
    public String solution(String s) {
      String answer = "";
        String[] sArr = s.split("");
        // 다음에 나타나는 문자가첫글자 인지 여부
        boolean flag = true;
        
        for(String a : sArr) {
             // 첫글자면 대문자화
            // toUpperCase 및 toLowerCase 메서드는 숫자나 공백은 변환이 무시됨
            answer += flag ? a.toUpperCase() : a.toLowerCase();
            // 만일 공백이면 다음에 나올것이 대문자니깐 트루, 아니면 false
            flag = a.equals(" ")? true:false;
        }
        
        return answer;
        
    }
}