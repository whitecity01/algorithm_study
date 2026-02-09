import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Shark implements Comparable<Shark> {
        int r;	// 행 위치
        int c;	// 열 위치
        int s;	// 상어 속력
        int d;	// 상어 방향
        int z;	// 상어 사이즈

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        // 상어를 사이즈 순으로 비교
        @Override
        public int compareTo(Shark o) {
            return Integer.compare(this.z, o.z);
        }
    }

    static int ans = 0, r, c, m;
    static Shark [][] map;
    static int [] rows;
    static int [] cols;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        putArr();

        map = new Shark [r][c];
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int sharkR = Integer.parseInt(st.nextToken())-1;
            int sharkC = Integer.parseInt(st.nextToken())-1;
            int sharkS = Integer.parseInt(st.nextToken());
            int sharkD = Integer.parseInt(st.nextToken());
            int sharkZ = Integer.parseInt(st.nextToken());

            map[sharkR][sharkC] = new Shark(sharkR,sharkC,sharkS,sharkD,sharkZ);
        }
        PriorityQueue<Shark> pq = new PriorityQueue<>();
        for (int i=0;i<c;i++) {
            getShark(i);
            findShark(pq);
            moveShark(pq);
        }
        System.out.println(ans);
    }

    // 좌표 이동을 돕는 배열 초기화
    private static void putArr() {
        rows = new int[r * 2 - 2];
        cols = new int[c * 2 - 2];
        
        int len = -1;
        for (int i = 0; i < r; i++) rows[i] = ++len;
        for (int i = r; i < r * 2 - 2; i++) rows[i] = --len;
        len = -1;
        for (int i = 0; i < c; i++) cols[i] = ++len;
        for (int i = c; i < c * 2 - 2; i++) cols[i] = --len;
    }

    // 상어가 좌표 이동하는 함수
    private static void moveShark(PriorityQueue<Shark> pq) {
        while (!pq.isEmpty()) {
            Shark s = pq.poll();
            if (s.d <= 2)
                moveV(s);
            else
                moveH(s);
            map[s.r][s.c] = s;
        }
    }

    // 상어가 가로축 이동하는 함수
    // 3 = 오른쪽(증가), 4 = 왼쪽(감소)
    private static void moveH(Shark s) {
        if (c == 1) {
            s.c = 0;
            return ;
        }
        int len = c * 2 - 2;
        int nextC = s.c;
        
        // 만약 이동 방향이 감소하는 방향이라면
        if (s.d == 4)
            nextC = len - s.c;
        nextC += s.s;
        nextC %= len;
        if (nextC < c)
            s.d = 3;
        else
            s.d = 4;
        s.c = cols[nextC];
    }

    // 상어가 세로축 이동하는 함수
    // 1 = 위쪽(감소), 2 = 아래쪽(증가)
    private static void moveV(Shark s) {
        if (r == 1) {
            s.r = 0;
            return ;
        }
        int len = r * 2 - 2;
        int nextR = s.r;
        
        // 만약 이동 방향이 감소하는 방향이라면
        if (s.d == 1)
            nextR = len - s.r;
        nextR += s.s;

        nextR %= len;
        if (nextR < r)
            s.d = 2;
        else
            s.d = 1;
        s.r = rows[nextR];
    }

    // 상어를 찾는 함수
    private static void findShark(PriorityQueue<Shark> pq) {
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (map[i][j] != null) {
                    pq.add(map[i][j]);
                    map[i][j] = null;
                }
            }
        }
    }

    // 어부가 상어를 잡는 함수
    private static void getShark(int idx) {
        for (int i=0;i<r;i++) {
            if (map[i][idx] != null) {
                ans += map[i][idx].z;
                map[i][idx] = null;
                break;
            }
        }
    }
}
