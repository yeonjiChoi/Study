package BOJ;

import java.util.Scanner;

public class BOJ_5582_공통부분문자열_Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String str1 = sc.next();
		String str2 = sc.next();
		
		int[][] dp = new int[str1.length()][str2.length()];
		
		int result = 0;
		for(int i = 0; i < str1.length(); i++) {
			for(int j = 0; j < str2.length(); j++) {
				
				if(str1.charAt(i) == str2.charAt(j)) {
					if(i != 0 && j != 0)
						dp[i][j] = dp[i - 1][j - 1] + 1;
					else 
						dp[i][j] += 1;
				}
				
				result = Math.max(result, dp[i][j]);
			}
		}
		
		System.out.println(result);
		
	}

}
