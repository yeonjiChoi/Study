package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_색종이만들기_Main {
	
	static int N, map[][], white, blue ;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		partition(0, 0, N);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void partition(int r, int c, int n) {
		
		if(check(r, c, n)) {
			if(map[r][c] == 0) 
				white++;
			else 
				blue++;
			
			return;
		}
		
		int size = n / 2;
		
		partition(r, c, size);
		partition(r, c + size, size);
		partition(r + size, c, size);
		partition(r + size, c + size, size);
		
	}

	private static boolean check(int r, int c, int n) {
		
		int color = map[r][c];
		for(int i = r; i < r + n; i++) {
			for(int j = c; j < c + n; j++) {
				if(map[i][j] != color)
					return false;
			}
		}
		
		return true;
	}
}
