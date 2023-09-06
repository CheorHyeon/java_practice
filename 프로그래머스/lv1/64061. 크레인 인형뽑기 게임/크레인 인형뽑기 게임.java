import java.util.ArrayList;
import java.util.List;

class Solution {
	public int solution(int[][] board, int[] moves) {
		// 1. 인형을 집어 올리기(해당 열에서 마지막부터 0이 아닌것) -> 집어 올리면 해당 자리는 0으로 수정
		// 2. 결과에 하나씩 넣고, 넣고 나서 이전값과 같으면 사라지게
		// 위 과정을 moves 크기 만큼 반복

		List<Integer> resultList = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < moves.length; i++) {
			int move = moves[i] - 1;
			for (int j = 0; j < board.length; j++) {
				// 인형 찾으면 하나 넣기
				if (board[j][move] != 0) {
					resultList.add(board[j][move]);
					board[j][move] = 0;
					break;
				}
			}

			// 넣은 결과가 2개 이상이라면 사라지게 하기 위함
			if (resultList.size() >= 2) {
				int k = resultList.size() - 1;
				if (resultList.get(k).equals(resultList.get(k - 1))) {
					// k번째 및 이전꺼 삭제 => 어차피 2개 만나자 마자 삭제니깐 반복 필요 x
					resultList.remove(k);
					resultList.remove(k - 1);
					count += 2;
				}
			}
		}

		return count;
	}
}