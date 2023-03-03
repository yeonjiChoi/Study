package BOJ;

import java.util.Scanner;

public class BOJ_10250_ACM호텔_Main {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int H = sc.nextInt();
			int W = sc.nextInt();
			int N = sc.nextInt();
		
			int height = N % H == 0 ? H * 100: (N % H) * 100;
			int width = N % H == 0 ? N / H : (N / H) + 1;
			
			System.out.println(height + width);
		}
	}
}
