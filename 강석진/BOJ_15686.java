import java.util.Scanner;

public class BOJ_15686 {
	
	static class Pos {
		int r;
		int c;
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	private static int N;
	private static int M;
	private static int cnt2;
	private static int cnt1;
	private static int[] selected;
	private static int ans = Integer.MAX_VALUE;
	private static Pos[] chik;
	private static Pos[] home;

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	M = sc.nextInt();
    	
    	
    	int [][] arr = new int [N][N];
    	
    	for (int i=0;i<N;i++) {
    		for (int j=0;j<N;j++) {
    			arr[i][j] = sc.nextInt();
    			if (arr[i][j] == 1) {
    				cnt1++;
    			} else if (arr[i][j] == 2) {
    				cnt2++;
    			}
    		}
    	}
    	
    	// 집 기준으로 거리 계산해야 해서 
    	home = new Pos[cnt1];
    	chik = new Pos[cnt2];
    	selected = new int [M];
    	
    	int idx1 = 0;
    	int idx2 = 0;
    	for (int i=0;i<N;i++) {
    		for (int j=0;j<N;j++) {
    			if (arr[i][j] == 1) {
    				home[idx1++] = new Pos(i, j);
    			} else if (arr[i][j] == 2) {
    				chik[idx2++] = new Pos(i, j);
    			}
    		}
    	}
    	
    	comb(0, 0);
    	System.out.println(ans);
	}

	private static void comb(int cnt, int idx) { // cnt는 지금까지 고른 치킨집 개수, idx : 다음에 고를 수 있는 치킨집 번호
		if (cnt == M) {
			checkDistance();
		} else {
			for (int i=idx;i<cnt2;i++) {
				selected[cnt] = i;
				comb(cnt+1, i+1);
			}
		}
		
	}

	private static void checkDistance() {
		int sum = 0;
		for (int i=0;i<cnt1;i++) {
			int dis = Integer.MAX_VALUE;
			// 이 집에서 선택된 치킨집 들 중 가장 가까운 거리 찾기
			for (int j=0;j<M;j++){ // 선택된 치킨집들 
				dis = Math.min(dis,  Math.abs(home[i].r - chik[selected[j]].r) + Math.abs(home[i].c - chik[selected[j]].c));
			}
			sum += dis;
		}
		ans = Math.min(ans, sum);
	}
}
