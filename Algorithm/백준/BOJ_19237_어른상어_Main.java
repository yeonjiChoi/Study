package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_19237_어른상어_Main {

	static int N, M, K;
	static int[] dr = {-1, 1, 0, 0}; // 위 아래 왼쪽 오른쪽
	static int[] dc = {0, 0, -1, 1}; // 위 아래 왼쪽 오른쪽

	static int map[][];
	static Smell[][] smellMap;
	static Shark[] sharks;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N]; // 상어 위치 맵 배열
		smellMap = new Smell[N][N]; // 냄새 맵 배열
		sharks = new Shark[M]; // 상어 정보 관리  

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken()); // 상어 번호
				if(num != 0) {
					map[i][j] = num; // 상어 위치 표시
					smellMap[i][j] = new Smell(num, K); // 냄새 정보 표시
				}
			}
		}

		int[] dirInput = new int[M]; // 각 상어에 대한 방향 정보 배열
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) 
			dirInput[i] = Integer.parseInt(st.nextToken()) - 1;

		for(int i = 0; i < M; i++) {
			int[][] dirs = new int[4][4]; // 각 방향에 대한 우선순위 방향 배열
		
			for(int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < 4; k++) {
					dirs[j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
			// 상어 정보 관리 배열
			sharks[i] = new Shark(i + 1, dirInput[i], dirs, true);
		}

		int result = 0; // 시간 
		while(true) {
			move(); // 상어 이동
			result++; // 시간 흐름

			// 격자에 남아있는 상어가 1마리(1번 상어)일 경우 반복문 종료
			if(count() == 1) { 
				System.out.println(result);
				return;
			}
			
			// 1000초가 지나도 다른 상어가 남아있다면 -1 출력 후 반복문 종료
			if(result == 1000) {
				System.out.println(-1);
				return;
			}
		}


	}

	// 남아있는 상어 수를 카운트하는 함수
	private static int count() {
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 0) cnt++;
			}
		}
		return cnt;
	}

	// 격자 위 냄새 상태 변경
	private static void smellChange() {

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(smellMap[i][j] == null) continue;
				Smell smell = smellMap[i][j];

				// 냄새의 남은 상태가 1이면 사라짐
				if(smell.k == 1) smellMap[i][j] = null;
				// 냄새 상태 -1 감소
				else smell.k--;
			}
		}

	}

	private static void move() {
		// 상어들의 이동 후 정보 : 2차원 리스트 배열 생성 및 초기화
		List<Integer> temp[][] = new ArrayList[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				temp[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 0) continue;

				int num = map[i][j]; // 상어 번호
				Shark shark = sharks[num - 1]; // num번의 상어 정보
				int[] order = shark.pri[shark.dir]; // num번 상어의 방향 우선순위 배열
				
				// 1. 상어는 빈 칸으로 우선 이동
				boolean isEmpty = false; // 빈 칸으로 이동 가능한지 체크
				for(int d = 0; d < 4; d++) {
					int nr = i + dr[order[d]];
					int nc = j + dc[order[d]];

					// 배열 밖으로 벗어날 수 없음 
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					// 빈 칸이 아닐 경우 이동 불가
					if(map[nr][nc] != 0) continue;
					// 빈 칸이어도 다른 상어의 냄새가 있다면 이동할 수 없음
					if(smellMap[nr][nc] != null) continue;

					// 임시 배열에 이동한 상어 추가
					temp[nr][nc].add(shark.num);
					// 변한 방향으로 상어 정보 갱신
					shark.dir = order[d];
					// 빈 칸으로 이동했음 = true
					isEmpty = true;
					break;
				}

				// 2. 빈 칸이 없다면 인접한 칸 중 자신의 냄새가 나는 곳으로 이동
				if(!isEmpty) {
					for(int d = 0; d < 4; d++) {
						int nr = i + dr[order[d]];
						int nc = j + dc[order[d]];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						// 이동할 칸에 다른 상어의 냄새가 있다면 이동 불가
						if(smellMap[nr][nc] != null && (smellMap[nr][nc].num != shark.num)) continue;

						temp[nr][nc].add(shark.num);
						shark.dir = order[d];
						break;
					}
				}
			}
		}
		
		// 상어가 이동한 후 격자의 냄새 상태 변경
		smellChange();
		
		// 이동 후 격자의 상태를 갱신 
		int map2[][] = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 상어가 없는 칸은 0
				if(temp[i][j].size() == 0) 
					map2[i][j] = 0;
				// 상어가 1마리 있는 칸
				else if(temp[i][j].size() == 1) {
					map2[i][j] = temp[i][j].get(0);
					smellMap[i][j] = new Smell(sharks[map2[i][j] - 1].num, K);
				}
				// 상어가 2마리 이상 있는 칸
				else {
					int min = Integer.MAX_VALUE;
					for(int k = 0; k < temp[i][j].size(); k++) 
						min = Math.min(min, temp[i][j].get(k));
					// 가장 작은 숫자의 상어만 남음
					map2[i][j] = min;
					smellMap[i][j] = new Smell(sharks[map2[i][j] - 1].num, K);
				}
			}
		}
		map = map2;
	}

	// 디버깅용 : 격자 위 상어 위치 출력
	public static void showMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------");
	}
	// 디버깅용 : 격자 위 냄새 상태 출력
	public static void showSmellMap() {

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(smellMap[i][j] != null) 
					System.out.print("[ " + smellMap[i][j].num + " " + smellMap[i][j].k + " ]" );
				else {
					System.out.print("[ 0 0 ]" );
				}
			}
			System.out.println();
		}
		System.out.println("-------------------------");
	}

	public static class Smell {
		int num, k;

		public Smell(int num, int k) {
			super();
			this.num = num;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Smell [num=" + num + ", k=" + k + "]";
		}
	
	}

	public static class Shark {
		int num, dir, pri[][];
		boolean isAlive;

		public Shark(int num, int dir, int[][] pri, boolean isAlive) {
			super();
			this.num = num;
			this.dir = dir;
			this.pri = pri;
			this.isAlive = isAlive;
		}

		@Override
		public String toString() {
			return "Shark [num=" + num + ", dir=" + dir + ", pri=" + Arrays.toString(pri) + ", isAlive=" + isAlive
					+ "]";
		}

	}

}
