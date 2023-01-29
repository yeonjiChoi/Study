package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2164_카드_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Queue<Integer> queue = new LinkedList<>();
		
		for(int n = 1; n <= N; n++) 
			queue.offer(n);
		
		while(queue.size() != 1) {
			
			if(queue.size() != 1) queue.poll();
			if(queue.size() != 1) queue.offer(queue.poll());
			
		}
		
		System.out.println(queue.poll());
	}
}
