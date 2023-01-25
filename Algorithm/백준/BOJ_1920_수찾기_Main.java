package BOJ;

import java.util.HashSet;
import java.util.Scanner;

public class BOJ_1920_수찾기_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		HashSet<Integer> set = new HashSet<>();
		int N = sc.nextInt();
		for(int i = 0; i < N; i++)
			set.add(sc.nextInt());
		
		int M = sc.nextInt();
		for(int i = 0; i < M; i++) 
			System.out.println(set.contains(sc.nextInt()) ? 1 : 0);
		
	}
}
