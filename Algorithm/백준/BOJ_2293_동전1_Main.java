package BOJ;

import java.util.Scanner;

public class BOJ_2293_동전1_Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] coin = new int[N];
		long[] dp = new long[K + 1];
		dp[0] = 1;

		for(int i = 0; i < N; i++) 
			coin[i] = sc.nextInt();
		
		for(int i = 0; i < N; i++) {
			for(int j = coin[i]; j <= K; j++) {
				dp[j] = dp[j] + dp[j - coin[i]];
			}
		}
		
		System.out.println(dp[K]);
		
		
	}
}
