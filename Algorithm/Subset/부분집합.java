package CJENM;

import java.util.Scanner;

public class 부분집합 {

	static int N; // 총 원소의 개수
	static int input[]; // 입력된 데이터를 받는 배열
	static boolean isSelected[]; // 각각의 원소들이 부분집합에 포함되었는지 여부
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		input = new int[N];
		isSelected = new boolean[N];
		
		for(int i = 0; i < N; i++) 
			input[i] = sc.nextInt();
		
		subset(0);
	}

	private static void subset(int cnt) {
		// 모든 N개의 원소를 고려할 경우(마지막 원소까지 부분집합에 고려되었을 경우)
		if(cnt == N) {
			for(int i = 0; i < N; i++)
				// isSelected[i] = true인 원소들 출력
				System.out.print((isSelected[i] ? input[i] : "X") + " ");
			System.out.println();
			return;
		}
		
		// 현재 원소를 선택
		isSelected[cnt] = true;
		// 다음 원소로 넘어감
		subset(cnt + 1);
		// 현재 원소를 비선택
		isSelected[cnt] = false;
		// 다음 원소로 넘어감
		subset(cnt + 1);
		
	}
}
