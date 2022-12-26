package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_10866_덱_Main {


	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new LinkedList<>();

		for(int i = 0; i < N; i++) {

			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int value = 0;

			switch(command) {
			case "push_front" :
				value = Integer.parseInt(st.nextToken());
				deque.addFirst(value);
				break;
			case "push_back" :
				value = Integer.parseInt(st.nextToken());
				deque.addLast(value);
				break;
			case "pop_front" :
				System.out.println(deque.isEmpty() ? -1 : deque.pollFirst());
				break;
			case "pop_back" :
				System.out.println(deque.isEmpty() ? -1 : deque.pollLast());
				break;
			case "size" :
				System.out.println(deque.size());
				break;
			case "empty" :
				System.out.println(deque.isEmpty() ? 1 : 0);
				break;
			case "front" :
				System.out.println(deque.isEmpty() ? -1 : deque.peekFirst());
				break;
			case "back" :
				System.out.println(deque.isEmpty() ? -1 : deque.peekLast());
				break;
			}
		}

	}
}
