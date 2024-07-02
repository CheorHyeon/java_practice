class Solution {
    public int solution(String[][] board, int h, int w) {
        int result = 0;
       // 1. h, w위치의 색 추출
        String target = board[h][w];
        int[] dRow = new int[] {-1, 1, 0, 0};
        int[] dCol = new int[] {0, 0, -1, 1};
        // 2. 상/하/좌/우 를 보며 같은 색인지 확인하고 result++
        for(int i = 0; i < 4; i++) {
            int nextH = h + dRow[i];
            int nextC = w + dCol[i];
            if(nextH >= 0 && nextH < board.length && nextC >=0 && nextC < board[0].length) {
                if(board[nextH][nextC].equals(target)) {
                    result++;
                }
            }
        }
        // 3. 결과 반환
        return result;
    }
}