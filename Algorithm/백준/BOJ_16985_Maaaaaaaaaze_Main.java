package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16985_Maaaaaaaaaze_Main {

	static int N, origin[][][], maze[][][], floorOrders[], rotateOrders[], result = Integer.MAX_VALUE;
	static boolean isSelected[];
	static int[] dx = {0, 0, 0, 0, -1, 1};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dz = {-1, 1, 0, 0, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = 5;
		origin = new int[N][N][N]; // z, y, x;
		maze = new int[N][N][N]; // z, y, x;
		floorOrders = new int[N];
		rotateOrders = new int[N];
		isSelected = new boolean[N];
		
		for(int z = 0; z < N; z++) {
			for(int y = 0; y < N; y++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x = 0; x < N; x++) {
					origin[z][y][x] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		floorPermutation(0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static void floorPermutation(int cnt) {
		if(cnt == N) {
			for(int i = 0; i < N; i++) {
				maze[i] = origin[floorOrders[i]];
			}
			rotatePermutation(0);
			return;
		}
		for(int i = 0; i < 5; i++) {
			if(isSelected[i]) continue;
			
			floorOrders[cnt] = i;
			isSelected[i] = true;
			
			floorPermutation(cnt + 1);
			isSelected[i] = false;
		}
	}

	private static void rotatePermutation(int cnt) {
		
		if(cnt == N) {
			setMaze();
			return;
		}
		
		for(int i = 1; i <= 4; i++) {
			rotateOrders[cnt] = i;
			rotatePermutation(cnt + 1);
		}
	}

	private static void setMaze() {
		int[][][] tempMaze = new int[N][N][N];
		for(int z = 0; z < N; z++) {
			tempMaze[z] = rotate(z, rotateOrders[z]);
		}
		
		if(tempMaze[0][0][0] == 0 || tempMaze[4][4][4] == 0) return;
		
		boolean[][][] visited = new boolean[N][N][N];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0, 0, 0}); // z, y, x, cnt
		visited[0][0][0] = true; 
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if(cur[0] == N - 1 && cur[1] == N - 1 && cur[2] == N - 1) {
				result = Math.min(result, cur[3]);
				return;
			}
			
			for(int d = 0; d < 6; d++) {
				int nz = cur[0] + dz[d];
				int ny = cur[1] + dy[d];
				int nx = cur[2] + dx[d];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N || nz < 0 || nz >= N) continue;
				if(tempMaze[nz][ny][nx] == 0) continue;
				if(visited[nz][ny][nx]) continue;
				
				queue.offer(new int[] {nz, ny, nx, cur[3] + 1});
				visited[nz][ny][nx] = true;
			}
		}
	}

	private static int[][] rotate(int floor, int rotate) {
		int[][] temp = new int[N][N];
		switch(rotate) {
		case 1:
			temp = maze[floor];
			break;
		case 2:
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					temp[j][N - 1 - i] = maze[floor][i][j];
				}
			}
			break;
		case 3:
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					temp[N - 1 - i][N - 1 - j] = maze[floor][i][j];
				}
			}
			break;
		case 4:
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					temp[N - 1 - j][i] = maze[floor][i][j];
				}
			}
			break;
		}
		return temp;
		
	}

}
