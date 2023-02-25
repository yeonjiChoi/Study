package BOJ;

import java.util.Scanner;

public class BOJ_11050_이항계수1_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int result = 1;
		for(int i = 0; i < K; i++) {
			result *= N;
			N--;
		}
		
		for(int i = 1; i <= K; i++) {
			result /= i;
		}
		
		System.out.println(result);
	}
}
