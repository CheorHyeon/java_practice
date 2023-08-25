import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws Exception {
		// 1. N 크기 입력 받음
		// 2, 구름이, 플레이어 시작 위치 저장
		// 3. 움직이는 판 생성
		// 4. 해당 판 위치에서 방향과 숫자 추출하여 이동
		// - 구름이, 플레이어 각각 게임 진행
		// - 이동하는 칸을 방문한 것이니 방문 수 +1, 2번 방문했을 때는 중복이니깐 1 빼주기

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		int N = Integer.parseInt(input);

		String goo_str = br.readLine();
		int[] Goo = parseInput(goo_str);

		String player_str = br.readLine();
		int[] player = parseInput(player_str);

		String[][] board = new String[N][N];
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().split(" ");
		}

		String result = play(board, Goo, player);

		System.out.println(result);

		br.close();
	}

	private static int[] parseInput(String input) {
		String[] parts = input.split(" ");
		return new int[] {Integer.parseInt(parts[0]) - 1, Integer.parseInt(parts[1]) - 1};
	}

	private static String play(String[][] board, int[] goo, int[] player) {
		// 방문한 곳 세는 변수, 맨 처음꺼 방문
		// 구름이 진행
		boolean[][] gameBoardGoo = new boolean[board.length][board.length];
		// 플레이어 진행
		boolean[][] gameBoardPlayer = new boolean[board.length][board.length];

		boolean end = false;
		int y = goo[0];
		int x = goo[1];

		gameBoardGoo[y][x] = true;
		int goormCount = 1;

		// 구름이 진행
		while (!end) {
			// 명령어 추출
			String commandString = board[y][x];
			int num = Integer.parseInt(commandString.substring(0, commandString.length()-1) + "");
			String command = commandString.substring(commandString.length()-1);

			// 1씩 증가시켜가며 이동
			for (int i = 0; i < num; i++) {
				// 위로 이동 명령어
				if (command.equals("U")) {
					y = (y - 1 + gameBoardGoo.length) % gameBoardGoo.length;
				}
				// 아래로 이동
				else if (command.equals("D")) {
					y = (y + 1 + gameBoardGoo.length) % gameBoardGoo.length;
				}
				// 왼쪽 이동
				else if (command.equals("L")) {
					x = (x - 1 + gameBoardGoo.length) % gameBoardGoo.length;
				}
				// 오른쪽 이동
				else if (command.equals("R")) {
					x = (x + 1 + gameBoardGoo.length) % gameBoardGoo.length;
				}

				if (gameBoardGoo[y][x]) {
					end = true;
					break;
				}
				goormCount++;
				gameBoardGoo[y][x] = true;
			}
		}

		end = false;
		y = player[0];
		x = player[1];

		gameBoardPlayer[y][x] = true;
		int playerCount = 1;

		while (!end) {
			// 명령어 추출
			String commandString = board[y][x];
			int num = Integer.parseInt(commandString.substring(0, commandString.length()-1) + "");
			String command = commandString.substring(commandString.length()-1);

			// 1씩 증가시켜가며 이동
			for (int i = 0; i < num; i++) {
				// 위로 이동 명령어
				if (command.equals("U")) {
					y = (y - 1 + gameBoardPlayer.length) % gameBoardPlayer.length;
				}
				// 아래로 이동
				else if (command.equals("D")) {
					y = (y + 1 + gameBoardPlayer.length) % gameBoardPlayer.length;
				}
				// 왼쪽 이동
				else if (command.equals("L")) {
					x = (x - 1 + gameBoardPlayer.length) % gameBoardPlayer.length;
				}
				// 오른쪽 이동
				else if (command.equals("R")) {
					x = (x + 1 + gameBoardPlayer.length) % gameBoardPlayer.length;
				}

				if (gameBoardPlayer[y][x]) {
					end = true;
					break;
				}
				playerCount++;
				gameBoardPlayer[y][x] = true;
			}
		}

		int max = Math.max(playerCount, goormCount);

		if (max == playerCount) {
			return "player " + max;
		}
		return "goorm " + max;
	}
}