package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935_문자열폭발_Main {
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String origin = br.readLine();
		String explosion = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < origin.length(); i++) {
			
			stack.push(origin.charAt(i));
			
			if(stack.size() >= explosion.length()) {
				
				boolean flag = true;
				
				for(int j = 0; j < explosion.length(); j++) {
					char ch = stack.get(stack.size() - explosion.length() + j);
					
					if(ch != explosion.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if(flag) {
					for(int j = 0; j < explosion.length(); j++) {
						stack.pop();
					}
				}	
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for(char ch : stack) 
			answer.append(ch);
		
		System.out.println(answer.length() == 0 ? "FRULA" : answer);
	}
}
