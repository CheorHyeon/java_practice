import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] distance = new int[N+1][N+1];
        // 인접행렬 초기화
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(i == j) {
                    distance[i][j] = 0;
                }
                else {
                    distance[i][j] = 10_000_001; // 충분히 큰수
                }
            }
        }
        // 친구 관계 양방향 지정
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            distance[s][e] = 1;
            distance[e][s] = 1;
        }
        // 플로이드-워셜 알고리즘 수행
        for(int k = 1; k <= N; k++) {
            for(int s = 1; s <= N; s++) {
                for(int e = 1; e <= N; e++) {
                    if(distance[s][e] > distance[s][k] + distance[k][e]) {
                        distance[s][e] = distance[s][k] + distance[k][e];
                    }
                }
            }
        }
        // 최소값 찾기
        int min = Integer.MAX_VALUE;
        int answer = -1;
        for(int i = 1; i <= N; i++) {
            // 케빈 베이컨 수
            int temp = 0;
            for(int j = 1; j <= N; j++) {
                temp = temp + distance[i][j];
            }
            // 최소값이 더 큰경우만 갱신
            // 이래야 가장 작은값이면서 인덱스가 작은 번호 추출 가능
            if(min > temp) {
                min = temp;
                answer = i;
            }
        }
        System.out.println(answer);
    }
}