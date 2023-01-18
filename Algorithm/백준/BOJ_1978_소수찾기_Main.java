package BOJ;

import java.util.Scanner;

public class BOJ_1978_소수찾기_Main {
	
	public static void main(String[] args) {
		
		boolean[] decimal = new boolean[1001];
		decimal[1] = true;
		
		for(int i = 2; i <= 1000; i++) {
			int j = 2;
			while(i * j <= 1000) {
				decimal[i * j] = true;
				j++;
			}
		}

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int result = 0;
		
		for(int i = 0; i < N; i++) {
			int n = sc.nextInt();
			
			if(!decimal[n]) result++;
		}
		
		System.out.println(result);
		
	}
}
