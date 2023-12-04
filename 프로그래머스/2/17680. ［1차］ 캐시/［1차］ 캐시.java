import java.util.List;
import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        // 캐시 사이즈가 0이라면 DB접속 계속 해야함
       if(cacheSize == 0) {
           return cities.length * 5;
       }
        
        int answer = 0;
    
        List<String> caches = new LinkedList<>();
        for(int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase(); // 대소문자 구분x
            // cache에 없다면
            if(!caches.contains(city)) {
                answer += 5;
                if(caches.size() >= cacheSize) {
                    // 가장 앞에 있는 것이 오랫동안 사용하지 않은 캐시
                    caches.remove(0);
                }
                caches.add(city);
                continue;
            }
            // cache에 있다면
            else {
                answer += 1;
                // 리스트에서 삭제시키고 다시 추가
                // 맨 뒤가 가장 최근에 찾은 도시가 되고, 가장 앞이 삭제 대상
                caches.remove(city);
                caches.add(city);
            }
        }
        
        return answer;
    }
}