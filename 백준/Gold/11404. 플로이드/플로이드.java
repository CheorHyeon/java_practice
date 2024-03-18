import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[][] distance;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        distance = new int[N+1][N+1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if (i==j) {
                    distance[i][j] = 0;
                }
                else {
                    distance[i][j] = 10_000_001; // 충분히 큰 수로 저장
                }
            }
        }
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 여러 경로 있을 수 있다 했으니, 작은 엣지 나오면 갱신
            if(distance[s][e] > v) {
                distance[s][e] = v;
            }
        }
        // 플로이드-워셜 알고리즘
        for(int k = 1; k <= N; k++) {
            for(int s = 1; s <= N; s++) {
                for(int e = 1; e <= N; e++) {
                    if(distance[s][e] > distance[s][k] + distance[k][e]) {
                        distance[s][e] = distance[s][k] + distance[k][e];
                    }
                }
            }
        }
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(distance[i][j] == 10_000_001){
                    bw.write("0 ");
                }
                else {
                    bw.write(distance[i][j] + " ");
                }
            }
            bw.write("\n");
        }
        bw.close();
        br.close();
    }
}