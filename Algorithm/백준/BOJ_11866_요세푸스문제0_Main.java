package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_11866_요세푸스문제0_Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++)
			queue.offer(i);
		
		StringBuilder sb = new StringBuilder();
		
		int k = 1;
		while(!queue.isEmpty()) {
			
			int n = queue.poll();
			
			if(k == K) {
				sb.append(n + ", ");
				k = 1;
			}
			else {
				queue.offer(n);
				k++;
			}
		}
		
		sb = sb.delete(sb.length() - 2, sb.length());
		System.out.println("<" + sb.toString() + ">");
	}

}
