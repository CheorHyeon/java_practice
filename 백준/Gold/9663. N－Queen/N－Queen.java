import java.util.*;
import java.io.*;

class Main {
    // i번째 열에 몇번째 행에 Queen이 위치하는지 나타내는 배열
    static int[] arr;
    // 정답 개수
    static int count = 0;
    // 목표로 하는 N개의 퀸 수
    static int targetQueenCount;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        targetQueenCount = N;
        
        recursive(0);
        System.out.println(count);
    }
    
    public static void recursive (int depth) {
        // 종료 조건 : N개의 퀸을 모두 배치한 경우
        if(depth == targetQueenCount) {
            count++;
            return;
        }
        // 실행 : 해당 depth열에 i번째 행 자리에 퀸을 넣어보고 되면 다음 재귀함수 호출
        // 안되면 퀸을 넣을 수 없음
        for(int i = 0; i < targetQueenCount; i++) {
            arr[depth] = i;
            if(canPositioningQueen(depth)) {
                recursive(depth + 1);
            }
        }
    }
    
    public static boolean canPositioningQueen(int col) {
        for(int i = 0; i < col; i++) {
            // 1. 이전 열에 위치한 것들과 같은 행에 위치하면 불가능
            if(arr[i] == arr[col]) {
                return false;
            }
             // 2. 이전 열에 위치한 것들의 대각선에 위치하면 불가능
            else if(Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }
        // 위 조건 제외시 설치 가능
        return true;
        
    }
}