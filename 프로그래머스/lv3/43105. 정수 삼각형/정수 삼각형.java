class Solution {
    public int solution(int[][] triangle) {
        int answer = triangle[0][0];
        // 대각선 방향으로 한칸 오 또는 왼
        for(int i = 1; i < triangle.length; i++) {
            for(int j = 0; j < triangle[i].length; j++) {
                // 왼쪽 끝
                if( j == 0) {
                    triangle[i][j] += triangle[i-1][j];
                }
                // 오른쪽 끝
                else if (j == i) {
                    triangle[i][j] += triangle[i-1][j-1];
                }
                // 양 끝 숫자가 아니면 왼쪽 또는 오른쪽 대각선과의 합 중 큰 값
                else {
                    triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
                }
                
                answer = Math.max(answer, triangle[i][j]);
            }
        }
        
        return answer;
    }
}