import java.io.*;
import java.util.*;

public class Main {
    static int answer = 0;
    static int[] dayArr;
    static int[] payArr;
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dayArr = new int[N + 1];
        payArr = new int[N + 1];
        // 1. 상담 일자별 소요일수, pay 초기화
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dayArr[i] = Integer.parseInt(st.nextToken());
            payArr[i] = Integer.parseInt(st.nextToken());
        }
        // 2. dfs 탐색 시작
        dfs(1, 0); 
        // 3. 정답 출력
        System.out.println(answer);
    }
    
    public static void dfs(int day, int sum) {
        // 1. 종료 조건
        if(day >= N + 1) { // 퇴사 다음날 이상 초과할 경우
            answer = Math.max(answer, sum);
            return;
        }
        // 2. 반복 수행 -> N일차때 1일 걸리는 상담 하면 N+1일이 기준이 되니깐
        if(day + dayArr[day] <= N + 1) {
            // 상담 진행
            dfs(day + dayArr[day], sum + payArr[day]);
        }
        // 상담 진행x
        dfs(day + 1, sum);
    }
        
}