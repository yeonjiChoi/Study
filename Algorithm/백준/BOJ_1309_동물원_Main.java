package BOJ;

import java.util.Scanner;

public class BOJ_1309_동물원_Main {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		long[][] dp = new long[N + 1][3];
		
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		for(int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] * 2) % 9901;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 9901;
			
		}
		long answer = dp[N][0] * 2 + dp[N][1] * 3;
		System.out.println(answer % 9901);
	}

}
