package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_10845_큐_Main {
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int back = -1;
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			switch(command) {
			
			case "push" :
				int num = Integer.parseInt(st.nextToken());
				queue.offer(num);
				back = num;
				break;
			
			case "pop" :
				System.out.println(queue.isEmpty() ? -1 : queue.poll());
				break;
			
			case "size" :
				System.out.println(queue.size());
				break;
			
			case "empty" :
				System.out.println(queue.isEmpty() ? 1 : 0);
				break;
			
			case "front" :
				System.out.println(queue.isEmpty() ? -1 : queue.peek());
				break;
			
			case "back" :
				System.out.println(queue.isEmpty() ? -1 : back);
				break;
			
			}
		}
		
	}
}
