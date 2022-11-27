package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소_Main {

	static int answer; // 최대 안전구역 개수
	static int N, M, map[][]; // emptyCnt : 전체 맵에서 빈 공간의 개수
	static List<Point> vList, eList; // 바이러스의 위치들과 빈 공간의 위치들을 저장하는 리스트
	static int[] selected; // 선택한 3개의 빈 공간의 인덱스를 저장
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Point {
		int r, c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		vList = new ArrayList<>();
		eList = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 0) {
					eList.add(new Point(i, j)); // 빈 공간이면 리스트에 저장
				}
				else if(map[i][j] == 2) vList.add(new Point(i, j)); // 바이러스면 리스트에 저장
			}
		}
		
		selected = new int[3]; // 선택한 3개의 빈 공간의 인덱스 번호를 저장
		// 빈 공간들 중 벽을 놓기 위한 3개의 자리를 선택 => 조합(kC3)
		comb(0, 0);
		
		System.out.println(answer);
	}

	// 조합 : 빈 공간들 중에 3개의 벽을 세우기 위한 공간을 선택
	private static void comb(int cnt, int start) {
		
		if(cnt == 3) {
			int[][] cMap = copyMap(); // map을 복사해서 사용하기 위한 cMap
			for(int i = 0; i < 3; i++) { // 선택한 3개의 빈 공간을 벽으로 바꿈
				Point wall = eList.get(selected[i]);
				cMap[wall.r][wall.c] = 1;
			}
			
			int count = bfs(cMap); // bfs 호출, 남은 안전지대 리턴 
			answer = Math.max(count, answer); // 최대 안전지대 개수 갱신
			
			return;
		}
		
		for(int i = start; i < eList.size(); i++) {
			selected[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}


	private static int bfs(int[][] cMap) {
		
		int curCnt = eList.size() - 3; // 3개의 벽을 세우고 난 뒤의 남은 빈 공간의 수
		boolean[][] visited = new boolean[N][M];
		Queue<Point> queue = new LinkedList<>();
		
		// 바이러스 리스트의 값들을 큐에 삽입
		for(int i = 0; i < vList.size(); i++) { 
			int r = vList.get(i).r;
			int c = vList.get(i).c;
			queue.offer(new Point(r, c));
			visited[r][c] = true;
		}

		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue; // 배열 벗어나면 무시
				if(visited[nr][nc]) continue; // 이미 방문한 곳이면 무시
				if(cMap[nr][nc] == 1) continue; // 벽이면 무시
				
				curCnt--; // 안전지대 개수 감소
				cMap[nr][nc] = 2; // 바이러스 확산
				queue.offer(new Point(nr, nc));
				visited[nr][nc] = true;
			}
		}
		return curCnt;
	}

	// 원본 map을 유지하기 위해 map을 복사하는 메소드
	private static int[][] copyMap() {
		int[][] temp = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}
}

