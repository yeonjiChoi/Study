package BOJ;

import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;

public class BOJ_5430_AC_Main {
	
	static int n; // 배열의 크기
	static Deque<Integer> deque; // 덱 사용
	static String p; // 수행할 함수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		outer: for(int t = 1; t <= T; t++) {
			
			p = br.readLine();
			n = Integer.parseInt(br.readLine());
			deque = new LinkedList<>();
			
			String x = br.readLine(); // 입력되는 함수 [1,2,3,4]
			x = x.substring(1, x.length() - 1); // 입력 값 대괄호 [] 제거
			
			StringTokenizer st = new StringTokenizer(x, ","); // ,를 기준으로 자름
			for(int i = 0; i < n; i++) 
				deque.offer(Integer.parseInt(st.nextToken())); // 입력 숫자들 덱에 삽입
			
			boolean isReversed = false; // 뒤집어진 상태면 true, 아니면 false
			
			for(int i = 0; i < p.length(); i++) {
				char ch = p.charAt(i);
				
				// R 함수 수행 -> isReversed를 반대로 변경(true<->false)
				if(ch == 'R') 
					isReversed = isReversed ? false : true; 
				
				// D 함수 수행
				else {
					// 덱이 비어있을 때 D함수를 사용하면 error 출력
					if(deque.isEmpty()) {
						System.out.println("error");
						continue outer; 
					}
					// 뒤집어진 상태 -> 맨 뒤 숫자 제거
					if(isReversed) deque.pollLast();
					// 뒤집어지지 않은 상태 -> 맨 앞 숫자 제거
					else deque.pollFirst();
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			if(isReversed) {
				while(!deque.isEmpty()) 
					sb.append(deque.pollLast() + ",");
			}
			else {
				while(!deque.isEmpty()) 
					sb.append(deque.pollFirst() + ",");
			}
			
			// 1,2,3,과 같이 sb가 0보다 크면 맨 뒤에 출력된 ,를 제거해줘야 함
			if(sb.length() > 0) 
				sb.delete(sb.length() - 1, sb.length());
			
			System.out.println("[" + sb.toString() + "]");
		}
	}
}
