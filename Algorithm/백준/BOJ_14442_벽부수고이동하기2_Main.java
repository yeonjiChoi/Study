package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14442_벽부수고이동하기2_Main {
	
	static int N, M, K, map[][], answer;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Point {
		int r, c, k, cnt;

		public Point(int r, int c, int k, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
			this.cnt = cnt;
		}
	}	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		boolean flag = bfs();
		
		if(flag) System.out.println(answer);
		else System.out.println(-1);
	}

	private static boolean bfs() {

		boolean[][][] visited = new boolean[N][M][K+1];
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, 0, 1));
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			
			Point cur = queue.poll();
			if(cur.r == N-1 && cur.c == M-1) {
				answer = cur.cnt;
				return true;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc][cur.k]) continue;
				
				if(map[nr][nc] == 0) {
					queue.offer(new Point(nr, nc, cur.k, cur.cnt + 1));
					visited[nr][nc][cur.k] = true;
				} else {
					
					if(cur.k >= K) continue;
					if(visited[nr][nc][cur.k + 1]) continue;
					
					queue.offer(new Point(nr, nc, cur.k + 1, cur.cnt + 1));
					visited[nr][nc][cur.k + 1] = true;
				}
			}
		}
		return false;
	}
}
