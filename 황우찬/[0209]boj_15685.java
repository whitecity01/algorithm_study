import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int x, y;
    static int [][] moves = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    
    // 좌표는 (0,0)부터 ~(100, 100)까지
    static boolean [][] map = new boolean[102][102];

    public static void main(String [] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            map[y][x] = true;
            List<Integer> directions = new ArrayList();
            directions.add(d);
            // 90도 방향으로 방향을 세대만큼 추가
            for (int j=1;j<=g;j++) spin90(directions);
            for (int l = 0;l<directions.size();l++) {
                int dir = directions.get(l);
                move(dir);
            }
        }
        int ans = 0;
        for (int r=0;r<=100;r++) {
            for (int c=0;c<=100;c++) {
                // 이동한 좌표로 정사각형을 만들 수 있다면
                if (map[r][c] && map[r+1][c] && map[r][c+1] && map[r+1][c+1])
                    ans++;
            }
        }

        System.out.println(ans);
    }

    static void move(int dir) {
        int ny = y + moves[dir][0];
        int nx = x + moves[dir][1];
        if (nx < 0 || nx > 100 || ny < 0 || ny > 100)
            return ;
        map[ny][nx] = true;
        y = ny;
        x = nx;
    }

    static void spin90(List<Integer> li) {
        int size = li.size()-1;
        for (int i=size;i>=0;i--) {
            li.add((li.get(i) + 1) % 4);
        }
    }
}
