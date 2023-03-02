package BOJ;

import java.util.Scanner;

public class BOJ_2609_최대공약수와최소공배수_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		int max = Math.max(A, B);
		int min = Math.min(A, B);
		
		for(int i = min; i >= 1; i--) {
			if(A % i == 0 && B % i == 0) {
				System.out.println(i);
				break;
			}
		}
		
		for(int i = max; i <= A * B; i++) {
			if(i % A == 0 && i % B == 0) {
				System.out.println(i);
				break;
			}
		}
	
	
	}
}
