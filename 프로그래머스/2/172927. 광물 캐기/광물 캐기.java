import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
	// 자원의 종류와 비용 저장하는 class
	class Mineral {
		private int cost;
		private String mineral;

		public Mineral(int cost, String mineral) {
			this.cost = cost;
			this.mineral = mineral;
		}

		public int getCost() {
			return cost;
		}

		public String getMineral() {
			return mineral;
		}
	}

	// 그룹의 index와 총 비용 저장하는 class
	class Node {
		private int groupIndex;
		private int totalCost;

		public Node(int groupIndex, int totalCost) {
			this.groupIndex = groupIndex;
			this.totalCost = totalCost;
		}

		public int getGroupIndex() {
			return groupIndex;
		}

		public int getTotalCost() {
			return totalCost;
		}
	}

	public int solution(int[] picks, String[] minerals) {
		/**
		 * 피로도를 최소화 하기 위해서는 자원을 5개 기준으로 묶고 5개의 비용을 계산한다.
		 * 비용이 제일 비싼건 다이아 곡괭이로 캐고, 비용이 제일 저렴한건 돌 곡괭이로 캔다.
		 */
		int answer = 0;

		// 1. 그룹 초기화
		int groupSize = minerals.length / 5 + 1;
		List<Mineral>[] group = new ArrayList[groupSize];
		for (int i = 0; i < groupSize; i++) {
			group[i] = new ArrayList<>();
		}

		// 2. 그룹 생성
		// 전체 곡괭이 갯수만큼 그룹 생성 가능 (1개의 곡괭이가 5개를 캘 수 있음)
		// 곡괭이 없이 그룹을 만들어도 캘 수 없다 (1개의 곡괭이로는 최대 5개니깐 총 곡괭이 수 이후에 나오는 그룹의 광물은 캘 수 없음)
		// 만일 곡괭이가 없는 그룹을 만들 시 그룹을 비용 기준으로 내림차순 정렬할 때 데이터 정합성이 안맞는 경우가 생긴다.
		// (캘 수 없는 자원의 비용이 더 높은 경우)
		int count = 0;
		int pickSum = Arrays.stream(picks).sum();
		for (int i = 0; i < groupSize; i++) {
			// 곡괭이가 0개 이면 탈출
			if (pickSum == 0) {
				break;
			}
			for (int j = count; j < minerals.length; j++) {
				count++;
				String mineral = minerals[j];
				// 자원별 비용 : 어떤 곡괭이로 캘 지 모르니 최대 비싼 돌 곡괭이로 최초
				int cost = 0;
				switch (mineral) {
					case "diamond":
						cost = 25;
						break;
					case "iron":
						cost = 5;
						break;
					default:
						cost = 1;
						break;
				}
				// 그룹에 자원 정보 저장
				group[i].add(new Mineral(cost, mineral));

				// 5개 저장했다면
				if (count % 5 == 0) {
					// 곡괭이 1개 감소
					pickSum--;
					break;
				}
			}
		}
		// 비용 저장(Node에 index와 cost 저장)
		List<Node> cost = new ArrayList<>();
		// 그룹의 수만큼 노드 생성
		for (int i = 0; i < group.length; i++) {
			int sum = group[i].stream()
				.mapToInt(g -> g.getCost())
				.sum();
			cost.add(new Node(i, sum));
		}

		// 3. 비용 기준 내림차순 정렬
		Collections.sort(cost, (o1, o2) -> Integer.compare(o2.getTotalCost(), o1.getTotalCost()));

		// 4. 그룹별 비싼것 부터 cost에 저장되어 있으니 다이아, 철, 돌 곡괭이 순으로 캐기
		// 그룹별로 모아진 Mineral을 가져와서 처리함
		for (int i = 0; i < cost.size(); i++) {
			// 다이아몬드 곡괭이 사용
			if (picks[0] > 0) {
				int groupNum = cost.get(i).getGroupIndex();
				// 피로도 계산
				for (Mineral m : group[groupNum]) {
					answer += 1;
				}
				// 다이아 곡괭이 감소
				picks[0]--;
			}
			// 철 곡괭이 사용
			else if (picks[1] > 0) {
				int node = cost.get(i).getGroupIndex();
				// 피로도 계산
				for (Mineral m : group[node]) {
					if (m.getMineral().equals("diamond"))
						answer += 5;
					else
						answer += 1;
				}
				// 철 곡괭이 감소
				picks[1]--;
			}
			// 돌 곡괭이 사용
			else if (picks[2] > 0) {
				int node = cost.get(i).getGroupIndex();
				// 피로도 계산
				for (Mineral m : group[node]) {
					if (m.getMineral().equals("diamond"))
						answer += 25;
					else if (m.getMineral().equals("iron"))
						answer += 5;
					else if (m.getMineral().equals("stone"))
						answer += 1;
				}
				// 돌 곡괭이 감소
				picks[2]--;
			}
			// 곡괭이 없으면 탈출
			else {
				break;
			}
		}

		return answer;
	}
}