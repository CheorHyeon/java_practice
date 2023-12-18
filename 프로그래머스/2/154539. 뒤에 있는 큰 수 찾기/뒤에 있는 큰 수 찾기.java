import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i< numbers.length; i++) {
            // stack에 idx값을 넣어 numbers 배열에서 비교
            while(!stack.empty() && numbers[stack.peek()] < numbers[i]) {
                // 가까운 큰 값으로 변경
                numbers[stack.pop()] = numbers[i];
            }
            stack.push(i); // index값 넣기
        }
        // 남은 index 값은 더 큰 녀석이 없기에 -1 넣어주기
        for(int i : stack) {
            numbers[i] = -1;
        }
        
        return numbers;
    }
}