package acmicpc.p3665;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int idx;
        int indegree=0;
        HashSet<Integer> next = new HashSet<>();

        Node() {}

        Node (int idx) {
            this.idx = idx;
        }
    }

    static int n;
    static acmicpc.p3665.Main.Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i=0;i<t;i++) {

            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodes = new acmicpc.p3665.Main.Node[n];
            for (int j=0;j<n;j++) nodes[j] = new acmicpc.p3665.Main.Node();

            for (int j=0;j<n;j++) {
                int idx = Integer.parseInt(st.nextToken());
                nodes[j].idx = idx;
                nodes[j].indegree = j;

                // idx는 1부터 n까지 숫자이기 때문에 배열에 사용하기 위해 -1을 함.
                for (int k=j-1;k>=0;k--) {
                    nodes[k].next.add(idx-1);
                }
            }

            // 인덱스 번호로 정렬 => nodes[0] = idx : 1, nodes[1] = idx : 2번이 옴.
            Arrays.sort(nodes, (o1, o2) -> {
                return o1.idx - o2.idx;
            });

            int m = Integer.parseInt(br.readLine());
            for (int j=0;j<m;j++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken())-1;
                int b = Integer.parseInt(st.nextToken())-1;

                if (nodes[a].next.contains(b)) {
                    // 만약 a 다음으로 b가 오는 경우 => 순위가 a가 b보다 앞인 경우
                    nodes[b].indegree--;
                    nodes[a].indegree++;
                    nodes[b].next.add(a);
                    nodes[a].next.remove(b);
                } else {
                    // 만약 b 다음으로 a가 오는 경우 => 순위가 b가 a보다 앞인 경우
                    nodes[a].indegree--;
                    nodes[b].indegree++;
                    nodes[a].next.add(b);
                    nodes[b].next.remove(a);
                }
            }
            System.out.println(solve());
        }
    }

    static String solve() {
        Queue<acmicpc.p3665.Main.Node> q = new ArrayDeque<>();
        for (int j=0;j<n;j++) {
            if (nodes[j].indegree == 0) q.add(nodes[j]);
        }

        boolean [] v = new boolean[n];
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            // q.size가 1보다 큰 경우는 어느 순위 하나를 확정 지을 수 없음.
            if (q.size() > 1) {
                return "?";
            }
            acmicpc.p3665.Main.Node node = q.poll();
            v[node.idx-1] = true;
            sb.append(node.idx).append(' ');

            for (int prev : node.next) {
                nodes[prev].indegree--;
                if (nodes[prev].indegree == 0)
                    q.add(nodes[prev]);
            }
        }
        // 만약 모든 노드를 방문했다면 정상적인 출력, 아니라면 불가능한 출력임.
        if (check(v)) return sb.toString();
        return "IMPOSSIBLE";
    }
    static boolean check(boolean [] v) {
        for (int i=0;i<n;i++) {
            if (!v[i]) return false;
        }
        return true;
    }
}
