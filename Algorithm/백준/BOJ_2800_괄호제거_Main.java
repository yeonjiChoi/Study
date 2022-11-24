import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class BOJ_2800_괄호제거_Main {

	public static String input; // 수식
	public static int total; // 괄호 쌍의 개수
	public static boolean[] selected; // 부분집합을 위한 배열
	public static PriorityQueue<String> pqueue; // 사전 순 정렬을 위한 우선순위 큐
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine();
		
		// 수식에서 괄호쌍의 개수 구하기
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(') total++;
		}

		// 부분집합 배열 초기화
		selected = new boolean[total];
		
		// 괄호를 제거한 수식을 사전순 정렬하기 위한 우선순위 큐
		pqueue = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		// 부분집합 
		subset(0);
		
		
		while(!pqueue.isEmpty()) {
			String result = pqueue.poll();
			// 기본 수식과 같은 수식은 출력하지 않음
			if(result.equals(input)) continue;
			System.out.println(result);
		}
	}

	private static void subset(int cnt) {
		if(cnt == total) {
			// 수식 제거
			make();
			return;
		}
		selected[cnt] = true;
		subset(cnt + 1);
		
		selected[cnt] = false;
		subset(cnt + 1);
	}

	private static void make() {

		StringBuilder sb = new StringBuilder();
		Stack<Data> stack = new Stack<>();
		
		int pointer = 0; // 부분집합 배열을 가리키는 포인터
		for(int i = 0; i < input.length(); i++) {
			
			char c = input.charAt(i);
			// 괄호가 아닌 문자는 sb에 추가
			if(c != '(' && c != ')') sb.append(c);
			
			// 열린 괄호일 경우
			if(c == '(') {
				// 괄호를 제거하지 않을 경우
				if(selected[pointer]) {
					// sb에 추가 후 스택에 삽입, 예) Data = {'(', true}
					sb.append(c);
					stack.add(new Data(c, selected[pointer]));
				} 
				// 괄호를 제거할 경우
				else {
					// sb에 추가히지 않고 스택에 삽입, 예) Data = {'(', false}
					stack.add(new Data(c, selected[pointer]));
				}
				// 포인터 이동
				pointer++;
			}
			
			// 닫힌 괄호일 경우
			if(c == ')') {
				// 스택에서 열린 괄호를 꺼냄
				Data data = stack.pop();
				// 열린 괄호의 isShow가 true일 경우 닫힌 괄호도 출력
				if(data.isShow) {
					sb.append(c);
				} 
			}
		}
		// 중복된 수식은 큐에 넣지 않음
		if(!pqueue.contains(sb.toString()))
			pqueue.offer(sb.toString());
	}
	
	public static class Data {
		char ch;
		boolean isShow;
		
		public Data(char ch, boolean isShow) {
			super();
			this.ch = ch;
			this.isShow = isShow;
		}
	}
}
