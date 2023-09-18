import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

	boolean[][] visitBoard;
	boolean[][] visitTable;
	// 좌 우 하 상
	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, 1, -1};
	// 채운 개수
	int answer = 0;

	public int solution(int[][] game_board, int[][] table) {
		int boardSize = game_board.length;
		visitBoard = new boolean[boardSize][boardSize];
		visitTable = new boolean[boardSize][boardSize];

		// game_board에서 '빈 공간' 하나씩 추출
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				// 빈 공간이 아니거나 방문한 위치라면 넘어가기
				// 방문한 위치라는 것은 채울 수 있는 블록이 없다는 것
				if (game_board[i][j] == 1 || visitBoard[i][j]) {
					continue;
				}

				// 빈 공간 영역이 존재하면 bfs로 해당 공간의 좌료를 찾아 클래스(Position) 생성 후 리스트
				List<Position> emptyCoord = extractBlock(game_board, new Position(i, j), true);
				// 좌표 리스트를 가지고 2차원 배열에 그려준다(0, 0부터 시작)
				int[][] empty = makeBlock(emptyCoord);

				// table에서 블럭 영역 추출
				match:
				for (int k = 0; k < boardSize; k++) {
					for (int l = 0; l < boardSize; l++) {
						// 블록이 아니거나 방문한 적 있으면 다음 블록 검사
						// 방문한 적 -> 지금 채우고자 하는 빈 공간에 맞지 않은 블록이라 넘어감
						if (table[k][l] == 0 || visitTable[k][l]) {
							continue;
						}

						// 블록 영역의 좌표를 가지는 리스트 변환
						List<Position> blockCoord = extractBlock(table, new Position(k, l), false);
						// 빈 영역과 블록의 크기가 다르면 채울 필요도 없으니 다음 블록 검사
						if (emptyCoord.size() != blockCoord.size()) {
							continue;
						}

						// 블록 좌표 리스트를 가지고 2차원 배열에 그려준다.
						// row와 col 뽑아주는 이유는 블록 90도 회전 시 (0, 0)부터 그리기 위함
						// 블록과 빈공간 모두 0, 0에 옮겨놓고 같은지 확인하기 위함
						int[][] block = makeBlock(blockCoord);
						int row = blockCoord.get(0).maxX - blockCoord.get(0).minX + 1;
						int col = blockCoord.get(0).maxY - blockCoord.get(0).minY + 1;

						// 빈 영역과 블록 모양 확인
						for (int z = 0; z < 4; z++) {
							if (isSame(empty, block)) {
								// 모양이 같으면 블록을 써서 채운거니까 해당 블록의 위치를 0으로 지워준다
								for (int x = 0; x < blockCoord.size(); x++) {
									Position rollback = blockCoord.get(x);
									table[rollback.x][rollback.y] = 0;
								}
								// 채워진거니까 채운 칸의 개수를 더해줌
								answer += blockCoord.size();
								// 해당 빈공간 채웠으니 다음 빈공간을 찾으러감
								break match;
							}

							// 매칭이 안된경우 90도로 회전 시켜본다.
							// row, col을 스왑하는 이유는 90도 회전 시 크기가 변경되기 때문
							block = rotateBlock(block, row, col);
							int tmp = row;
							row = col;
							col = tmp;
						}
					}
				}
				// match 끝난 후 -> 해당 빈공간에 블록들 방문하며 탐색했었으니
				// 다른 빈공간에서도 매칭 안된 블록들을 참조해야 하니 초기화 해주기
				visitTable = new boolean[boardSize][boardSize];
			}
		}
		return answer;
	}

	public List<Position> extractBlock(int[][] board, Position p, boolean isBoard) {
		int boardSize = board.length;
		List<Position> list = new ArrayList<>();
		Queue<Position> eq = new LinkedList<>();

		eq.offer(p);

		if (isBoard) {
			visitBoard[p.x][p.y] = true;
		} else {
			visitTable[p.x][p.y] = true;
		}

		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;

		while (!eq.isEmpty()) {
			Position start = eq.poll();
			list.add(start);
			minX = Math.min(start.x, minX);
			minY = Math.min(start.y, minY);
			maxX = Math.max(start.x, maxX);
			maxY = Math.max(start.y, maxY);

			for (int i = 0; i < 4; i++) {
				int row = start.x + dx[i];
				int col = start.y + dy[i];

				// 상, 하, 좌, 우 좌표가 범위 내에 있는지 검사
				if (row < 0 || col < 0 || row > boardSize - 1 || col > boardSize - 1)
					continue;

				// 공백 찾아서 묶기라면
				if (isBoard) {
					// 공백이 아니거나 방문한 적이 없으면 방문 표시
					// 공백이 아닌건 -> 해당 공간에 이미 다른 블록으로 채워져있음
					// 방문한 적이 있음 -> 해당 공간이 이미 다른 빈공간에 의해 채워짐
					if (board[row][col] == 1 || visitBoard[row][col])
						continue;
					visitBoard[row][col] = true;
				}
				// 여유 블럭 찾아서 묶기라면
				else {
					// 여유 블럭은 1로 표시 : 0이면 여유 블럭이 없는 곳
					// 방문한적 : 이미 다른 위치에서 같은 블럭으로 묶은 경우
					if (board[row][col] == 0 || visitTable[row][col])
						continue;
					visitTable[row][col] = true;
				}
				eq.offer(new Position(row, col));

			}
		}

		// 최대, 최소의 위치를 블럭의 첫 시작 부분에 저장함
		list.get(0).minX = minX;
		list.get(0).minY = minY;

		list.get(0).maxX = maxX;
		list.get(0).maxY = maxY;

		return list;
	}

	public int[][] makeBlock(List<Position> list) {
		int[][] result = new int[50][50];
		int minX = list.get(0).minX;
		int minY = list.get(0).minY;

		int emptyBlockSize = list.size();
		for (int i = 0; i < emptyBlockSize; i++) {
			Position p = list.get(i);
			// 0, 0을 시작점으로 옮기기 위해 리스트 가장 최소값을 뺴줌
			result[p.x - minX][p.y - minY] = 1;
		}

		// 0, 0을 시작점으로 하는 블록 반환
		return result;
	}

	public boolean isSame(int[][] empty, int[][] block) {
		for (int i = 0; i < empty.length; i++) {
			for (int j = 0; j < empty[0].length; j++) {
				if (block[i][j] != empty[i][j]) {
					return false;
				}
			}
		}
		// 빈공간과 블록의 위치가 같으면 트루 아니면 false
		return true;
	}

	// 90도 회전
	public int[][] rotateBlock(int[][] block, int row, int col) {
		int[][] tempBlock = new int[50][50];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tempBlock[j][row - 1 - i] = block[i][j];
			}
		}
		return tempBlock;
	}

	class Position {
		int x;
		int y;
		int minX;
		int minY;
		int maxX;
		int maxY;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}