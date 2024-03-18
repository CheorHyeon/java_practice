import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] distance = new int[N][N];
        // 인접행렬 저장하기
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());
                distance[i][j] = v;
            }
        }
        // 변형된 플로이드-워셜 알고리즘
        for(int k = 0; k < N; k++) {
            for(int s = 0; s < N; s++) {
                for(int e = 0; e < N; e++) {
                    if(distance[s][k] == 1 && distance[k][e] == 1){
                        distance[s][e] = 1;
                    }
                }
            }
        }
        // 출력
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                bw.write(distance[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.close();
        br.close();
    }
}