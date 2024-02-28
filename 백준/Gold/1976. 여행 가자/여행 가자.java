import java.util.Scanner;

public class Main {
    public static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] dosi = new int[N+1][N+1];
        // 도시 연결 데이터 저장하기
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                dosi[i][j] = sc.nextInt();
            }
        }
        int[] route = new int[M+1];
        // 여행 도시 정보 저장
        for(int i = 1; i <= M; i++) {
            route[i] = sc.nextInt();
        }
        parent = new int[N+1];
        // 대표 노드를 자기 자신으로 초기화
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                // 도시 연결되어 있으면 union 실행
                if(dosi[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        // 여행 계획 도시들이 1개의 도시로 연결되어있는지 확인
        int index = find(route[1]);
        for(int i = 2; i < route.length; i++) {
            // 하나라도 다르면 같은 그룹이 아니기에 불가능
            if(index != find(route[i])){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    
    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b) {
            parent[b] = a;
        }
    }
    
    public static int find(int a) {
        if(a == parent[a]) {
            return a;
        }
        else {
            return parent[a] = find(parent[a]);
        }
            
    }
}