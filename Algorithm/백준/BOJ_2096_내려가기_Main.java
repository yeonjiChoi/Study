package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2096_내려가기_Main {

	static int N, map[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 3; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int min = getMin();
		int max = getMax();
		System.out.println(max + " " + min);
	}

	private static int getMax() {
		int dp[][] = new int[N][3];
		dp[0][0] = map[0][0];
		dp[0][1] = map[0][1];
		dp[0][2] = map[0][2];
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < 3; j++) {
				int a= 0, b = 0, c = 0;
				switch(j) {
				case 0:
					a = dp[i - 1][j] + map[i][j];
					b = dp[i - 1][j + 1] + map[i][j];
					dp[i][j] = Math.max(a, b);
					break;
				
				case 1:
					a = dp[i - 1][j - 1] + map[i][j];
					b = dp[i - 1][j] + map[i][j];
					c = dp[i - 1][j + 1] + map[i][j];
					dp[i][j] = Math.max(Math.max(a, b), c);
					break;
				
				case 2:
					a = dp[i - 1][j - 1] + map[i][j];
					b = dp[i - 1][j] + map[i][j];
					dp[i][j] = Math.max(a, b);
					break;
				
				}
			}
		}
		
		int result = Math.max(dp[N - 1][0], Math.max(dp[N - 1][1], dp[N - 1][2]));
		
		return result;
	}

	private static int getMin() {
		int dp[][] = new int[N][3];
		dp[0][0] = map[0][0];
		dp[0][1] = map[0][1];
		dp[0][2] = map[0][2];
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < 3; j++) {
				int a= 0, b = 0, c = 0;
				switch(j) {
				case 0:
					a = dp[i - 1][j] + map[i][j];
					b = dp[i - 1][j + 1] + map[i][j];
					dp[i][j] = Math.min(a, b);
					break;
				
				case 1:
					a = dp[i - 1][j - 1] + map[i][j];
					b = dp[i - 1][j] + map[i][j];
					c = dp[i - 1][j + 1] + map[i][j];
					dp[i][j] = Math.min(Math.min(a, b), c);
					break;
				
				case 2:
					a = dp[i - 1][j - 1] + map[i][j];
					b = dp[i - 1][j] + map[i][j];
					dp[i][j] = Math.min(a, b);
					break;
				
				}
			}
		}
		
		int result = Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2]));
		
		return result;
	}

}
