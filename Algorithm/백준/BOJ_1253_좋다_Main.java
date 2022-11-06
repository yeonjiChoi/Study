package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1253_좋다_Main {
	
	static int N, input[], answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		input = new int[N];

		// Key : 두 수의 합, Value : 두 수의 인덱스 배열 {i, j}
		HashMap<Integer, ArrayList<int[]>> map = new HashMap<>(); 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) 
			input[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				// 같은 인덱스는 무시
				if(i == j) continue; 
				
				// sum : 두 수의 합
				int sum = input[i] + input[j];
				
				// map에 sum을 키 값으로 갖는 value가 없다면
				if(map.get(sum) == null) {
					// 새로운 ArrayList 생성 후 {i, j} 배열을 추가
					ArrayList<int[]> list = new ArrayList<>();
					list.add(new int[] {i, j});
					map.put(sum, list);
				}
				// map에 sum을 키 값으로 갖는 value가 있다면
				else {
					// 기존의 ArrayList에 새로운 {i, j} 배열을 추가
					ArrayList<int[]> list = map.get(sum);
					list.add(new int[] {i, j});
					map.replace(sum, list);
				}
			}
		}

		for(int i = 0; i < N; i++) {
			// map에 input[i] 값인 key가 없다면 무시 
			if(!map.containsKey(input[i])) continue;
			
			// 배열 가져오기
			ArrayList<int[]> list = map.get(input[i]);

			for(int j = 0; j < list.size(); j++) {
				
				int[] arr = list.get(j);
				int cnt = 0;
				
				for(int k = 0; k < 2; k++) {
					if(arr[k] == i) cnt--;
					else cnt++;				
				}
				
				if(cnt == 2) {
					answer++;
					break;
				}
			}
		}
		System.out.println(answer);
	}
}
