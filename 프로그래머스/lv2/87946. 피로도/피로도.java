class Solution {
    int answer;
    boolean[] visited;
    int[][] dungeons;
    
    public int solution(int k, int[][] dungeons) {
       // 완전 탐색 -> dfs 풀이
        this.answer = 0;
        this.visited = new boolean[dungeons.length];
        this.dungeons = dungeons;
        dfs(0, k);
        return answer;
    }
    
    private void dfs(int cnt, int k) {
        for(int i = 0; i < dungeons.length; i++) {
            // 방문한 적이 없고 최소 피로도 이상이라면
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(cnt + 1, k - dungeons[i][1]);
                visited[i] = false;
            }
            
            answer = Math.max(cnt, answer);
        }
    }
}