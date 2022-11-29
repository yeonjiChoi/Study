import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589_보물섬_Main {

	static int N, M, answer;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0}; // 상좌하우
	static int[] dc = {0, 1, 0, -1}; // 상좌하우
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
		
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'W') continue;
				
				int cnt = bfS(i, j);
				answer = Math.max(answer, cnt);
			}
		}
		System.out.println(answer);
		
	}

	private static int bfS(int r, int c) {
		
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		queue.add(new Point(r, c, 0));
		visited[r][c] = true;
		
		int max = 0;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();

			max = Math.max(cur.cnt, max);
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc]) continue;
				
				if(map[nr][nc] == 'W') {
					max = Math.max(cur.cnt, max);
				}
				else {
					queue.offer(new Point(nr, nc, cur.cnt + 1));
					visited[nr][nc] = true;
				}
			}
		}
		return max;
	}
	
	public static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", cnt=" + cnt + "]";
		}

	}
}
