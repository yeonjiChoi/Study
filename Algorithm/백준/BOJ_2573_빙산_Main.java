import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산_Main {

	static int N, M, map[][][], answer, idx;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				map[i][j][1] = map[i][j][0];
			}
		}
		
		while(true) {

			// 빙산 녹음
			melt(); 
			// 1년 추가
			answer++;
			
			// 빙산 덩어리 개수
			int count = count();
			
			// 빙산 덩어리 개수가 0이다 = 빙산이 다 녹았으면서 덩이리가 없음 = 0 출력
			if(count == 0) {
				answer = 0;
				break;
			}
			// 빙산 덩어리가 2개 이상이면 종료
			if(count >= 2) break;
			
		}
		System.out.println(answer);
		
	}

	// 디버그용 출력 함수
	private static void show() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(map[i][j][idx] + " ");
			}
			System.out.println();
		}
		System.out.println("--------------------------------------");
	}
	

	// 덩어리 개수 세는 함수
	private static int count() {
		visited = new boolean[N][M];
		
		int cnt = 0; // 덩어리 개수
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				// 빙산이 없을 경우 무시
				if(map[i][j][idx] == 0) continue;
				// 이미 방문한 곳이면 무시
				if(visited[i][j]) continue;
				
				// 덩어리 개수 증가
				cnt++;
				// 한 빙산과 연결된 다른 덩어리 방문 체크
				bfs(i, j);
			}
		}
		return cnt;
	}

	// 덩어리 방문 체크
	private static void bfs(int r, int c) {

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(map[nr][nc][idx] == 0) continue;
				if(visited[nr][nc]) continue;

				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		
	}


	// 빙하 녹음
	private static void melt() {
		int nIdx = (idx + 1) % 2;
		
		clear(nIdx);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j][idx] == 0) continue;
				
				int melt = 0;
				int nr = 0, nc = 0;
				for(int d = 0; d < 4; d++) {
					nr = i + dr[d];
					nc = j + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
					
					if(map[nr][nc][idx] == 0) 
						melt++;
				}
				
				map[i][j][nIdx] = map[i][j][idx] - melt < 0 ? 0 : map[i][j][idx] - melt;

			}
		}
		
		idx = nIdx;
	}

	// 빙산 배열 초기화
	private static void clear(int idx) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j][idx] = 0;
			}
		}
		
	}

}
