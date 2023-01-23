package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20057_마법사상어와토네이도_Main {
	
	static int sendPer[] = {1, 1, 2, 2, 5, 7, 7, 10, 10, 0}; // 0은 a자리
	
	static int sendLeftDir[][] = { //오른쪽 -> 왼쪽 기준
			{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}, // r 방향
			{1, 1, 0, 0, -2, 0, 0, -1, -1, -1}}; // c 방향
	static int sendRightdir[][] = {
			{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
			{-1, -1, 0, 0, 2, 0, 0, 1, 1, 1}};
	static int sendUpDir[][] = {
			{1, 1, 0, 0, -2, 0, 0, -1, -1, -1},
			{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}};
	static int sendDownDir[][] = {
			{-1, -1, 0, 0, 2, 0, 0, 1, 1, 1},
			{-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}};
	static int nextDir[][] = {
			{0, 1, 0, -1},
			{-1, 0, 1, 0}}; // 왼쪽, 아래, 오른쪽, 위
	
	static int N, map[][], total;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		
		int r = N / 2, c = N / 2;
		int move = 1, dir = 0; 

		while(true) {
			
			int repeat = move != N - 1 ? 2 : 3;
			for(int i = 0; i < repeat; i++) {
				for(int m = 0; m < move; m++) {
					r = r + nextDir[0][dir];
					c = c + nextDir[1][dir];
					
					moveSend(r, c, dir);
				}
				
				dir = (dir + 1) % 4;
			}
			
			move++;
			if(move == N) break;
		}
		
		int result = count();
		System.out.println(total - result);
	}
	
	private static void showMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------");
	}

	private static int count() {
		int result = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				result += map[i][j];
			}
		}
		return result;
	}

	private static void moveSend(int r, int c, int dir) {

		int sendDir[][] = null;
		switch(dir) {
		case 0 : 
			sendDir = sendLeftDir; break;
		case 1 : 
			sendDir = sendDownDir; break;
		case 2 :
			sendDir = sendRightdir; break;
		case 3 : 
			sendDir = sendUpDir; break;
		}
		
		int totalSend = map[r][c], movedSend = 0, leftSend = map[r][c]; 
		
		for(int d = 0; d < 10; d++) {
			int nr = r + sendDir[0][d];
			int nc = c + sendDir[1][d];
			
			int send = (int)(totalSend * ((double)sendPer[d] / 100)) ;
			movedSend += send;
			leftSend -= send;
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			
			if(d == 9) {
				map[nr][nc] += leftSend;
				continue;
			}
			
			map[nr][nc] += send;

		}
		map[r][c] = 0;

	}
}
