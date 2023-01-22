package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20056_마법사상어와파이어볼_Main {

	// 격자 크기, 파이어볼 개수, 지속 시간
	static int N, M, K;
	static List<Fireball> map[][];

	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 북쪽 기준 시계방향 
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1}; // 북쪽 기준 시계방향 

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new ArrayList[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) 
				map[i][j] = new ArrayList<>();
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[r][c].add(new Fireball(m, s, d));
		}

		while(true) {

			move();
			divide();
			K--;
			if(K == 0) {
				int result = count();
				System.out.println(result);
				return;
			}
		}

	}

	private static void show() {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {

				if(map[i][j].size() == 0) {
					System.out.print("[             ]");
				}
				for(int k = 0; k < map[i][j].size(); k++) {
					System.out.print(map[i][j].get(k));
				}
			}
			System.out.println();
		}
	}
	
	private static int count() {
		int result = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j].size() == 0) continue;
				
				for(int k = 0; k < map[i][j].size(); k++) {
					Fireball fireball = map[i][j].get(k);
					result += fireball.m;
				}
			}
		}
		return result;
	}

	private static void divide() {
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j].size() <= 1) continue;
				
				int cnt = map[i][j].size();
				int sumM = 0;
				int sumS = 0;
				int sumD = 0;
				while(!map[i][j].isEmpty()) {
					Fireball fireball = map[i][j].remove(0);
					sumM += fireball.m;
					sumS += fireball.s;
					sumD += (fireball.d % 2);
				}
				
				int start = (sumD == 0 || sumD == cnt) ? 0 : 1;
				if((int)Math.floor(sumM / 5) == 0) continue;
				for(int d = start; d <= 7; d+=2) {
					map[i][j].add(new Fireball((int)Math.floor(sumM / 5), (int)Math.floor(sumS / cnt), d));
				}
			}
		}
	}

	private static void move() {

		List<Fireball> temp[][] = new ArrayList[N + 1][N + 1];

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) 
				temp[i][j] = new ArrayList<>();
		}

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {

				if(map[i][j].size() == 0) continue;

				for(int k = 0; k < map[i][j].size(); k++) {

					Fireball fireball = map[i][j].get(k);

					int nr = i + (dr[fireball.d] * fireball.s);
					int nc = j + (dc[fireball.d] * fireball.s);

					if(nr <= 0 || nr > N || nc <= 0 || nc > N) {
						if(nr <= 0) while(nr <= 0) nr += N;
						if(nc <= 0) while(nc <= 0) nc += N;
						if(nr > N) while(nr > N) nr -= N;
						if(nc > N) while(nc > N) nc -= N;
					}
					
					temp[nr][nc].add(fireball);
				}

			}
		}
		map = temp;
	}

	public static class Fireball {
		int m, s, d;

		public Fireball(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "[m=" + m + ", s=" + s + ", d=" + d + "]";
		}
		
	}
}
