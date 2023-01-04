package BOJ;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1181_단어정렬_Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		HashSet<String> set = new HashSet<>();
		PriorityQueue<Data> pqueue = new PriorityQueue<>(new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				return o1.len == o2.len ? o1.str.compareTo(o2.str) : o1.len - o2.len;
			}
		});

		for(int i = 0; i < N; i++) {
			String str = sc.next();
			if(!set.contains(str)) {
				pqueue.offer(new Data(str, str.length()));
				set.add(str);
			}
		}
		
		while(!pqueue.isEmpty()) {
			System.out.println(pqueue.poll().str);
		}

	}

	public static class Data {
		String str;
		int len;

		public Data(String str, int len) {
			super();
			this.str = str;
			this.len = len;
		}

	}
}
