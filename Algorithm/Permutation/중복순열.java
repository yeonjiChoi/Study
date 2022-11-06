package CJENM;

import java.util.Arrays;
import java.util.Scanner;

public class 중복순열 {

	static int N, R;
	static int[] input, output;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
	
		input = new int[N];
		output = new int[R];

		for(int i = 0; i < N; i++) 
			input[i] = sc.nextInt();
		
		permutation(0);
	}

	private static void permutation(int cnt) {
		if(cnt == R) {
			System.out.println(Arrays.toString(output));
			return;
		}
		
		for(int i = 0; i < N; i++) {
			output[cnt] = input[i];
			permutation(cnt + 1);
		}
		
	}

}
