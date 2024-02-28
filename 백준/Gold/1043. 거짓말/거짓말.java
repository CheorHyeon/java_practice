import java.util.*;

public class Main {
    public static int[] parent;
    public static int[] trueP;
    public static ArrayList<Integer>[] party;
    public static int result;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();
        result = 0;
        trueP = new int[T];
        // 진실을 아는 사람 저장하기
        for(int i = 0; i < T; i++) {
            trueP[i] = sc.nextInt();
        }
        
        party = new ArrayList[M];
        for(int i = 0; i < M; i++) {
            party[i] = new ArrayList<Integer>();
            int partySize = sc.nextInt();
            // 파티 참여자 저장하기
            for(int j = 0; j < partySize; j++) {
                party[i].add(sc.nextInt());
            }
        }
        parent = new int[N+1];
        // 대표 노드 자기 자신으로 초기화
        for(int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        // 파티에 참여한 사람끼리 그룹화
        // 진실을 아는 사람이 낀 그룹과 끼지 않은 그룹으로 나누기 위함
        for(int i = 0; i < M; i++) {
            int firstPeople = party[i].get(0);
            for(int j = 1; j < party[i].size(); j++) {
                union(firstPeople, party[i].get(j));
            }
        }
        // 각 파티의 대표 노드와 진실을 아는 사람의 대표 노드가 같다면 과장 불가
        for(int i = 0; i < M; i++) {
            boolean isPossible = true;
            int cur = party[i].get(0);
            for(int j = 0; j < trueP.length; j++) {
                // 해당 파티에 참여한 사람 중 진실을 아는 사람과 만날사람이 포함된다면 바로 종료
                if(checkSame(cur, trueP[j])) {
                    isPossible = false;
                    break;
                }
            }
            if(isPossible) result++; // 모두 다르면 결과값 1 증가
        }
        
        System.out.println(result);
    }
        public static void union(int a, int b) {
            a = find(a);
            b = find(b);
            if(a != b) {
                parent[b] = a;
            }
        }
        
        public static int find(int a) {
            if(a == parent[a])
                return a;
            else
                return parent[a] = find(parent[a]);
        }
        
        public static boolean checkSame(int a, int b) {
            a = find(a);
            b = find(b);
            if(a == b) {
                return true;
            }
            return false;
        }
    }