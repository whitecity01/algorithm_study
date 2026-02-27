package homework_0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14567 {

	public static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] result = new int[N];
		List<Integer>[] list = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken())-1;
			int B = Integer.parseInt(st.nextToken())-1;
			list[A].add(B);
			arr[B]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i = 0; i < N; i++) {
			if(arr[i] == 0) {
				result[i] = 1;
				queue.add(i);
			}
		}
		
		boolean[] visited = new boolean[N];
		while(!queue.isEmpty()) {
			int current = queue.poll();
			visited[current] = true;
			for(int next : list[current]) {
				arr[next]--;
				if(arr[next] == 0) {
					result[next] = result[current]+1;
					queue.add(next);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println(result[i]);
		}
	}
}