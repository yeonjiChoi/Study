package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_10816_숫자카드2_Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(map.containsKey(n))
				map.put(n, map.get(n) + 1);
			else
				map.put(n, 1);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int n = Integer.parseInt(st.nextToken());
			sb.append(map.getOrDefault(n, 0) + " ");
		}
		System.out.println(sb.toString());
	}
}
