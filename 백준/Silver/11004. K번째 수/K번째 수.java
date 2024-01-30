import java.util.StringTokenizer;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        // 정렬하고
        quickSort(A, 0, N - 1, K - 1);
        // K번째 수 출력
        System.out.println(A[K-1]);
    }
    /*
        quick정렬 : 정렬 배열, 시작 위치, 종료 위치, 찾을 인덱스
    */
    public static void quickSort(int[] A, int S, int E, int K) {
        // 끝 인덱스가 뒤에 있을경우만 실행
        if(S < E) {
            int pivot = partition(A, S, E);
            // 정렬 결과가 바로 K번째 수라면 K번째 수를 이미 찾은것이니 종료
            if (pivot == K)
                return;
            // 만약 K번째 숫자가 중간값 나타내는 pivot의 왼쪽에 있다면 왼쪽만 정렬하면 됨
            else if (K < pivot)
                quickSort(A, S, pivot-1, K);
            // 만약 K번째 숫자가 중간값 나타내는 pivot 뒤의 인덱스라면 오른쪽만 정렬해보면 됨
            else
                quickSort(A, pivot + 1, E, K);
        }
    }
    
    /*
        두 구간으로 나눠주는 경계를 구하는 메서드
    */
    public static int partition(int[] A, int S, int E) {
        // 만일 정렬해야 할 부분이 2개의 원소만 가지고 있다면
        if(S + 1 == E) {
            // 앞의 인자가 더 크면 두 위치를 바꿔주고 끝낸다.
            if(A[S] > A[E]) swap(A, S, E);
            return E;
        }
        // 중간값을 피벗으로 잡고, 해당 인덱스를 첫번째로 옮긴다
        // 이 작업은 i와 j 이동 편하게 하기 위함
        int M = (S + E) / 2;
        swap(A, S, M);
        
        int pivot = A[S];
        int i = S + 1, j = E;
        while (i <= j) {
            // 뒷 부분은 피벗보다 작은 값을 만날때까지 인덱스를 줄임
            while(j >= S + 1 && pivot < A[j]) {
                j--;
            }
            // 앞 부분은 피벗보다 큰 값을 만날때까지 인덱스를 키움
            while(i <= E && pivot > A[i]) {
                i++;
            }
            // 각각의 지점을 swap해주고, start와 end가 만날때까지 반복해준다.
            if(i <= j) {
                swap(A, i++, j--);
            }
        }
        // 피벗 데이터를 나눠진 두 그룹의 경계 index에 저장
        A[S] = A[j];
        A[j] = pivot;
        return j;
    }
    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}