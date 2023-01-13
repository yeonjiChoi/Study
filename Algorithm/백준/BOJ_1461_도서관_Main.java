package BOJ;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1461_도서관_Main {
	
	static int N, M;
	static PriorityQueue<Integer> negQueue, posQueue;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		negQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
		posQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
		
		int max = 0;
		for(int i = 0; i < N; i++) {
			int n = sc.nextInt();
		
			if(n < 0) negQueue.offer(Math.abs(n));
			else posQueue.offer(n);
		
			max = Math.max(max, Math.abs(n));
		}
		
		int result = 0;
		while(!negQueue.isEmpty()) {
			result += Math.abs(negQueue.peek()) * 2;
			
			for(int i = 0; i < M; i++) {
				if(negQueue.isEmpty()) break;
				negQueue.poll();
				
			}
		}
	
		while(!posQueue.isEmpty()) {
			result += posQueue.peek() * 2;
			
			for(int i = 0; i < M; i++) {
				if(posQueue.isEmpty()) break;
				posQueue.poll();
			}
		}
		
		result -= max;
		System.out.println(result);
	}
}
