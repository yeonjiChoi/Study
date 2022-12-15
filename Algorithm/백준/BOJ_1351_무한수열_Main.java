package BOJ;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ_1351_무한수열_Main {

	static long N, P, Q;
	static Map<Long, Long> map = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextLong();
		P = sc.nextLong();
		Q = sc.nextLong();
	
		Long answer = recur(N);
		System.out.println(answer);
	}

	private static long recur(long num) {

		if(num == 0) return 1;
		if(map.containsKey(num)) return map.get(num);
		
		map.put(num, recur(num / P) + recur(num / Q));
		return map.get(num);
		
	}

}
