package BOJ;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1764_최소힙_Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			int x = sc.nextInt();
			
			if(x == 0) {
				sb.append(!pq.isEmpty() ? pq.poll() + "\n" : 0 + "\n");
			}
			else {
				pq.offer(x);
			}
		}
		System.out.println(sb.toString());
	}

}
