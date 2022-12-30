package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16562_친구비_Main {

	static int N, M, K, cost[], answer, cnt;
	static boolean map[][], visited[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[N + 1][N + 1];
		cost = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) 
			cost[i] = Integer.parseInt(st.nextToken());
			
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a][b] = true;
			map[b][a] = true;
		}
		
		visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {
			
			if(visited[i]) continue;
			answer += bfs(i);
		}
		
		if(answer <= K) 
			System.out.println(answer);
		else 
			System.out.println("Oh no");
	}
	
	private static int bfs(int no) {
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(no);
		visited[no] = true;
		
		int min = Integer.MAX_VALUE;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			min = Math.min(min, cost[cur]);
			
			for(int i = 1; i <= N; i++) {
				if(visited[i]) continue;
				if(!map[cur][i]) continue;
				
				queue.offer(i);
				visited[i] = true;
			}
		}
		
		return min;
		
	}

	public static class Friend {
		int no, val;

		public Friend(int no, int val) {
			super();
			this.no = no;
			this.val = val;
		}
	}
	
	
}
