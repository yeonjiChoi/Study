package CJENM;

import java.util.Arrays;
import java.util.Scanner;

// nCr
// n개의 숫자 중 r개를 선택
/* 
	입력 예제
	5 3
	1 2 3 4 5
*/
public class 조합 {

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

	// cnt : 현재 뽑는 자리의 위치
	private static void combination(int cnt, int start) {
		
		// 기저 조건
		if(cnt == R) {
			System.out.println(Arrays.toString(output));
			return;
		}
		
		for(int i = start; i < N; i++) {
			output[cnt] = input[i];
			// 다음 자리는 현재 뽑은 수의 다음 수부터 뽑도록 i + 1
			combination(cnt + 1, i + 1);
		}
	}
}
