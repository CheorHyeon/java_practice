import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Main {
	public static void main(String[] args) throws Exception {
		// 3개의 문자열로 나누는 모든 case 생성
		// 중복 없는 P 집합, 알파벳 순 정렬
		// 각 나눈 case의 합을 구한 리스트
		// 리스트의 최대값 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();

			String S = br.readLine();
			Set<String[]> set = new LinkedHashSet<>();
			// 문장 나눈 모든 조합 넣기
			// 중복 발생 경우 삭제를 위해 set 도입
			for(int j=1; j<=S.length()-2; j++) {
				for(int k=j+1; k<=S.length()-1; k++){
					String[] tmp = new String[3];
					tmp[0] = S.substring(0, j);
					tmp[1] = S.substring(j, k);
					tmp[2] = S.substring(k);
					set.add(tmp);
				}
			}
			// 중복 없는 P 생성
			// 1. P 선언
			Set<String> P = new LinkedHashSet<>();

			// 2. P에 String 순서대로 넣기
			// 중복 없이 하기 위해 set
			for(String[] s : set) {
				for(String a : s) {
					P.add(a);
				}
			}

			// 3. P 정렬
			List<String> sortedP = P.stream().sorted().collect(Collectors.toList());

			// 각 나눈 케이스의 i + j + k의 합 저장
			// 100자 -> 100 + 99 + 98 이라해도 int로 충분
			List<Integer> list = new ArrayList<>();

			for(String[] s : set) {
				int sum = 0;
				for(String tmpS : s) {
					// 리스트에서 일치하는 거 있으면 인덱스+1값 더하기
					for(int i=0; i<sortedP.size(); i++) {
						if(sortedP.get(i).equals(tmpS)){
							sum += i+1;
						}
					}
				}
				// String 배열 별 합을 리스트에 추가
				list.add(sum);
			}

			// 최대 정수 출력
			System.out.println(Collections.max(list));
		}

	}