package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499_주사위굴리기_Main {

	static int N, M, r, c, K, dice[], command[], map[][];
	static int[] dr = {0, 0, -1, 1}; // 동서북남
	static int[] dc = {1, -1, 0, 0}; // 동서북남
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken()); // 세로 크기
		M = Integer.parseInt(st.nextToken()); // 가로 크기
		r = Integer.parseInt(st.nextToken()); // 세로 위치
		c = Integer.parseInt(st.nextToken()); // 가로 위치
		K = Integer.parseInt(st.nextToken()); // 명령 수
		
		// 주사위, 명령, 지도 배열 초기화
		dice = new int[6]; // [0] 주사위 윗면, [5] 주사위 바닥면
		command = new int[K];
		map = new int[N][M];
		
		// 지도 배열 입력값 저장
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 명령어 배열 입력값 저장
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < K; i++) {
			move(Integer.parseInt(st.nextToken()));
		}
	}

	private static void move(int dir) {
		
		int nr = r + dr[dir - 1];
		int nc = c + dc[dir - 1];
		
		// 바깥으로 넘어가면 명령 무시
		if(nr < 0 || nr >= N || nc < 0 || nc >= M) return;
		
		if(dir == 1) // 동쪽으로 이동
			moveEast();
		else if(dir == 2) // 서쪽으로 이동
			moveWest();
		else if(dir == 3) // 북쪽으로 이동
			moveNorth();
		else // 남쪽으로 이동
			moveSouth();
		
		// 주사위가 이동한 칸의 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
		if(map[nr][nc] == 0) 
			map[nr][nc] = dice[5];
		// 0이 아닌 경우, 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사  + 칸에 쓰여 있는 수는 0
		else {
			dice[5] = map[nr][nc];
			map[nr][nc] = 0;
		}	
		
		// 주사위의 위치 갱신
		r = nr; c = nc;
		//주사위 상단 값 출력
		System.out.println(dice[0]);
		
	}

	private static void moveSouth() {
		int temp = dice[0];
		dice[0] = dice[1];
		dice[1] = dice[5];
		dice[5] = dice[4];
		dice[4] = temp;
	}

	private static void moveNorth() {
		int temp = dice[0];
		dice[0] = dice[4];
		dice[4] = dice[5];
		dice[5] = dice[1];
		dice[1] = temp;
	}

	private static void moveWest() {
		int temp = dice[0];
		dice[0] = dice[2];
		dice[2] = dice[5];
		dice[5] = dice[3];
		dice[3] = temp;
	}

	private static void moveEast() {
		int temp = dice[0];
		dice[0] = dice[3];
		dice[3] = dice[5];
		dice[5] = dice[2];
		dice[2] = temp;
	}

}
