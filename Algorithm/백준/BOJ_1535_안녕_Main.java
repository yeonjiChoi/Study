package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1535_안녕_Main {
	
	static int LIMIT = 100, N, map[][], answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[2][N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) 
			map[0][i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) 
			map[1][i] = Integer.parseInt(st.nextToken());
	
		dfs(0, LIMIT, 0);
		System.out.println(answer);
	}

	// i : 인사한 사람, lim : 남은 체력, val : 얻은 기쁨
	private static void dfs(int i, int limit, int val) {
		
		if(i == N) {
			answer = Math.max(answer, val);
			return;
		}
		
		// 남은 체력이 i번째 사람과 인사할 때 소모되는 체력보다 많으면
		if(limit > map[0][i]) {
			dfs(i + 1, limit - map[0][i], val + map[1][i]);
		}
		
		dfs(i + 1, limit, val);
	}
}
