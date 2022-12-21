package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5052_전화번호목록_Main {

	static int T, N;
	static String[] numbers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		outer: for(int t = 0; t < T; t++) {
			
			N = Integer.parseInt(br.readLine());
			numbers = new String[N];
			for(int i = 0; i < N; i++) 
				numbers[i] = br.readLine();
				
			Arrays.sort(numbers);
			
			for(int i = 0; i < N - 1; i++) {
				String cur = numbers[i];
				String next = numbers[i + 1];
				
				if(next.startsWith(cur)) {
					System.out.println("NO");
					continue outer;
				}
			}
			System.out.println("YES");
		}
		
	}

}
