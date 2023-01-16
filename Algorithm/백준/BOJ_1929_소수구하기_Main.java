package BOJ;

import java.util.Scanner;

public class BOJ_1929_소수구하기_Main {
	
	public static void main(String[] args) {
		
		// 소수 체크 배열 -> 소수이면 false, 소수가 아니면 true
		boolean[] check = new boolean[1000001];
		check[1] = true; // 1은 소수가 아니기 때문에 1로 변경
		
		for(int i = 2; i <= 1000000; i++) {
			int j = 2;
			// 소수가 아닌 숫자들을 true로 변경
			while(i * j <= 1000000) {
				check[i * j] = true;
				j++;
			}
		}
		
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		// M과 N 사이의 숫자 중 소수 출력
		for(int i = M; i <= N; i++) {
			if(!check[i]) System.out.println(i);
		}
	}
}
