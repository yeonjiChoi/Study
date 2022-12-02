import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593_상범빌딩_Main {
	static int L, R, C;
	static char map[][][];
	
	static int[] dr = {-1, 1, 0, 0, 0, 0};
	static int[] dc = {0, 0, -1, 1, 0, 0};
	static int[] dl = {0, 0, 0, 0, -1, 1};
	
	static Point start, end;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			L = Integer.parseInt(st.nextToken()); // 층
			R = Integer.parseInt(st.nextToken()); // 세로
			C = Integer.parseInt(st.nextToken()); // 가로
			if(L == 0 && R == 0 && C == 0) break;

			map = new char[R][C][L];

			for(int l = 0; l < L; l++) {
				for(int r = 0; r < R; r++) {
					String str = br.readLine();
					for(int c = 0; c < C; c++) {
						map[r][c][l] = str.charAt(c);
						
						if(map[r][c][l] == 'S') 
							start = new Point(r, c, l, 0);
						if(map[r][c][l] == 'E') 
							end = new Point(r, c, l, 0);
						
					}
				}
				br.readLine();
			}
			
			int move = escape();
			if(move == 0)
				System.out.println("Trapped!");
			else
				System.out.println("Escaped in " + move + " minute(s).");
			
		}
		
	}

	private static int escape() {
		Queue<Point> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[R][C][L];

		int min = Integer.MAX_VALUE;
		queue.offer(start);
		visited[start.r][start.c][start.l] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
		
			if(cur.r == end.r && cur.c == end.c && cur.l == end.l) {
				return cur.m;
			}
			
			for(int d = 0; d < 6; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				int nl = cur.l + dl[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C || nl < 0 || nl >= L) continue;
				if(map[nr][nc][nl] == '#') continue;
				if(visited[nr][nc][nl]) continue;

				queue.offer(new Point(nr, nc, nl, cur.m + 1));
				visited[nr][nc][nl] = true;
			}
		}
		return 0;
	}
	
	public static class Point {
		int r, c, l, m;

		public Point(int r, int c, int l, int m) {
			super();
			this.r = r;
			this.c = c;
			this.l = l;
			this.m = m;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", l=" + l + ", m=" + m + "]";
		}
	
	}
	
}
