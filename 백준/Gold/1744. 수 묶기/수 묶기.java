import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수열의 수의 개수
        // 양수는 내림차순 (큰 것끼리 곱하기)
        PriorityQueue<Integer> plusPq = new PriorityQueue(Collections.reverseOrder());
        // 음수는 오름차순 (작은 것 끼리 곱해서 양수를 크게 만들기 위함)
        PriorityQueue<Integer> minusPq = new PriorityQueue();
        int one = 0;
        int zero = 0;
        // 4개의 그룹으로 분리하여 저장하기
        for(int i = 0; i < N; i++) {
            int data = sc.nextInt();
            if(data > 1) {
                plusPq.add(data);
            }
            else if (data == 1) {
                one++;
            }
            else if (data == 0) {
                zero++;
            }
            else {
                minusPq.add(data);
            }
        }
        int sum = 0;
        // 양수 처리
        while(plusPq.size() > 1) {
            int first = plusPq.poll();
            int second = plusPq.poll();
            sum = sum + first * second;
        }
        if(!plusPq.isEmpty()) {
            sum += plusPq.poll();
        }
        // 음수 처리
        while(minusPq.size() > 1) {
            int first = minusPq.poll();
            int second = minusPq.poll();
            sum = sum + first * second;
        }
        if(!minusPq.isEmpty()) {
            // 0이 없으면 결과에 그냥 더해주기
            if(zero == 0) {
                sum += minusPq.poll();
            }
        }
        // 1처리
        sum += one;
        System.out.println(sum);
    }
}