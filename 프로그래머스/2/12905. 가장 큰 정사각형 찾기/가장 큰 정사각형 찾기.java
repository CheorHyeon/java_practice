class Solution{
    public int solution(int [][]board){
        // board의 행 또는 열이 1로 주어질 때 1
        if(board.length ==1 && board[0].length == 1){
            if(board[0][0] == 1) return 1;
            return 0;
        }
        // 1. dp 정의 : (0, 0) 부터 (i, j)번째 대각선 위치를 포함할 때 만들 수 있는 최대 정사각형 길이
        int[][] dp = new int[board.length][board[0].length];
        
        // 2. dp 초기화 : 첫줄, 첫 열의 경우 1이라면 자기 자신만 정사각형 됨
        for(int i = 0; i < board[0].length; i++) {
            dp[0][i] = board[0][i];
        }
        for(int i = 0; i < board.length; i++) {
            dp[i][0] = board[i][0];
        }
        
        int max = 0;
        
        // 3. dp 점화식을 통해 값 채우며 최대 정사각형 길이 갱신
        for(int i = 1; i < board.length; i++) {
            for(int j = 1; j < board[0].length; j++) {
                if(board[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
                    if(dp[i][j] > max) {
                        max = dp[i][j];
                    }
                }
            }
        }
        
        // 4. 반환
        return max * max;
    }
}