public class Solution {
    int[] numbers;
    int target;
    int answer;
    
    void dfs (int index, int sum) {
        // 1. 탈출 조건
        if(index == numbers.length) {
            if(sum == target) {
                answer++;
            }
            return;
        }
        // 2. 수행 동작
        dfs (index + 1, sum + numbers[index]);
        dfs (index + 1, sum - numbers[index]);
    }
    
	public int solution(int[] numbers, int target) {
        // 각 자리의 숫자를 더하거나 빼기 가능 각각 2개의 경우
        // 20개 이하 -> 2^20 -> 100만번, 500만번 미만이라면 얼추 DFS 가능
        // 1. 수행동작(이번 턴 안에서 무엇을 할 지)
        // 2. 언제 탈출할 지 결정(탈출 조건) -> call된 인덱스가 배열의 마지막까지면 종료
        answer = 0;
        this.numbers = numbers;
        this.target = target;
        
        dfs(0, 0);
        
        return answer;
	}
}