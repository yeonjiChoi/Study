package BOJ;

import java.util.Scanner;

public class BOJ_1107_리모컨_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		boolean[] broken = new boolean[10];
		for(int i = 0; i < M; i++) 
			broken[sc.nextInt()] = true;
		
		int result = Math.abs(N - 100);
		
		for(int i = 0; i <= 999999; i++) {
			String str = String.valueOf(i);
			int len = str.length();
			
			boolean isBreak = false;
			for(int j = 0; j < len; j++) {
				if(broken[str.charAt(j) - '0']) {
					isBreak = true;
					break;
				}
			}
			
			if(!isBreak) {
				int min = Math.abs(N - i) + len;
				result = Math.min(min, result);
			}
		}
		
		System.out.println(result);
		
	}
}
