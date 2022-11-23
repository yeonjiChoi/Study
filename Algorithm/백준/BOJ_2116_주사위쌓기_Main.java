import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2116_주사위쌓기_Main {
	
	// [0, 5] / [1, 3] / [2, 4] 마주보고 있음
	static int N, dice[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dice = new int[N][6];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 6; j++) 
				dice[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		for(int i = 0; i < 6; i++) {

			int bottomIdx = i;
			int topIdx = reverse(i);
			
			int bottom = dice[0][bottomIdx];
			int top = dice[0][topIdx];
			
			int sum = maxValue(0, bottom, top);
			
			for(int j = 1; j < N; j++) {
				bottomIdx = findIdx(j, top);
				topIdx = reverse(bottomIdx);
				
				bottom = dice[j][bottomIdx];
				top = dice[j][topIdx];
				
				sum += maxValue(j, bottom, top);
			}
			result = Math.max(result, sum);

		}
		
		System.out.println(result);
		
		
	}
	
	public static int findIdx(int row, int value) {
		for(int i = 0; i < 6; i++) {
			if(dice[row][i] == value)
				return i;
		}
		return 0;
	}
	
	
	public static int maxValue(int row, int bottom, int top) {
		int max = 0;
		for(int i = 0; i < 6; i++) {
			if(dice[row][i] == bottom || dice[row][i] == top) continue;
			max = Math.max(max, dice[row][i]);
		}
		
		return max;
		
		
	}
	
	public static int reverse(int n) {
		if(n == 0 || n == 5) 
			return n == 0 ? 5 : 0;
		else if(n == 1 || n == 3)
			return n == 1 ? 3 : 1;
		else
			return n == 2 ? 4 : 2;
	}
	
	
}
