package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16938_캠프준비_Main {

	static int N, L, R, X, input[], answer;
	static boolean isSelected[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
	
		input = new int[N];
		isSelected = new boolean[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 0);
	
		System.out.println(answer);
	}

	private static void subset(int cnt, int select) {

		if(cnt == N) {
			if(select != 1 && select != 0) {
				
				int sum = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
				
				for(int i = 0; i < N; i++) {
					if(isSelected[i]) {
						sum += input[i];
						max = Math.max(max, input[i]);
						min = Math.min(min, input[i]);
					}
				}
				
				if(sum >= L && sum <= R && (max - min) >= X) {
					answer++;
				}
			}
			
			return;
		}
		
		isSelected[cnt] = true;
		subset(cnt + 1, select + 1);
		
		isSelected[cnt] = false;
		subset(cnt + 1, select);
	}

}
