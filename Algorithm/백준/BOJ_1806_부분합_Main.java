package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806_부분합_Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] input = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0, end = 0, sum = 0, length = Integer.MAX_VALUE;
		
		while(end <= N) {
			if(sum >= S) {
				length = Math.min(end - start, length);
			}
			
			if(sum < S) {
				sum += input[end];
				end++;
			}
			else {
				sum -= input[start];
				start++;
			}
		}

		if(length == Integer.MAX_VALUE)
			System.out.println(0);
		else 
			System.out.println(length);
	}
}
