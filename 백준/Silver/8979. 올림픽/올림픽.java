import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static class Nation implements Comparable<Nation> {
		private int name;
		private int gold;
		private int silver;
		private int bronze;
		private int rank;

		public Nation(int name, int gold, int silver, int bronze) {
			this.name = name;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
			this.rank = 1;
		}

		@Override
		public int compareTo(Nation o) {
			if (this.gold == o.gold) {
				if (this.silver == o.silver) {
					return o.bronze - this.bronze;
				} else {
					return o.silver - this.silver;
				}
			} else {
				return o.gold - this.gold;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Nation> nationList = new ArrayList<>();
		// 1. 각 나라별 메달 수 초기화
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int country = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());
			nationList.add(new Nation(country, gold, silver, bronze));
		}
		// 2. 나라별 순위 정렬
		Collections.sort(nationList);
		// 3. 나라별 순위값 초기화
		for (int i = 1; i < nationList.size(); i++) {
			Nation beforeHighRankedCountry = nationList.get(i - 1);
			Nation currentCountry = nationList.get(i);

			// 동등한 랭크 조건 만족하는지 검사
			if (beforeHighRankedCountry.gold == currentCountry.gold
				&& beforeHighRankedCountry.silver == currentCountry.silver
				&& beforeHighRankedCountry.bronze == currentCountry.bronze) {
				currentCountry.rank = beforeHighRankedCountry.rank;
			} 
			// 동등한 인덱스가 아니면 현재 인덱스 번호로 초기화
			else {
				currentCountry.rank = i + 1;
			}
		}
		// 4. 순회하며 랭크 같은거 찾아서 출력
		for(Nation nation : nationList) {
			if(nation.name == K) {
				System.out.println(nation.rank);
				break;
			}
		}

	}
}