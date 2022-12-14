package BOJ;

import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2174_로봇시뮬레이션_Main {
	
	static int A, B, N, M, map[][]; // 가로 크기, 세로 크기, 로봇 수, 명령 수
	static int[] dy = {-1, 0, 1, 0}; // NESW
	static int[] dx = {0, 1, 0, -1}; // NESW
	
	static boolean isCrashedWall, isCrashedRobot;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		map = new int[B + 1][A + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 로봇 수
		M = Integer.parseInt(st.nextToken()); // 명령 수
		
		int[][] robots = new int[N + 1][3]; // x좌표, y좌표, 방향
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = B - Integer.parseInt(st.nextToken()) + 1;
			int d = change(st.nextToken().charAt(0));
			
			robots[n + 1] = new int[] {x, y, d};
//			System.out.println("x : " + x + " y : " + y);
			map[y][x] = n + 1;
		}
		
		///////////////////////////// 디버그 ///////////////////////////////////
//		for(int i = 0; i <= N; i++) {
//			System.out.println(Arrays.toString(robots[i]));
//		}
		/////////////////////////////////////////////////////////////////////
//		show();
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			char CMD = st.nextToken().charAt(0);
			int CNT = Integer.parseInt(st.nextToken());
			
			for(int c = 0; c < CNT; c++) {
				show();
				if(CMD == 'L') {
					int dir = doLeft(robots[R][2]);
					robots[R][2] = dir;
				}
				else if(CMD == 'R') {
					int dir = doRight(robots[R][2]);
					robots[R][2] = dir;
				}
				else if(CMD == 'F') {
					map[robots[R][1]][robots[R][0]] = 0;
					
					int[] robot = doForward(robots[R]);
					
					if(isCrashedRobot) {
						System.out.printf("Robot %d crashes into robot %d", R, robot[0]);
						return;
					}
					if(isCrashedWall) {
						System.out.printf("Robot %d crashes into the wall", R);
						return;
					}
					
					robots[R] = robot;
					map[robots[R][1]][robots[R][0]] = R;
				}
				 
			}
			
		}
		System.out.println("OK");
	}

	private static int change(char ch) {
		if(ch == 'N') return 0;
		else if(ch == 'E') return 1;
		else if(ch == 'S') return 2;
		else if(ch == 'W') return 3;
		
		return 0;
	}
	
	private static int doLeft(int dir) {
		return dir - 1 < 0 ? 3 : dir - 1;
	}

	private static int doRight(int dir) {
		return dir + 1 > 3 ? 0 : dir + 1;
	}
	
	private static int[] doForward(int[] pos) {
		int nx = pos[0] + dx[pos[2]];
		int ny = pos[1] + dy[pos[2]];
		
		if(nx <= 0 || nx > A || ny <= 0 || ny > B) {
			isCrashedWall = true;
			return new int[] {0, 0, 0};
		}
		
		if(map[ny][nx] > 0) {
			isCrashedRobot = true;
			return new int[] {map[ny][nx], map[ny][nx], map[ny][nx]};
		}
		
		return new int[] {nx, ny, pos[2]};
	}
	
	private static void show() {
		for(int i = 0; i <= B; i++) {
			for(int j = 0; j <= A; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("=============================");
	}
}
