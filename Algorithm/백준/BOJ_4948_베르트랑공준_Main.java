package BOJ;

import java.util.Scanner;

public class BOJ_4948_베르트랑공준_Main {
	
	public static void main(String[] args) {
		
		int N = 123456 * 2 + 1;
		boolean[] check = new boolean[N];
		for(int i = 2; i < N; i++) {
			int j = 2;
			while(i * j < N) {
				check[i * j] = true;
				j++;
			}
		}
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int n = sc.nextInt();
			
			if(n == 0) break;
			int result = 0;
			for(int i = n + 1; i <= (2 * n); i++) {
				if(!check[i]) result++;
			}
			
			sb.append(result + "\n");
		}
		System.out.println(sb);
	
	}
}
