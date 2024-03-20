import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        tNode root = new tNode();
        // 1. 트라이 자료구조 구축
        while (n > 0) {
            String text = sc.next();
            tNode now = root;
            for(int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                // 26개 알파벳 위치를 배열 index로 나타내기 위해 -'a' 수행
                if(now.next[c-'a'] == null) {
                    now.next[c-'a'] = new tNode();
                }
                now = now.next[c - 'a'];
                if (i == text.length() - 1) {
                    now.isEnd = true;
                }
            }
            n--;
        }
        int count = 0;
        while (m > 0) {
            String text = sc.next();
            tNode now = root;
            for(int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                // 공백 노드라면 이 문자열 포함하지 않기에 반복문 종료
                if(now.next[c-'a'] == null) {
                    break;
                }
                now = now.next[c - 'a'];
                // 문자열의 마지막 문자이면서, S에 저장된 문자 탐색 중 끝문자라면 찾는 단어임
                if(i == text.length() - 1 && now.isEnd) {
                    count++;
                }
            }
            m--;
        }
        System.out.println(count);
    }
}

class tNode {
    // 기본 생성자 생략
    tNode[] next = new tNode[26];
    boolean isEnd;
}