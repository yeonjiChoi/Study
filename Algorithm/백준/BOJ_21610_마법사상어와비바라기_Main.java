package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기_Main {
	
	static int N, M, map[][][], cloud[][];
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1}; 
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1][2];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloud = new int[N + 1][N + 1];
		for(int i = N - 1; i <= N; i++) {
			for(int j = 1; j <= 2; j++) {
				cloud[i][j] = 1;
			}
		}
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			
			moveCloud(d, s);
			raining();
			waterCopyBug();
			newCloud();
			// showMap();
		}
		
		System.out.println(result());
	}

	private static int result() {
		int result = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				result += map[i][j][0];
			}
		}
		return result;
	}

	private static void showMap() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				System.out.print(cloud[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---------------------------------");

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				System.out.print(map[i][j][0] + " ");
			}
			System.out.println();
		}
		System.out.println("==================================");
	}
	
	private static void newCloud() {
		int[][] tempCloud = new int[N + 1][N + 1];

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				
				if(map[i][j][0] >= 2 && map[i][j][1] == 0) {
					tempCloud[i][j] = 1;
					map[i][j][0] -= 2;
				}
				
				map[i][j][1] = 0;
			}
		}	
		cloud = tempCloud;
	}

	private static void waterCopyBug() {
		
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j][1] == 1) {

					int count = 0;
					for(int d = 1; d <= 7; d += 2) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						
						if(nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
						if(map[nr][nc][0] != 0) count++;
					}
					
					map[i][j][0] += count;
				}
			}
		}		
	}

	private static void raining() {

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(cloud[i][j] == 1) {
					map[i][j][0]++;
					map[i][j][1] = 1;
				}
			}
		}
	}

	private static void moveCloud(int d, int s) {

		int[][] movedCloud = new int[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(cloud[i][j] == 1) {
					int nr = (i + (dr[d] * s)) % N;
					int nc = (j + (dc[d] * s)) % N;
					
					nr = nr <= 0 ? nr + N : nr;
					nc = nc <= 0 ? nc + N : nc;
					// System.out.println(i + " -> " + nr + " | " + j + " -> " + nc);
					movedCloud[nr][nc] = 1;
				}
			}
		}
		cloud = movedCloud;
	}
}
