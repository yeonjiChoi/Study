package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1043_거짓말_Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken()); // 사람의 수
		int M = Integer.parseInt(st.nextToken()); // 파티의 수
		
		st = new StringTokenizer(br.readLine()); 
		int T = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수
		if(T == 0) {
			System.out.println(M);
			return;
		}
		
		Queue<Integer> queue = new LinkedList<>(); // BFS 탐색을 위한 큐
		
		boolean[] trueFlags = new boolean[N + 1]; // 진실을 아는 사람에 대한 배열
		for(int i = 0; i < T; i++) {
			int n = Integer.parseInt(st.nextToken());
			trueFlags[n] = true; // 진실을 알 경우 true
			queue.offer(n); // 큐에 삽입
		}
		
		int[][] parties = new int[M][N]; // 파티 배열, M개의 파티에 최대 N명의 사람 참가
		boolean[][] map = new boolean[N + 1][N + 1]; // 파티에 참여한 사람 간의 관계 체크
		
		for(int i = 0; i < M ; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int P = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < P; j++) 
				parties[i][j] = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < P; j++) {
				for(int k = 0; k < P; k++) {
					if(j == k) continue;
					map[parties[i][j]][parties[i][k]] = true;
				}
			}
		}
		
		// BFS : 진실을 아는 사람 체크
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int i = 0; i < map[cur].length; i++) {
				if(map[cur][i]) {
					if(trueFlags[i]) 
						continue;
					else { 
						trueFlags[i] = true;
						queue.offer(i);
					}
				}
			}
		}
		
		int answer = M;
		for(int i = 0; i < M; i++) {
			int[] party = parties[i];
			
			for(int j = 0; j < party.length; j++) {
				if(trueFlags[party[j]]) {
					// 진실을 아는 사람이 파티에 있을 경우 거짓말 불가 -> 파티 개수 감소
					answer--;
					break;
				}
			}
		}
		
		
		System.out.println(answer);
		
	}
}
