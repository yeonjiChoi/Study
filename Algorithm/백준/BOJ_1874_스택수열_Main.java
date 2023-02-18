package BOJ;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_1874_스택수열_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int N = sc.nextInt();
		Stack<Integer> stack = new Stack<>();
		
		int n = 0;
		while(N-- > 0) {
			
			int value = sc.nextInt();
			
			if(value > n) {
				for(int i = n + 1; i <= value; i++) {
					stack.push(i);
					sb.append("+\n");
				}
				n = value;
			}
			
			else if(stack.peek() != value) {
				System.out.println("NO");
				return;
			}
			
			stack.pop();
			sb.append("-\n");
		}
		
		System.out.println(sb);
	}
}
