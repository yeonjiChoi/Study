package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1912_연속합_Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N  = Integer.parseInt(br.readLine());
		int[] inputs = new int[N];
		int[] dp = new int[N];
		int maxSum = Integer.MIN_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = inputs[0];
		maxSum = inputs[0];
		for(int i = 1; i < N; i++) {
			dp[i] = Math.max(dp[i - 1] + inputs[i], inputs[i]);
			maxSum = Math.max(dp[i], maxSum);
		}
		
		System.out.println(maxSum);
	}

}
