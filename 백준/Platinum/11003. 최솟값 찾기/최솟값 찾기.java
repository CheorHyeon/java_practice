import java.util.*;
import java.io.*;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Deque<Node> mydeque = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken());
            
            // 삽입하려는 숫자보다 큰 수가 앞에 있다면 모두 비워주기
            while(!mydeque.isEmpty() && mydeque.getLast().value > now) {
                mydeque.removeLast();
            }
            // 마지막에 삽입
            mydeque.addLast(new Node(now, i));
            
            // 삽입하고자 하는 인덱스가 윈도우 크기를 초과하면 첫번째 노드 삭제
            if(i - mydeque.getFirst().index >= L) {
                mydeque.removeFirst();
            }
            bw.write(mydeque.getFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }
    
    static class Node {
        public int value;
        public int index;
        
        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}