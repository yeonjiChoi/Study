package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17135_캐슬디펜스_Main {

	static int N, M, D, copyMap[][], originMap[][], ㅁrchers[], originTotal, copyTotal, killCnt, result = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 행의 수
		M = Integer.parseInt(st.nextToken()); // 열의 수
		D = Integer.parseInt(st.nextToken()); // 공격 제한 거리 

		originMap = new int[N][M]; // 맵 배열(원본)
		copyMap = new int[N][M]; // 맵 배열(복사)

		ㅁrchers = new int[3]; // 궁수 3명의 위치(열)

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
				// 전체 적의 수 카운트
				if(originMap[i][j] == 1) originTotal++;
			}
		}

		combination(0, 0); // nC3 : 3명의 궁수가 위치할 수 있는 자리의 경우의 수 
		
		System.out.println(result);

	}

	private static void combination(int cnt, int start) {

		if(cnt == 3) {
			// 생성된 조합 확인 코드
			// System.out.println(Arrays.toString(pos));
			
			// 조합이 나올 떄 마다 맵 위의 적의 수, 죽인 적의 수, 맵 배열을 원본으로 갱신해주어야 함
			copyTotal = originTotal; 
			killCnt = 0; 
			copy(); // 맵 복사
			
			// 맵 위의 적의 수가 0이 되면 종료
			while(copyTotal != 0) {
				attack(); // 공격
				move(); // 공격받은 적 제거 및 한칸씩 이동

				result = Math.max(result, killCnt);
			}
			return;
		}

		for(int i = start; i < M; i++) {
			ㅁrchers[cnt] = i;
			combination(cnt + 1, i + 1);
		}

	}

	// 맵 복사
	private static void copy() {
		copyMap = new int[N][M];

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copyMap[i][j] = originMap[i][j];
			}
		}

	}

	private static void move() {
		
		// 공격받은 적을 제거, map[i][j]이 2 이상이면 공격을 받음 
		for(int i = N - 1; i >= 0; i--) {
			for(int j = 0; j < M; j++) {
				
				if(copyMap[i][j] > 1) {
					copyMap[i][j] = 0; // 맵 위의 적 제거
					copyTotal--; // 맵 위의 적의 수 감소
					killCnt++; // 죽인 적의 수 증가
				}
				
			}
		}

		// 맵 위의 적의 위치를 한 칸씩 이동
		for(int i = N - 1; i >= 0; i--) {
			for(int j = 0; j < M; j++) {

				if(copyMap[i][j] == 1) {
					// 이동 시 맵 밖을 벗어나면 맵 위의 적의 수 감소
					if(i + 1 == N) 
						copyTotal--;
					// 이동 시 맵 위치를 벗어나지 않으면 한 칸 아래로 이동
					else 
						copyMap[i + 1][j] = 1;
					// 기존 적의 자리는 0으로 변경
					copyMap[i][j] = 0;
				}
			}
		}
	}

	private static void attack() {

		// 3명의 궁수 
		for(int i = 0; i < 3; i++) {
			int p = ㅁrchers[i]; // 궁수의 열 위치

			// 가장 가까운 자리의 적의 위치(minR, minC) 및 그 거리
			int minC = 0, minR = 0, minDist = Integer.MAX_VALUE;
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {

					// 아무도 없으면 무시
					if(copyMap[r][c] == 0) continue;

					int dist = calcDist(r, c, p);
					// 공격 거리를 넘으면 무시
					if(dist > D) continue;

					// 적이 최소 거리라면 갱신
					if(dist < minDist) {
						minDist = dist;
						minR = r; minC = c;
					}
					// 거리가 같으면 더 왼쪽에 있는 적을 죽임
					else if(dist == minDist) {
						if(c < minC) {
							minDist = dist;
							minR = r; minC = c;
						}
					}
				}
			}
			// 죽일 적의 위치를 +1
			if(minDist != Integer.MAX_VALUE) 
				copyMap[minR][minC]++;
			
		}


	}
	// 거리 계산
	private static int calcDist(int r1, int c1, int c2) {
		return Math.abs(r1 - N) + Math.abs(c1 - c2);
	}

	// 디버그용 함수
	private static void show() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				System.out.print(copyMap[r][c] + " ");
			}
			System.out.println();
		}
		System.out.println("=====================");
		
	}
}
