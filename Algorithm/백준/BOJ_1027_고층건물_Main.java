package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1027_고층건물_Main {

	static int N, answer;
	static long arr[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new long[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++)
			arr[i] = Long.parseLong(st.nextToken());
	
		
		for(int i = 1; i <= N; i++) {
			
			// 현재 빌딩의  X 좌표
			int curX = i;
			// 현재 빌딩의 Y 좌표
			long curY = arr[i];
			
			// 왼쪽, 오른쪽 빌딩의 X 좌표
			int leftX = i - 1, rightX = i + 1, count = 0;
			
			// 왼쪽 계싼
			while(true) {
				// 범위를 벗어나면 종료
				if(leftX <= 0) break;

				// 다음 빌딩의 Y 좌표
				long leftY = arr[leftX];

				// 기울기
				double m = (double)(curY - leftY) / (curX - leftX);
				double n = (double)curY - (m * curX);
				
				boolean flag = true;
				for(int j = i - 1; j > leftX; j--) {
					double y = (m * j) + n;
					if(arr[j] >= y) {
						flag = false;
						break;
					}
				}
				
				if(flag) count++;
				leftX--;
//				System.out.printf("y = %fx + %f\n", m, n);
			
			}
		
			// 오른쪽 계싼
			while(true) {
				// 범위를 벗어나면 종료
				if(rightX > N) break;

				// 다음 빌딩의 Y 좌표
				long rightY = arr[rightX];

				// 기울기
				double m = (double)(curY - rightY) / (curX - rightX);
				double n = (double)curY - (m * curX);
				
				boolean flag = true;
				for(int j = i + 1; j < rightX; j++) {
					double y = (m * j) + n;
					if(arr[j] >= y) {
						flag = false;
						break;
					}
				}
				
				if(flag) count++;
				rightX++;
//				System.out.printf("y = %fx + %f\n", m, n);
			
			}
			
			answer = Math.max(count, answer);
		}
		
		System.out.println(answer);
	}
	

}
