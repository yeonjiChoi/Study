package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17822_원판돌리기_Main {

	static int N, M, T, map[][];
	static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 회전할 원판 번호
			int d = Integer.parseInt(st.nextToken()); // 회전 방향(0: 시계방향)
			int k = Integer.parseInt(st.nextToken()); // 이동칸

			// 1) x의 배수에 해당하는 원판 회전
			for(int i = x; i <= N; i += x)
				rotate(i, d, k);
			// 2-1) 인접한 같은 수 제거 -> 제거할 숫자가 없으면 false 리턴
			boolean flag = remove();
			// 2-2) 제거할 숫자가 없으면 평균을 구해서 값 갱신
			if(!flag) doAverage();
		}

		// 원판에 적힌 수의 합
		System.out.println(count());
	}

	// 디버깅용 출력 함수
	private static void show() {
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------------------");
	}
	
	private static int count() {
		int sum = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}

	// 원판에 적힌 수의 평균을 구하기, 평균보다 큰 수는 1 뺴기/작은 수는 1 더하기
	private static void doAverage() {
		int sum = 0, cnt = 0;
		double avg = 0;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue;
				
				cnt++;
				sum += map[i][j];
			}
		}
		
		avg = (double)sum / cnt;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue;
				
				if(map[i][j] < avg) map[i][j]++;
				else if(map[i][j] > avg) map[i][j]--;
			}
		}
	}

	private static boolean remove() {
		
		boolean flag = false;
		boolean[][] isRemoved = new boolean[N + 1][M];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				
				if(map[i][j] == 0) continue;
				
				for(int d = 0; d < 4; d++) {
					int nr = i + dir[d][0];
					int nc = j + dir[d][1];
					
					if(nc < 0) nc = M - 1;
					else if(nc >= M) nc = 0;
					
					if(nr <= 0 || nr > N) continue;
					
					if(map[nr][nc] == map[i][j]) {
						isRemoved[i][j] = true;
						flag = true;
					}
				}
			}
		}
		if(!flag) return false;

		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < M; j++) {
				if(isRemoved[i][j])
					map[i][j] = 0;
			}
		}
		return true;
	}

	private static void rotate(int x, int d, int k) {
		// 시계 방향 회전
		if(d == 0) {
			for(int j = 0; j < k; j++) {
				int temp = map[x][M - 1];
				for(int i = M - 2; i >= 0; i--)
					map[x][i + 1] = map[x][i];
				map[x][0] = temp;
			}
		}
		// 반시계 방향
		else {
			for(int j = 0; j < k; j++) {
				int temp = map[x][0];
				for(int i = 1; i < M; i++) {
					map[x][i - 1] = map[x][i];
				}
				map[x][M - 1] = temp;
			}
		}
	}

}
