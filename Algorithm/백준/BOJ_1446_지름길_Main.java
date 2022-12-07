package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1446_지름길_Main {
	
	static int N, END;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		END = Integer.parseInt(st.nextToken());
		
		List<Point> list = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
		
			list.add(new Point(start, end, dist));
		}
		
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0));
		
		int answer = Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(cur.start == END) {
				answer = Math.min(answer, cur.dist);
				continue;
			}
			
			for(int i = 0; i < list.size(); i++) {
				Point next = list.get(i);
				if(next.start == cur.start) {
					if(next.end <= END) {
						queue.offer(new Point(next.end, cur.dist + next.dist));
					}
				}
			}
			
			
			queue.offer(new Point(cur.start + 1, cur.dist + 1));
		}
		
		System.out.println(answer);
	}
	
	public static class Point {
		int start, end, dist;

		public Point(int start, int end, int dist) {
			super();
			this.start = start;
			this.end = end;
			this.dist = dist;
		}

		public Point(int start, int dist) {
			super();
			this.start = start;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Point [start=" + start + ", end=" + end + ", dist=" + dist + "]";
		}
		
	}

}
