import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {0, 1, 0 , -1};
    static int[] dy = {1, 0 , -1, 0};
    static boolean[][] visited;
    static int[][] A;
    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        visited = new boolean[N][M];
        // 미로 입력
        for(int i=0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(line.substring(j ,j+1));
            }
        }
        // BFS 탐색
        BFS(0, 0);
        // 해당 위치 depth출력
        System.out.println(A[N-1][M-1]);
    }
    
    public static void BFS(int x, int y) {
        Queue<Integer> queueX = new LinkedList<>();
        Queue<Integer> queueY = new LinkedList<>();
        queueX.offer(x);
        queueY.offer(y);
        visited[x][y] = true;
        while(!queueX.isEmpty()) {
            int nowX = queueX.poll();
            int nowY = queueY.poll();
            for(int k = 0; k < 4; k++) {
                int targetX = nowX + dx[k];
                int targetY = nowY + dy[k];
                // 배열의 범위가 유효한지 검사
                if(targetX >=0 && targetY >=0 && targetX < N && targetY < M) {
                    if(A[targetX][targetY] != 0 && !visited[targetX][targetY]) {
                        visited[targetX][targetY] = true;
                        A[targetX][targetY] = A[nowX][nowY] + 1;
                        queueX.add(targetX);
                        queueY.add(targetY);
                    }
                }
            }
        }
    }
    
}