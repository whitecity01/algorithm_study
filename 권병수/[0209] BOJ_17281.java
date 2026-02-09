import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_17281_baseball {
	static int[] list; // 순서 저장
	static boolean[] visited;
	static int[][] data;
	static int N, result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 이닝 수 // 9명의 선수
		// 0번 선수를 3번 타자로 // 타순은 이닝이 변경되어도 유지
		// 1~8번수 -> 순서저장
		data = new int[N][9];  
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) 
				data[i][j] = Integer.parseInt(st.nextToken());
		}
		
		result = 0;
		visited = new boolean[9];
		list =  new int[9];
		order(0);
		
		System.out.println(result);
	}
	
	// 0번 선수를 3번 타자로 copy해서 중간에 끼워 넣을까? list <-> 전환 귀찮
	// 3이면 넘어가는걸로
	public static void order(int cnt) {
		if(cnt == 9) {
			result = Math.max(result, cal());
			return;
		}
		if(cnt == 3) cnt++;
		for (int i = 0; i < 8; i++) {
			if(!visited[i]) {
				visited[i] = true;
				list[cnt] = i+1;
				order(cnt+1);
				visited[i] = false;
			}
		}
	}
	
	// player의 정보를 뭐로 저장하지, 배열?list? 루의 수가 항상 3개로 고정이므로 배열 선택
	public static int cal() {
		int p = 0;
		int[] player = new int[3];
		
		int idx = 0; // 현재타자, 타순은 초기화되지 않는다.
		for (int i = 0; i < N; i++) {
			int out = 0; // 아웃 횟수
			
			while(true) {
				int n = data[i][list[idx]]; // i번째이닝중 idx번째 타석에 설 선수의 결과 0~4
				if(n==0) out++;
				else if(n==4) p += home(player);
				else p += hit(n, player);
				
				idx = (idx + 1)%9;
				if(out == 3) {
					player = new int[3]; // out일때 루가 초기화되는걸 깜빡, 문제를 잘읽자
					break;
				}
			}
		}
		return p;
	}
	
	// 타자와 루선수들이 n번 만큼 움직인다
	public static int hit(int n, int[] player) {
		int s = 0;
		for (int i = 0; i < n; i++) { // n번 반복 
			for (int j = 2; j > 0; j--) { // 110 --> 111
				if(j==2 && player[j] == 1) s++;
				player[j] = player[j-1];
			}
			player[0] = 0;
		}
		player[n-1] = 1; // 타자의 위치ㅠ
		return s;
	}
	
	public static int home(int[] player) {
		int s = 1; // 홈런을 친 본인
		for (int i = 0; i < 3; i++) { // 루에 있는 선수들 수
			if(player[i] == 1) s++;
			player[i] = 0; // 홈런 후 루에 있는 선수 초기화
		}
		return s;
	}
	
}
