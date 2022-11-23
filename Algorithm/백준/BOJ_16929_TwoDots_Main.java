import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16929_TwoDots_Main {

	static int N, M;
	static char map[][];
	
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				boolean flag = bfs(i, j, map[i][j]);
				if(flag) {
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
		
	}

	private static boolean bfs(int r, int c, char start) {
		
		Queue<Dot> queue = new LinkedList<>();
		ArrayList<int[]> visitedDots = new ArrayList<>();
		visitedDots.add(new int[] {r, c});
		queue.add(new Dot(r, c, visitedDots));
		
		while(!queue.isEmpty()) {
			Dot cur = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				ArrayList<int[]> visited = copy(cur.visitedDots);
				
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(map[nr][nc] != start) continue;
				
				if(visited.size() >= 4) {
					if(nr == r && nc == c) {
						return true;
					}
				}
				
				int[] arr = new int[] {nr, nc};
				if(compare(visited, arr)) continue;
				
				visited.add(arr);
				queue.offer(new Dot(nr, nc, visited));
			}
		}
		return false;
		
	}

	public static ArrayList<int[]> copy(ArrayList<int[]> list) {
		ArrayList<int[]> copyList = new ArrayList<>();
		for(int[] arr : list) {
			copyList.add(arr);
		}
		return copyList;
		
	}
	
	public static boolean compare(ArrayList<int[]> list, int[] arr) {
		
		for(int[] temp : list) {
			if(temp[0] == arr[0] && temp[1] == arr[1])
				return true;
		}
		
		return false;
	}
	
	
	public static class Dot {
		int r, c;
		ArrayList<int[]> visitedDots;

		public Dot(int r, int c, ArrayList<int[]> visitedDots) {
			super();
			this.r = r;
			this.c = c;
			this.visitedDots = visitedDots;
		}

		@Override
		public String toString() {
			return "Dot [r=" + r + ", c=" + c + ", visitedDots=" + visitedDots + "]";
		}
		
	}
	
}
