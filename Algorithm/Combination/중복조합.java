package CJENM;

import java.util.Arrays;
import java.util.Scanner;

/* 
입력 예제
5 3
1 2 3 4 5
*/
public class 중복조합 {

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
	
		combination(0, 0);
	}

	private static void combination(int cnt, int start) {
		
		if(cnt == R) {
			System.out.println(Arrays.toString(output));
			return;
		}
		
		for(int i = start; i < N; i++) {
			output[cnt] = input[i];
			// 다음 뽑을 수는 나보다 같거나 큰 수부터 시작
			combination(cnt + 1, i); 
		}
	}

}
