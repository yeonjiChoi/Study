package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14728_벼락치기_Main {
	
	static int N, T, dp[][], answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
	
		dp = new int[N + 1][T + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= T; j++) {
                dp[i][j] = dp[i-1][j];
                if ((j - k) >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - k] + s);
                }
            }
        }

        System.out.print(dp[N][T]);
	}
}
