import java.util.*;
import java.io.*;
public class Main {
    static long[] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 구간의 최솟값을 구하는 횟수
        int treeHeight = 0;
        int length = N;
        // 1. 트리의 높이 구하기 -> 입력 데이터 모두 리프노드에 넣을 수 있는 최소 높이
        while (length !=0) {
            length /= 2;
            treeHeight++;
        }
        // 2. 2^k * 2 크기의 트리로 사용할 배열 선언
        int treeSize = (int) Math.pow(2, treeHeight + 1);
        int leftNodeStartIndex = treeSize / 2; // 리프노드 시작 인덱스
        tree = new long[treeSize];
        for(int i = 1; i < tree.length; i++) {
            tree[i] = Integer.MAX_VALUE;
        }
        // 3. 데이터 입력 받기
        for(int i = leftNodeStartIndex; i < leftNodeStartIndex + N; i++){
            tree[i] = Long.parseLong(br.readLine());
        }
        // 4. 리프노드의 마지막 인덱스를 넘겨서 트리 초기화
        setTree(treeSize - 1);
        // 5. 질의 수행하기
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            s = s + leftNodeStartIndex - 1; // s = s + (2^k - 1)
            e = e + leftNodeStartIndex - 1; 
            System.out.println(getMin(s, e));
        }
        br.close();
    }
    
    private static long getMin(int s, int e) { // 범위의 최솟값을 구하는 함수
        long Min = Long.MAX_VALUE;
        while (s <= e) {
            if (s % 2 == 1) {
                Min = Math.min(Min, tree[s]); // 오른쪽 자식 노드 -> 부모가 인덱스 범위 벗어난 노드 포함(이전값)
                s++; // 따라서 현재 노드를 확인하고, 다음 노드의 부모를 가리키게 하기 위해 인덱스 조정
            }
            if (e % 2 == 0) { // 끝나는 노드가 왼쪽 자식노드라면, 해당 노드의 부모는 인덱스 범위를 벗어나 노드 포함(이후값)
                Min = Math.min(Min, tree[e]); // 따라서 현재 노드를 확인하고 이전 노드의 부모를 가리키게 하기 위해 인덱스 조정
                e--;
            }
            // 부모로 이동
            s = s / 2;
            e = e / 2;
        }
        return Min;
    }
    
    private static void setTree(int i) {
        while (i != 1) {
            if (tree[i / 2] > tree[i]) {
                tree[i / 2] = tree[i];
            }
            i--;
        }
    }
}