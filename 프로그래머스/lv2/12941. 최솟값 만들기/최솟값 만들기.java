import java.util.*;
class Solution
{
	public int solution(int[] A, int[] B)
	{
		int answer = 0;
		// 가장 큰수랑 가장 작은 수 곱
		Arrays.sort(A);
		Arrays.sort(B);
		for(int i = 0; i<A.length; i++) {
			answer += A[A.length - 1 - i] * B[i];
		}

		return answer;
	}
}