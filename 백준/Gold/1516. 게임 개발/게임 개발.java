import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<List<Integer>> I = new ArrayList<>();
    static int[] time;
    static int[] parentNum;
    static int[] answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        time = new int[N+1];
        parentNum = new int[N+1];
        answer = new int[N+1];
        for(int i = 0; i <= N; i++) {
            I.add(new ArrayList<>());
        }
        for(int i = 1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int preTemp = Integer.parseInt(st.nextToken());
                if(preTemp == -1){
                    break;
                }
                I.get(preTemp).add(i);
                parentNum[i]++;
            }
        }
        
        topologicalSort();
        
        for(int i = 1; i <= N; i++) {
            System.out.println(answer[i]);
        }
    }
    
    public static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 1; i <= N; i++) {
            if(parentNum[i] == 0) {
                q.offer(i);
            }
        }
        
        while(!q.isEmpty()) {
            int vertex = q.poll();
            answer[vertex] += time[vertex];
            
            for(int i = 0; i < I.get(vertex).size(); i++) {
                int nextV = I.get(vertex).get(i);
                parentNum[nextV]--;
                if(parentNum[nextV] == 0) {
                    q.offer(nextV);
                }
                
                answer[nextV] = Math.max(answer[nextV], answer[vertex]);
            }
        }
    }
}