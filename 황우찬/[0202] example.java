import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 p15684 사다리 조작
public class Main {

    static int [][] map;
    static int n, m, h;
    static boolean isFinish =false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int [h][n-1];

        // 이미 놓인 사다리 위치 입력받기
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            // a행 b열과 b+1열은 연결되어있음을 표시
            map[a][b] = 1;
        }

        // 사다리 놓는 갯수를 0부터 탐색하며 찾은 경우 더 이상 탐색하지 않음
        for (int maxDepth=0;maxDepth<4;maxDepth++) {
            dfs(0, 0, 0, maxDepth);
            if (isFinish) {
                System.out.println(maxDepth);
                break;
            }
        }
        if (!isFinish) System.out.println(-1);
    }

    static void dfs(int r, int c, int depth, int maxDepth) {
        if (depth == maxDepth) {
            if (checkMap())
                isFinish = true;
        } else {
            if (isFinish) return;
            for (int i=r;i<h;i++) {
                for (int j=c;j<n-1;j++) {
                    if (map[i][j] != 0) continue;
                    if (j-1 >= 0 && map[i][j-1] != 0) continue;
                    if (j+1 < n-1 && map[i][j+1] != 0) continue;

                    map[i][j] = 1;
                    dfs(i, j+1, depth+1, maxDepth);
                    map[i][j] = 0;
                }
                c = 0;
            }
        }
    }

    static boolean checkMap() {
        // 행 우선탐색
        for (int c=0;c<n;c++) {
            int pos = c;
            for (int r=0;r<h;r++) {
                // 왼쪽에 다리가 있으면 왼쪽으로 이동
                if (pos-1 >= 0 && map[r][pos-1] == 1)
                    pos--;
                    // 오른쪽에 다리가 있으면 오른쪽으로 이동
                else if (pos < n-1 && map[r][pos] == 1)
                    pos++;
            }
            if (pos != c) return false;
        }
        return true;
    }
}
