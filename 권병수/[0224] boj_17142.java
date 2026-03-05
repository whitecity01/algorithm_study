import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class G3_17142_연구소 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M, data[][], list[], space, result;
    static List<Node> virus;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 연구소의 크기
        M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스의 개수

        // 0:빈칸, 1:벽, 2:비활성 바이러스 위치
        data = new int[N][N];
        list = new int[M]; // 선택할 바이러스의 번호
        space = 0; // 빈 공간의 개수
        virus = new ArrayList<>(); // 바이러스들 위치 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
                if(data[i][j] == 0) space ++;
                if(data[i][j] == 2) {
                    virus.add(new Node(i, j));
                }
            }
        }

        result = Integer.MAX_VALUE; // 최소 시간
        if(space == 0) { // 이미 전부 퍼져있다면 0 출력
            System.out.println(0);
        } else {
            select(0, 0);
            // 전부 퍼트릴 수 없다면 -1 출력
            System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        }
    }

    // 바이러스 개수 중에 M개를 순서상관없이 뽑는다.
    public static void select(int idx, int cnt) {
        if(cnt == M) { // M개의 바이러스가 선택됐다면 퍼트리기
            spread();
            return;
        }
        for (int i = idx; i < virus.size(); i++) {
            list[cnt] = i;
            select(i+1, cnt+1);
        }
    }

    public static void spread() {
        int remain = space; // 남아있는 공간
        int[][] visited = new int[N][N];
        Queue<Node> que = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            Node n = virus.get(list[i]);
            que.add(n);
            visited[n.x][n.y] = 1; // start를 1로 초기화
        }

        while (!que.isEmpty()) {
            Node q = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = q.x + dx[i];
                int ny = q.y + dy[i];
                // 범위밖에 나가거나 이미 방문했거나 가려는위치가 벽이면 패스
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] != 0 || data[nx][ny] == 1) continue;
                que.add(new Node(nx, ny));
                visited[nx][ny] = visited[q.x][q.y] + 1;
                // 빈칸이라면 남아있는 개수 -
                if (data[nx][ny] == 0) {
                    remain--;
                }
                
                // 모두 감였됐다면 최소 시간 갱신
                if(remain == 0) {
                    result = Math.min(result, visited[nx][ny] -1);
                }
            }
        }

    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
