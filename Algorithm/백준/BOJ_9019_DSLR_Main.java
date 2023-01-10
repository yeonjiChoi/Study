package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9019_DSLR_Main {

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		outer: for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			Queue<Data> queue = new LinkedList<>();
			boolean[] visited = new boolean[10000];
			
			queue.offer(new Data(A, ""));
			visited[A] = true;
			
			while(!queue.isEmpty()) {
				Data cur = queue.poll();
				
				if(cur.number == B) {
					System.out.println(cur.result);
					continue outer;
				}
			
				// D 함수 호출
				int next = functionD(cur.number);
				if(!visited[next]) {
					queue.offer(new Data(next, cur.result + "D"));
					visited[next] = true;
				}
				
				next = functionS(cur.number);
				if(!visited[next]) {
					queue.offer(new Data(next, cur.result + "S"));
					visited[next] = true;
				}
				
				next = functionL(cur.number);
				if(!visited[next]) {
					queue.offer(new Data(next, cur.result + "L"));
					visited[next] = true;
				}
				
				next = functionR(cur.number);
				if(!visited[next]) {
					queue.offer(new Data(next, cur.result + "R"));
					visited[next] = true;
				}
			}
			
		}
	}

	public static class Data {
		int number;
		String result;
		
		public Data(int number, String result) {
			super();
			this.number = number;
			this.result = result;
		}
		
	}
	
	public static int functionD(int number) {
		return (number * 2) % 10000;
	}

	public static int functionS(int number) {
		return number == 0 ? 9999 : number - 1;
	}

	public static int functionL(int number) {
		int d1 = number / 1000;
		int d2 = (number / 100) % 10;
		int d3 = (number / 10) % 10;
		int d4 = number % 10;

		return d2 * 1000 + d3 * 100 + d4 * 10 + d1;
	}

	public static int functionR(int number) {
		int d1 = number / 1000;
		int d2 = (number / 100) % 10;
		int d3 = (number / 10) % 10;
		int d4 = number % 10;

		return d4 * 1000 + d1 * 100 + d2 * 10 + d3;
	}
	
}
