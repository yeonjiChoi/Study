package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_2751_수정렬하기2_Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		/*
		 * [방법1]
		 * 음수 -> [0][n], 양수 -> [1][n] 
		 */
		boolean arr[][] = new boolean[2][1000001];
		for(int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n < 0) arr[0][n * -1] = true;
			else arr[1][n] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1000000; i > 0; i--)
			if(arr[0][i]) sb.append(i * -1 + "\n");
	
		for(int i = 0; i < 1000001; i++)
			if(arr[1][i]) sb.append(i + "\n");
	
		/* 
		 * [방법2]
		 * 우선순위 큐 사용 -> 메모리, 속도효율 떨어짐
		 */
		
/*		PriorityQueue<Integer> pqueue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		for(int i = 0; i < N; i++)
			pqueue.offer(Integer.parseInt(br.readLine()));

		StringBuilder sb = new StringBuilder();
		while(!pqueue.isEmpty())
			sb.append(pqueue.poll() + "\n");*/
		
		System.out.println(sb.toString());
	
	}
}

