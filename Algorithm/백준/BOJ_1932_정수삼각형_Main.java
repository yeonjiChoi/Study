package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1932_정수삼각형_Main {
	
	static int N, map[][], result[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		result = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j <= i; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result[0][0] = map[0][0];
		for(int i = 1; i < N; i++) {
			for(int j = 0; j <= i; j++) {
				if(j - 1 >= 0) {
					result[i][j] = Math.max(result[i][j], result[i - 1][j - 1] + map[i][j]);
				}
				if(j <= i - 1) {
					result[i][j] = Math.max(result[i][j], result[i - 1][j] + map[i][j]);
				}
			}
		}
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(result[i][j]);
//			}
//			System.out.println();
//		}
		int answer = 0;
		for(int i = 0; i < N; i++) {
			answer = Math.max(answer, result[N - 1][i]);
		}
	
		System.out.println(answer);
	}
}
