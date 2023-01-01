package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_11279_최대힙_Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return Long.compare(o2, o1);
			}
		});
		
		for(int i = 0; i < N; i++) {
			Long n = Long.parseLong(br.readLine());
			
			if(n == 0) {
				System.out.println(pq.isEmpty() ? 0 : pq.poll());
			}
			else {
				pq.offer(n);
			}
		}
		
	}
}
