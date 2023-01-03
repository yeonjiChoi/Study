package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_6198_옥상정원꾸미기_Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();

		int answer = 0;
		for(int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			
			
			while(!stack.isEmpty() && stack.peek() <= height) {
				stack.pop();
			}
			
			stack.push(height);
			
			answer += stack.size() - 1;
			
		}
		
		System.out.println(answer);
	}
}
