import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        mData[] A = new mData[N];
        for(int i = 0; i < N; i++) {
            A[i] = new mData(Integer.parseInt(br.readLine()), i);
        }
        // 1. 데이터 정렬(value를 기준으로 정렬)
        // A[i] : A변수에 할당된 배열 인덱스는 정렬 후 인덱스가 된다.
        // 앞에서부터 작은 값대로 정렬
        // mData 내부의 index : 기존에 value가 가진 배열의 인덱스를 저장
        Arrays.sort(A);
        int Max = 0;
        // 2. 기존에 가진 index와 정렬하여 변경된 index(i)를 비교
        // 왼쪽으로 가장 많이 이동한 횟수를 구한다.(한 루프에서 swap이 일어나면 왼쪽으로 최대 1칸 이동)
        // 총 swap 횟수가 이제 가장 많이 왼쪽으로 이동한 횟수가 된다.
        for(int i = 0; i < N; i++) {
            if(Max < A[i].index - i) {
                Max = A[i].index - i;
            }
        }
        
        // 3. 총 swap횟수에 +1 출력한다.
        // 왜냐면 버블정렬에서는 루프에서 swap이 없을때가 이미 정렬이 다 된 상태기 때문에 조기 종료가 가능한 조건
        // 따라서 swap이 일어난 다음 루프에서 종료되는 것이 정답
        System.out.println(Max+1);
    }
    
    public static class mData implements Comparable<mData> {
        int value;
        int index;
        public mData(int value, int index) {
            this.value = value;
            this.index = index;
        }
        
        @Override
        public int compareTo(mData o) {
            // value 기준 오름차순 정렬
            return this.value - o.value;
        }
    }
}