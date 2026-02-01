import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

// SWEA 1249 보급로
public class SWEA_1249 {
    static int N;
    static int[][] data;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            data = new int[N][N];
            visited = new int[N][N]; // 방문처리 및 최단거리 저장
            result = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                char[] tmp = br.readLine().toCharArray();
                for (int j = 0; j < N; j++)
                    data[i][j] = tmp[j] - '0';
                Arrays.fill(visited[i], -1); // -1로 초기화
            }

            bfs();
            System.out.printf("#%d %d\n", t, visited[N-1][N-1]);

        }
    }

    public static void bfs() {
        ArrayDeque<Algo.D4_1249_BFS.Node> que = new ArrayDeque<>();
        que.add(new Algo.D4_1249_BFS.Node(0, 0));
        visited[0][0] = 0;

        while (!que.isEmpty()) {
            Algo.D4_1249_BFS.Node node = que.pop();

            for (int i = 0; i < 4; i++) { // 4방향 탐색
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (-1 < nx && nx < N && -1 < ny && ny < N) { // 범위 안에서만 탐색
                    // 아직 방문하지 않았거나, 방문했어도 더 빠른길이 있다면 업데이트
                    if (visited[nx][ny] == -1 || visited[nx][ny] > visited[node.x][node.y] + data[nx][ny]) {
                        visited[nx][ny] = visited[node.x][node.y] + data[nx][ny];
                        que.add(new Algo.D4_1249_BFS.Node(nx, ny));
                    }

                }
            }

        }
    }

    // x,y 좌표를 넣기위한 클래스 추가
    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
