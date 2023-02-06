package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058_마법사상어와파이어스톰_Main {

	static int SIZE, N, Q, map[][], temp[][];
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		SIZE = (int) Math.pow(2, N);
		map = new int[SIZE][SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < SIZE; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			
			divide(L);
//			show();
			checkIce();
		}
		int total = count();
		int biggest = bfs();
		
		System.out.println(total);
		System.out.println(biggest);
	}

	private static void show() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---------------------------------");
	}
	
	private static int bfs() {
		
		int biggest = 0;
		boolean[][] visited = new boolean[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				
				if(visited[i][j]) continue;
				if(map[i][j] == 0) continue;
				
				Queue<int[]> queue = new LinkedList<>();
				queue.offer(new int[] {i, j});
				visited[i][j] = true;
				
				int count = 0;
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					count++;
					
					for(int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						
						if(nr < 0 || nc < 0 || nr >= SIZE || nc >= SIZE) continue;
						if(visited[nr][nc]) continue;
						if(map[nr][nc] == 0) continue;
						
						queue.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
				biggest = Math.max(biggest, count);
			}
		}
		
		return biggest;
	}

	private static int count() {
		int total = 0;
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				total += map[i][j];
			}
		}
		return total;
	}

	private static void checkIce() {
		
		temp = new int[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				temp[i][j] = map[i][j];
			}
		}
		
		
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				
				if(map[i][j] == 0) continue;
				
				int count = 0;
				for(int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					if(nr < 0 || nc < 0 || nr >= SIZE || nc >= SIZE) continue;
					if(map[nr][nc] > 0) count++;
				}
				if(count <= 2)
					temp[i][j]--;
			}
		}
		
		map = temp;
	
	
	}

	private static void divide(int l) {
		
		if(l == 0) return;
		
		int size = (int)Math.pow(2, l);

		for(int i = 0; i < SIZE; i+=size) {
			for(int j = 0; j < SIZE; j+=size) {
				rotate(i, j, size);
			}
		}
	}


	private static void rotate(int i, int j, int size) {
		
		int[][] copy = new int[size][size];
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				copy[r][c] = map[i + r][j + c]; 
			}
		}
		
		int[][] temp = new int[size][size];
		for(int r = 0; r < size; r++) {
			for(int c = 0; c < size; c++) {
				temp[c][size - r - 1] = copy[r][c];
			}
		}
		
		for(int r = i; r < i+size; r++) {
			for(int c = j; c < j+size; c++) {
				map[r][c] = temp[r - i][c - j];
			}
		}
	}

}
