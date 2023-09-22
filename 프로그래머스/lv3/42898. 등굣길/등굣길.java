class Solution {
    public int solution(int m, int n, int[][] puddles) {
       int[][] maps = new int[n+1][m+1];
        maps[1][1] = 1;
        
        for(int[] puddle : puddles) {
            // (m, n)으로 위치 표기 -> [1]이 y, [0]이 x
            maps[puddle[1]][puddle[0]] = -1;
        }
        
        for(int i = 1; i<= n; i++) {
            for(int j=1; j<=m; j++) {
                if( i == 1 && j == 1) continue;
                
                // 웅덩이에서 우측으로 가도 갈 수 없으니 경우의 수 0으로 처리하기 위함
                    if(maps[i][j] == -1) {
                        maps[i][j] = 0;    
                        continue;
                    }
                if(i-1 >0) {
                    // 위 & 좌측 모두에서 올 수 있는 칸인 경우
                    if(j-1 > 0) {
                        maps[i][j] = (maps[i-1][j] % 1_000_000_007) + (maps[i][j-1] % 1_000_000_007);
                    }
                    // 위측에서만 올 수 있는 칸이라면
                    else {
                        maps[i][j] = (maps[i-1][j] % 1_000_000_007);
                    }
                }
                // 좌측에서만 올 수 있는 칸인 경우
                 else if(j-1 >0) {
                        maps[i][j] = (maps[i][j-1] % 1_000_000_007);
                }
            }
        }
        return maps[n][m] % 1_000_000_007 ;     
    }
}