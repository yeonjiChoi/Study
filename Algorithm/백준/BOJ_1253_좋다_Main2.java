package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 음수값이 존재한다!
public class BOJ_1253_좋다_Main2 {
	
	static int N, input[], answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) 
			input[i] = Integer.parseInt(st.nextToken());
		
		// 입력 배열 정렬(오름차순)
		Arrays.sort(input);
		
		for(int i = 0; i < N; i++) {
			solution(i);
		}
		
		System.out.println(answer);
	}

	private static void solution(int index) {
		int start = 0; // 시작 포인터
		int end = N - 1; // 끝 포인터
		int target = input[index]; // 목표 숫자
		
		// start가 end보다 작을 때까지만 반복
		while(start < end) { 
			
			// 시작 포인터가 index와 같다면 1 증가
			if(start == index) start++;
			
			// 끝 포인터가 index와 같다면 1 감소
			else if(end == index) end--;
			
			// start와 end가 index와 같지 않을 경우
			else {
				// target이 두 포인터가 가리키는 숫자의 합보다 클 경우, start 인덱스 증가
				if(target > input[start] + input[end]) 
					start++;
				
				// target이 두 포인터가 가리키는 숫자의 합보다 작을 경우, end 인덱스 감소
				else if(target < input[start] + input[end]) 
					end--;
				
				// target이 두 포인터가 가리키는 숫자의 합과 같을 경우
				else {
					// 좋다(Good) 개수 증가 후 리턴
					answer++;
					return;
				}
			}
		}
		
	}
}
