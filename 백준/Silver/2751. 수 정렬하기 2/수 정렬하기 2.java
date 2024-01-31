import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static int[] A, tmp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		A = new int[N + 1];
		tmp = new int[N + 1];
		// 1. 배열 입력 받기
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		// 2. merge sort
		merget_sort(1, N);
		// 3. 출력
		for (int i = 1; i <= N; i++) {
			bw.write(A[i] + "\n");
		}
		br.close();
		bw.close();
	}

	/*
		marge sort : 첫번째 그룹은 중간값까지, 두번째 그룹은 중간값 이후부터 끝까지
		- 각각 정렬
	*/
	private static void merget_sort(int s, int e) {
		// 정렬할 요소가 1개라면 종료
		if (e - s < 1) {
			return;
		}
		// 중간값 기준으로 그룹을 나누고 왼쪽과 오른쪽 정렬
		int m = s + (e - s) / 2;
		merget_sort(s, m);
		merget_sort(m + 1, e);
		for (int i = s; i <= e; i++) {
			tmp[i] = A[i];
		}
		int k = s;
		int index1 = s;
		int index2 = m + 1;
		// 한 그룹이 끝나기 전까지 반복
		while (index1 <= m && index2 <= e) {
			// 앞쪽이 더 크면 뒤쪽꺼(작은 수)가 먼저 나와야 오름차순이니깐 뒤쪽꺼 넣기
			if (tmp[index1] > tmp[index2]) {
				A[k] = tmp[index2];
				k++;
				index2++;
			}
			// 뒤쪽이 더 크면 앞쪽꺼(작은 수)가 먼저 나와야 오름차순이니깐 앞쪽꺼 넣기
			else {
				A[k] = tmp[index1];
				k++;
				index1++;
			}
		}
		// 한쪽 그룹이 모두 선택된 후 남아 있는 값 정렬하기
		while (index1 <= m) {
			A[k] = tmp[index1];
			k++;
			index1++;
		}

		while (index2 <= e) {
			A[k] = tmp[index2];
			k++;
			index2++;
		}

	}
}