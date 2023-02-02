package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 검은색 블록 : -1
 * 무지개 블록 : 0
 * 인접한 칸 : |r1-r2| + |c1-c2| = 1
 * 블록 그룹 : 일반 블록 최소 1개 + 모두 같은 색의 일반 블록 + 무지개 블록 개수 무관 + 최소 2개 이상으로 구성
 * -> 기준 블록 : 일반 블록 중에서 행 + 열 번호가 가장 작은 블록
 */

public class BOJ_21609_상어중학교_Main {

	static int N, M, map[][], result;
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {-1, 1, 0, 0};
	
	static BlockGroup biggestGroup;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 일반 블록 M가지
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
	
		
		while(true) {
			// 1. 크기가 가장 큰 블록 그룹 찾기
			findBiggestBlockGroup();
			
			// 2. 블록 그룹 제거
			if(biggestGroup == null) break;
			removeBlockGroup();
			
			// 3. 격자에 중력 작동
			gravity();
			
			// 4. 90도 반시계 회전
			rotate();
			
			// 5. 격자에 중력 작동
			gravity();
		}
		
		System.out.println(result);
	}

	private static void show() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------");
	}
	
	private static void rotate() {
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				temp[N - j - 1][i] = map[i][j]; 
			}
		}
		map = temp;
	}

	private static void gravity() {
		
		for(int j = 0; j < N; j++) {
			Stack<Integer> stack = new Stack<>();
			for(int i = 0; i < N; i++) {
				if(map[i][j] != -1) {
					if(map[i][j] != -2) {
						stack.add(map[i][j]);
						map[i][j] = -2;
					}
				}
				else {
					if(stack.isEmpty()) continue;
					int k = i - 1;
					while(!stack.isEmpty()) {
						map[k][j] = stack.pop();
						k--;
					}
				}
			}
			int k = N - 1;
			while(!stack.isEmpty()) {
				map[k][j] = stack.pop();
				k--;
			}
		}
	}

	private static void removeBlockGroup() {
		// 블록 그룹에 포함된 블록의 수^2 점 획득
		result += Math.pow(biggestGroup.blocks.size(), 2);
		
		for(int[] block : biggestGroup.blocks) {
			// 블록이 제거된 빈 공간을 -2로 표시
			map[block[0]][block[1]] = -2;
		}
	}

	private static void findBiggestBlockGroup() {

		boolean[][] isVisited = new boolean[N][N];
		biggestGroup = null;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] <= 0) continue;
				if(isVisited[i][j]) continue;
				
				BlockGroup blockGroup = new BlockGroup(null, 0, new ArrayList<int[]>());
				Queue<int[]> queue = new LinkedList<>();
				boolean[][] visited = new boolean[N][N];
				
				blockGroup.blocks.add(new int[] {i, j});
				blockGroup.stdBlock = new int[] {i, j};
				
				queue.offer(new int[] {i, j});
				visited[i][j] = true;
				isVisited[i][j] = true;

				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					
					for(int d = 0; d < 4; d++) {
						int nr = cur[0] + dr[d];
						int nc = cur[1] + dc[d];
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if(visited[nr][nc]) continue;
						
						// 같은 색의 일반 블록일 경우
						if(map[nr][nc] == map[i][j]) {
							// 기준 블록
							if(blockGroup.stdBlock[0] > nr) {
								blockGroup.stdBlock = new int[] {nr, nc};
							}
							else if(blockGroup.stdBlock[0] == nr) {
								if(blockGroup.stdBlock[1] > nc)
									blockGroup.stdBlock = new int[] {nr, nc};
							}
							
							blockGroup.blocks.add(new int[] {nr, nc});
							queue.offer(new int[] {nr, nc});
							visited[nr][nc] = true;
							isVisited[i][j] = true;
						}
						// 무지개 블록일 경우
						if(map[nr][nc] == 0) {
							blockGroup.blocks.add(new int[] {nr, nc});
							blockGroup.rainbowBlockCnt++;
							
							queue.offer(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					
					}
				}
				// 그룹 블록의 개수가 2보다 작으면 안됨
				if(blockGroup.blocks.size() < 2) continue;

				if(biggestGroup == null) {
					biggestGroup = blockGroup;
				}
				else {
					// 1) 크기가 가장 큰 그룹
					if(biggestGroup.blocks.size() < blockGroup.blocks.size()) 
						biggestGroup = blockGroup;
					else if(biggestGroup.blocks.size() == blockGroup.blocks.size()) {
						// 2) 무지개 블록의 수가 많은 그룹
						if(biggestGroup.rainbowBlockCnt < blockGroup.rainbowBlockCnt) 
							biggestGroup = blockGroup;
						else if(biggestGroup.rainbowBlockCnt == blockGroup.rainbowBlockCnt){
							// 3) 기준 블록의 행이 가장 큰 블록
							if(biggestGroup.stdBlock[0] < blockGroup.stdBlock[0])
								biggestGroup = blockGroup;
							else if(biggestGroup.stdBlock[0] == blockGroup.stdBlock[0]) {
								// 4) 기준 블록의 열이 가장 큰 블록
								if(biggestGroup.stdBlock[1] < blockGroup.stdBlock[1])
									biggestGroup = blockGroup;
							}
						}
					}
				}
			}
		}
	}

	private static class BlockGroup {
		int[] stdBlock;
		int rainbowBlockCnt;
		List<int[]> blocks;
		
		public BlockGroup(int[] stdBlock, int rainbowBlockCnt, List<int[]> blocks) {
			super();
			this.stdBlock = stdBlock;
			this.rainbowBlockCnt = rainbowBlockCnt;
			this.blocks = blocks;
		}
	}
}
