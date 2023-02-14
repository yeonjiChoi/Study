package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615_오목_Main {

	static int N = 19, map[][];
	static int[] dr = {-1, 0, 1, 1}; 
	static int[] dc = {1, 1, 1, 0};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int j = 0; j < N; j++) {
			for(int i = 0; i < N; i++) {
				if(map[i][j] == 0) continue;
				
				for(int d = 0; d < 4; d++) {
					int count = check(i, j, d, map[i][j]);
					
					if(count == 5) {
						System.out.println(map[i][j]);
						System.out.println((i + 1) + " " + (j + 1));
						return;
					}
				}
			}
		}
		
		System.out.println(0);
	}



	private static int check(int r, int c, int d, int color) {
		
		int nr = r, nc = c, count = 1;

		while(true) {
			nr += dr[d];
			nc += dc[d];
		
			if(nr < 0 || nr >= 19 || nc < 0 || nc >= 19) break;
			if(map[nr][nc] != color) break;
			
			count++;
		}
		
		nr = r; nc = c;
		while(true) {
			nr -= dr[d];
			nc -= dc[d];
		
			if(nr < 0 || nr >= 19 || nc < 0 || nc >= 19) break;
			if(map[nr][nc] != color) break;
			
			count++;
		}
		
		return count;
	}


}
