package BOJ;

import java.util.Scanner;

public class BOJ_1436_영화감독숌_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int number = 1;
		String str = null;
		while(true) {
			str = number + "";
			if(str.contains("666"))
				N--;
			if(N == 0) break;
			number++;
		}
		System.out.println(str);
		
	}
}
