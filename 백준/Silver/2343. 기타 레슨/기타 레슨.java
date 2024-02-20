import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] A = new int[N];
        int start = 0;
        int end = 0;
        // 1. 레슨 소요 시간 저장 블루레이 탐색 관련 설정
        for(int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            // 최소 블루레이 시간 : 최대 레슨 길이(가장 길게해야 1개 초과의 블루레이 수 가능)
            // 만일 가장 짧은 시간으로 할 시 1개의 블루레이만 가능
            // ex) 1 2 3 => 1분짜리 길이 -> 레슨 1만 저장
            // 반면 3분짜리 길이 -> 레슨 1 2 / 3 으로 모든 레슨 넣을 수 있음
            // 문제에서 N개의 레슨이 들어간다 했으니 모든 레슨 각각이 다 들어가긴 해야함
            if (start < A[i]) 
                start = A[i];
            // 최대 길이는 모두 합했을때의 길이로 설정
            // 각각 1개씩 블루레이를 만드는 것이 가장 최악의 경우
            end = end + A[i];
        }
        
        // 2. 이진탐색
        while(start <= end) {
            int mid = (start + end) / 2;
            int sum = 0; // 해당 블루레이 내에서의 시간 합계
            int count = 0; // 블루레이 갯수
            // mid값으로 모든 레슨 저장할 수 있는지 확인
            for(int i = 0; i < N; i++) {
                if(sum + A[i] > mid) {
                    count++;  // 시간이 넘친다면 더 넣을 수 없으니 해당 블루레이 작업 완료
                    sum = 0;  // 다음 블루레이 탐색
                }
                // 넘치지 않는다면 해당 레슨도 현재 블루레이에 포함시키기
                sum = sum + A[i];
            }
            // 끝까지 다 봤는데 블루레이 내 시간 합이 0이 아니라면 -> 아직 해당 블루레이이를 굽지 않음
            if(sum != 0) {
                count++; // 굽기 처리
            }
            // 만약 최소 M개를 넘친다? -> 그러면 블루레이의 크기를 키워서 필요한 블루레이 줄여야 함
            if(count > M) {
                start = mid + 1;
            }
            // 아닌 경우리면 최소 M개 이하인 경우
            // count == M : 조건 충족하지만, 더 최소인 사이즈가 있는지 탐색하기 위함
            // count < M : 블루레이 사이즈를 줄여서 M개로 맞출 수 있도록 조정 필요
            else
                end = mid - 1;
        }
        // 3. 블루레이 크기를 탐색했던 변수 start 출력
        System.out.println(start);
     
    }
}