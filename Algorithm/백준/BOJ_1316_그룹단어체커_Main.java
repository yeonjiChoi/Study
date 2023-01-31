package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BOJ_1316_그룹단어체커_Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			
			if(check(input)) {
				result++;
			}
		}
		System.out.println(result);
		
	}

	private static boolean check(String str) {
		Stack<Character> stack = new Stack<>();
		HashSet<Character> set = new HashSet<>();
		
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			if(!stack.isEmpty()) {
				if(stack.peek() == ch) {
					continue;
				}
				else {
					if(set.contains(ch))
						return false;
					else {
						stack.add(ch);
						set.add(ch);
					}
				}
			}
			else {
				stack.add(ch);
				set.add(ch);
			}
		}
		return true;
	}

}
