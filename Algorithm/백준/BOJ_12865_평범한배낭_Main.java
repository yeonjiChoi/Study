package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865_평범한배낭_Main {

	static int N, K, input[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][K + 1];
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		for(int n = 1; n <= N; n++) {
			for(int k = 1; k <= K; k++) {
				dp[n][k] = dp[n - 1][k];
				if(k - W[n] >= 0) {
					dp[n][k] = Math.max(dp[n - 1][k], dp[n - 1][k - W[n]] + V[n]);
					result = Math.max(dp[n][k], result);
				}
			}
		}
		System.out.println(dp[N][K]);
		
	}
}
