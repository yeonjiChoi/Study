package BOJ;

import java.util.Scanner;

public class BOJ_9465_스티커_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			
			int N = sc.nextInt();
			int[][] input = new int[2][N + 1];
			int[][] dp = new int[2][N + 1];
			
			for(int i = 0; i < 2; i++) {
				for(int j = 1; j <= N; j++) {
					input[i][j] = sc.nextInt();
				}
			}
			
			dp[0][1] = input[0][1];
			dp[1][1] = input[1][1];
			
			for(int i = 2; i <= N; i++) {
				dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + input[0][i];
				dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + input[1][i];
			}

			System.out.println(Math.max(dp[0][N], dp[1][N]));
		}
	}

}
