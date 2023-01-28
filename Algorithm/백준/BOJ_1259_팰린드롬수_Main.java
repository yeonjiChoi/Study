package BOJ;

import java.util.Scanner;

public class BOJ_1259_팰린드롬수_Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String input = sc.nextLine();
			
			if(input.equals("0")) break;
			
			int start = 0, end = input.length() - 1;
			boolean flag = true;
			
			while(start < end) {
				if(input.charAt(start) == input.charAt(end)) {
					start++;
					end--;
				}
				else {
					flag = false;
					break;
				}
			}
			System.out.println(flag ? "yes" : "no");
		}
		
	}
}
