package BOJ;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1037_약수_Main{
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Integer[] arr = new Integer[n];
		
		for(int i = 0; i < n; i++) 
			arr[i] = sc.nextInt();
		
		Arrays.sort(arr, Collections.reverseOrder());
		
		int N = arr[0] * 2;
		
		N = arr[0] * arr[n - 1];
		System.out.println(N);
	}
}
