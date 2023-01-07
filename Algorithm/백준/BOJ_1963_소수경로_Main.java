package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1963_소수경로_Main {

	static boolean[] check = new boolean[10000];
	
	public static void main(String[] args) {
	
		// 1 ~ 9999 까지 소수 체크 => false이면 소수
		for(int i = 2; i < 10000; i++) {
			int j = 2;
			
			while(i * j < 10000) {
				check[i * j] = true;
				j++;
			}
		}
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		outer: for(int t = 1; t <= T; t++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			Queue<Data> queue = new LinkedList<>();
			boolean[] visited = new boolean[10000];
			
			queue.add(new Data(A, 0));
			visited[A] = true;
			
			while(!queue.isEmpty()) {
				Data cur = queue.poll();

				// A와 B가 일치하면 단계 출력 후 다음 케이스로 이동
				if(cur.number == B) {
					System.out.println(cur.level);
					continue outer;
				}
				
				for(int i = 0; i < 4; i++) {
					for(int j = 0; j <= 9; j++) {
						// 천의 자리가 0이 되면 안됨 -> 1000 이하
						if(i == 3 && j == 0) continue;
						// 일의 자리가 짝수 또는 5가 될 수 없음
						if(i == 0 && (j % 2 == 0 || j == 5)) continue;
						
						int next = change(cur, i, j);
						
						// 이미 방문한 숫자 무시
						if(visited[next]) continue;
						// 소수인지 확인 -> 소수 아니면 무시
						if(check[next]) continue;
						
						queue.offer(new Data(next, cur.level + 1));
						visited[next] = true;
					}
				}
			}
			System.out.println(0);
		}
	}
	
	private static int change(Data data, int pos, int n) {
		
		int next = 1;
		switch(pos) {
		// 일의 자리 숫자 변경
		case 0 :
			next = (int) (data.getFourth() * Math.pow(10, 3)
			+ data.getThird() * Math.pow(10, 2)
			+ data.getSecond() * Math.pow(10, 1)
			+ n * Math.pow(10, 0));
			break;
		// 십의 자리 숫자 변경
		case 1 :
			next = (int) (data.getFourth() * Math.pow(10, 3)
			+ data.getThird() * Math.pow(10, 2)
			+ n * Math.pow(10, 1)
			+ data.getFirst() * Math.pow(10, 0));
			break;
		// 배의 자리 숫자 변경
		case 2 :
			next = (int) (data.getFourth() * Math.pow(10, 3)
			+ n * Math.pow(10, 2)
			+ data.getSecond() * Math.pow(10, 1)
			+ data.getFirst() * Math.pow(10, 0));
			break;
		// 천의 자리 숫자 변경
		case 3 :
			next = (int) (n * Math.pow(10, 3)
			+ data.getThird() * Math.pow(10, 2)
			+ data.getSecond() * Math.pow(10, 1)
			+ data.getFirst() * Math.pow(10, 0));
			break;
		}
		
		return next;
	}

	public static class Data {
		int number, level;
		
		public Data(int number, int level) {
			super();
			this.number = number;
			this.level = level;
		}
		
		// 천의 자리
		public int getFourth() {
			return number / 1000;
		}
		// 백의 자리
		public int getThird() {
			return (number / 100) % 10;
		}
		// 십의 자리
		public int getSecond() {
			return (number / 10) % 10;
		}
		// 일의 자리
		public int getFirst() {
			return number % 10;
		}
	}
	

}
