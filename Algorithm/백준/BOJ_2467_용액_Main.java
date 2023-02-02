package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467_용액_Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) 
			input[i] = Integer.parseInt(st.nextToken());
		
		
		int left = 0, right = N - 1;
		int ansLeft = 0, ansRight = 0;
		int min = Integer.MAX_VALUE;
		
		while(left < right) {
			int sum = input[left] + input[right];
			
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				ansLeft = input[left];
				ansRight = input[right];
			}
			
			if(sum > 0) 
				right--;
			else
				left++;
		}
		
		System.out.println(ansLeft + " " + ansRight);
		
	}
}
