import java.util.*;
import java.io.*;
public class Main{
    static ArrayList<cNode>[] A;
    static long lcm;
    static boolean visited[];
    static long D[];
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = sc.nextInt();
        A = new ArrayList[N];
        visited = new boolean[N];
        D = new long[N];
        lcm = 1;
        // 1. 비율 저장할 그래프 생성
        for(int i = 0; i < N; i++) {
            A[i] = new ArrayList<cNode>();
        }
        // 비율 저장 및 최소공배수 구하기
        for(int i = 0; i < N - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();
            A[a].add(new cNode(b, p, q));
            A[b].add(new cNode(a, q, p));
            lcm *= (p * q / gcd(p, q));  // 최소공배수 : 두 수의 곱을 최대 공약수로 나눈 것
        }
        // 임의의 노드에 최소 공배수 삽입 후 dfs탐색
        D[0] = lcm;
        // 2. dfs 탐색으로 최소공배수에 맞는 비율 구하기
        dfs(0);
        // 3. 구한 질량들이 배수 관계일 수도 있으니 최대공약수를 구해서 나눠서 최소한의 질량 구하기
        long mgcd = D[0];
        // 최대공약수 구하기
        for(int i = 1; i < N; i++) {
            mgcd = gcd(mgcd, D[i]);
        }
        // 4. 최대 공약수로 나눈 수 출력
        for(int i = 0; i < N; i++) {
            bw.write(D[i] / mgcd + " ");
        }
        bw.close();
    }
    
    public static long gcd(long a, long b) {
        if (b==0)
            return a;
        else
            return gcd(b, a%b);
    }
    public static void dfs(int node) {
        visited[node] = true;
        for(cNode i : A[node]) {
            int next = i.getB();
            if(!visited[next]){
                D[next] = D[node] * i.getQ() / i.getP(); // 비율로 다음 노드 갱신
                dfs(next);
            }
        }
    }
}

class cNode {
    int b;
    int p;
    int q;
    public cNode(int b, int p, int q) {
        this.b = b;
        this.p = p;
        this.q = q;
    }
    public int getB() {
        return b;
    }
    public int getP() {
        return p;
    }
    public int getQ() {
        return q;
    }
}