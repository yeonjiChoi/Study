package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1764_듣보잡_Main {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
	
		HashSet<String> set = new HashSet<>();
		for(int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		
		List<String> list = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			String input = br.readLine();
			if(set.contains(input))
				list.add(input);
		}
		
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
	
		System.out.println(list.size());
		for(String str : list)
			System.out.println(str);
	}
}
