package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2660_회장뽑기_Main {

	static int N, level[], min = Integer.MAX_VALUE;
	static List[] list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		level = new int[N + 1]; // 회장 점수 배열
		list = new ArrayList[N + 1]; // 인접리스트 배열, 친구 관계
		
		// 인접리스트 배열 초기화
		for(int i = 1; i <= N; i++) list[i] = new ArrayList<Integer>();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
		
			// 입력 종료
			if(A == -1 && B == -1) break;
			
			// A <-> B : 양방향 관계
			list[A].add(B);
			list[B].add(A);
		}

		// 각 회원에 대해 회장 점수 계산
		for(int i = 1; i <= N; i++) bfs(i);

		
		int cnt = 0; // 후보 수 
		// 후보 수 카운트
		for(int i = 1; i <= N; i++) 
			if(level[i] == min) cnt++;
		
		// 가장 작은 회장 점수 + 후보 수 출력
		System.out.println(min + " " + cnt);
		
		// 후보 오름차순 출력
		for(int i = 1; i <= N; i++) {
			if(level[i] == min)
				System.out.print(i + " ");
		}
	}

	private static void bfs(int start) {
		// 방문 체크 배열
		boolean[] visited = new boolean[N + 1];
		Queue<int[]> queue = new LinkedList<>();
		int cnt = 1;
		
		// {회원 번호, 회장 점수}
		queue.offer(new int[] {start, 1});
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			// cur[0]번의 회원과 친구인 회원 탐색
			for(int i = 0; i < list[cur[0]].size(); i++) {
				int next = (int) list[cur[0]].get(i);
				
				// 이미 방문체크한 회원은 무시
				if(visited[next]) continue;

				queue.offer(new int[] {next, cur[1] + 1});
				visited[next] = true;
				cnt++;
				
				// 모든 회원을 다 방문하면 회장 점수 업데이트
				if(cnt == N) {
					level[start] = cur[1];
					// 가장 낮은 회장 점수 갱신
					min = Math.min(min, cur[1]);
					return;
				}
			}
		}
	}
}
