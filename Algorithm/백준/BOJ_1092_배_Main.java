package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1092_배_Main {
	
	static List<Integer> cranes = new ArrayList<Integer>();
	static List<Integer> boxes = new ArrayList<Integer>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			cranes.add(Integer.parseInt(st.nextToken()));
		}
	
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < M; i++) {
			boxes.add(Integer.parseInt(st.nextToken())); 
		}
		
		Collections.sort(cranes, Collections.reverseOrder());
		Collections.sort(boxes, Collections.reverseOrder());
		
		// 만약 모든 박스를 배로 옮길 수 없으면 -1을 출력
		if(cranes.get(0) < boxes.get(0)) {
			System.out.println("-1");
			return;
		}
		
		
		int answer = 0;
		
        while(!boxes.isEmpty()) {
            int idx = 0;
            for(int i = 0; i < N; ) {
            	if(idx == boxes.size()) 
            		break;
                else if(cranes.get(i) >= boxes.get(idx)) {
                    boxes.remove(idx);
                    i++;
                }
                else 
                	idx++;
            }
            answer++;
        }
		
		System.out.println(answer);
		
	}

}
