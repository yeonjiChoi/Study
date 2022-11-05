package CJENM;

import java.util.*;
// nPr : n개의 수를 입력받아 순열 생성

/* 
	입력 예제
	5 3
	1 2 3 4 5

*/
public class 순열 {
	
	static int N, R;
	static int[] input, output;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		
		input = new int[N];
		output = new int[R];
		isSelected = new boolean[N];
		
		for(int i = 0; i < N; i++) 
			input[i] = sc.nextInt();
		
		permutation(0);
	}

	// cnt : 직전까지 뽑은 수의 개수, 현재 자리
	public static void permutation(int cnt) {
		
		// 종료 조건(기저 조건)
		if(cnt == R) {
			System.out.println(Arrays.toString(output));
			return;
		}
		
		// 입력받은 모든 수를 현재  자리에 넣어보기
		for(int i = 0; i < N; i++) {
			// 기존 자리(이미 앞에 넣은) 수들과 중복되는지 체크
			// 이미 선택된 수는 사용할 수 없음
			if(isSelected[i]) continue;
			
			// 현재 자리(i)의 숫자(input[i])를 선택
			output[cnt] = input[i];
			// i번째 숫자가 사용되었으므로 true로 변경
			isSelected[i] = true;
			
			// 다음 자리 수 뽑으러 이동
			permutation(cnt + 1);
			// 백트래킹에서 원상 복구
			isSelected[i] = false;
		}
	}
}
