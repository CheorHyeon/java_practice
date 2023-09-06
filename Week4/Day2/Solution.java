import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(b);
            graph[b].add(a);
        }

        List<Integer> result = new ArrayList<>();
        double density = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                Pair pair = bfs(i);
                List<Integer> temp = pair.list;
                double tempDensity = pair.value;

                if (Math.abs(tempDensity - density) < 1e-8) {
                    if (result.size() > temp.size()) {
                        result = temp;
                        density = tempDensity;
                    } else if (result.size() == temp.size()) {
                        if (temp.get(0) < result.get(0)) {
                            result = temp;
                            density = tempDensity;
                        }
                    }
                } else if (tempDensity > density) {
                    result = temp;
                    density = tempDensity;
                }
            }
        }

        for (int node : result) {
            System.out.print(node + " ");
        }

        sc.close();
    }

    public static Pair bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        Set<Integer> component = new HashSet<>();

        while (!q.isEmpty()) {
            int now = q.poll();

            if (visited[now]) {
                continue;
            }

            visited[now] = true;
            component.add(now);

            for (int to : graph[now]) {
                if (!visited[to]) {
                    q.add(to);
                }
            }
        }

        int edge = 0;

        for (int i : component) {
            for (int to : graph[i]) {
                if (component.contains(to)) {
                    edge++;
                }
            }
        }

        List<Integer> sortedList = new ArrayList<>(component);
        Collections.sort(sortedList);

        return new Pair(sortedList, (double) edge / component.size());
    }

    static class Pair {
        List<Integer> list;
        double value;

        Pair(List<Integer> list, double value) {
            this.list = list;
            this.value = value;
        }
    }
}