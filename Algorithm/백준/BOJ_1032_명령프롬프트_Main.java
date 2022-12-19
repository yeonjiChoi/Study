package BOJ;

import java.util.Scanner;

public class BOJ_1032_명령프롬프트_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		String[] inputs = new String[N];
		
		for(int i = 0; i < N; i++) {
			inputs[i] = sc.next();
		}
		
		String answer = "";
		for(int i = 0; i < inputs[0].length(); i++) {
			
			char ch = inputs[0].charAt(i);
			boolean flag = true;
			
			for(int j = 0; j < N; j++) {
				
				if(inputs[j].charAt(i) == ch) continue;
				
				else {
					flag = false;
					break;
				} 
			}
			
			if(flag) answer += ch;
			else answer += "?";
		}
		System.out.println(answer);
	}
}
