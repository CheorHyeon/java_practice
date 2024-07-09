import java.util.*;
class Solution {
	class Subject{
		String name;
		int start;
		int playTime;
		Subject(String name, int start, int playTime) {
			this.name = name;
			this.start = start;
			this.playTime = playTime;
		}

	}
	public String[] solution(String[][] plans) {
		String[] answer = new String[plans.length];
		int idx = 0;
		PriorityQueue<Subject> q = new PriorityQueue<>((o1, o2) -> (o1.start - o2.start));
		for(String[] p : plans) {
			q.add(new Subject(p[0], convertTime(p[1]), Integer.parseInt(p[2])));
		}

		Subject cur = q.poll();
		// 현재 시점을 첫 작업 만난 시점으로 초기화
		int now = cur.start;
		Stack<Subject> stack = new Stack<>();
		while (true) {
			// case 1 : 현재 작업중에 새로운 작업을 만난다면 일시정지
			if(!q.isEmpty() && now + cur.playTime > q.peek().start) {
				// 작업 일부 진행 했으니 남은 시간으로 작업 일시정지
				 stack.push(new Subject(cur.name, cur.start, cur.playTime - (q.peek().start - now)));
				 // 다음 작업 만난 시점으로 갱신
				 now = q.peek().start;
				 cur = q.poll();
			}
			// case 2 : 현재 작업을 다 끝내기에 충분한 시간
			else {
				// 과제 종료
				answer[idx++] = cur.name;
				now += cur.playTime;

				// case 2-1 : 작업이 다 끝난 시점에 새로운 작업을 시작할 때라면 작업 시작
				if(!q.isEmpty() && now == q.peek().start) {
					cur = q.poll();
				}
				// case 2-2 : 새로운 작업까지 시간이 남았고, 일시정지 작업이 있다면 이어서 시작
				else if(!stack.isEmpty()) {
					cur = stack.pop();
				}
				// case 2-3 : 새로운 작업까지 시간이 남았고 일시정지 작업이 없다면 잠시 대기하고 시작
				else if(!q.isEmpty()) {
					cur = q.poll();
					now = cur.start;
				}
				// 나머지 경우는 작업 종료
				else break;
			}
		}

		return answer;
	}

	public int convertTime(String t){
		String[] str = t.split(":");
		int min = Integer.parseInt(str[0])*60 + Integer.parseInt(str[1]);
		return min;
	}
}