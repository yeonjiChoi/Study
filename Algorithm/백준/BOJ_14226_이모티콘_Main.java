package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_14226_이모티콘_Main {
	
	static class Data {
		int value, copy, level;

		public Data(int value, int copy, int level) {
			super();
			this.value = value;
			this.copy = copy;
			this.level = level;
		}
		
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int S = sc.nextInt();
		
		boolean[][] visited = new boolean[2001][2001];
		Queue<Data> queue = new LinkedList<>();
		
		queue.offer(new Data(1, 0, 0));
		
		while(!queue.isEmpty()) {
			Data cur = queue.poll();
			
			if(cur.value == S) {
				System.out.println(cur.level);
				return;
			}
			
			// 복사
			if(cur.value > 0 && cur.value < 2000) {
				if(!visited[cur.value][cur.value]) {
					visited[cur.value][cur.value] = true;
					queue.offer(new Data(cur.value, cur.value, cur.level + 1));
				}
				
				// 삭제
				if(!visited[cur.value - 1][cur.copy]) {
					visited[cur.value - 1][cur.copy] = true;
					queue.offer(new Data(cur.value - 1, cur.copy, cur.level + 1));
				}
			}
			
			
			// 붙여넣기
			if(cur.copy > 0 && cur.value + cur.copy < 2000) {
				if(!visited[cur.value + cur.copy][cur.copy]) {
					visited[cur.value + cur.copy][cur.copy] = true;
					
					queue.offer(new Data(cur.value + cur.copy, cur.copy, cur.level + 1));
				}
			}
		}
	
	}
}
