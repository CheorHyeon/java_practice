import java.util.*;

public class Solution {
	public int[] solution(int []arr) {
		Stack<Integer> stack = new Stack<>();
		stack.add(arr[0]);
		for(int i=1; i<arr.length; i++) {
			if(stack.peek() != arr[i]){
				stack.add(arr[i]);
			}
		}
		return stack.stream().mapToInt(Integer::intValue).toArray();
	}
}