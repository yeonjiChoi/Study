package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링_Main {

	static int N, population[], result = Integer.MAX_VALUE; 
	static List<Integer> city[], groupA, groupB;
	
	static boolean powerset[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 구역의 개수
		population = new int[N + 1]; // 각 구역의 인구
		city = new ArrayList[N + 1]; // 구역의 연결 상태, 연결리스트 배열
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			population[i] = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			city[i] = new ArrayList<>(); // 연결리스트 배열 초기화

			st = new StringTokenizer(br.readLine());
			int J = Integer.parseInt(st.nextToken()); // 인접한 구역의 수
			for(int j = 0; j < J; j++) 
				city[i].add(Integer.parseInt(st.nextToken()));
		}
		
		powerset = new boolean[N + 1]; // 부분집합 배열
		subset(1); // 부분집합 배열 생성 함수
		
		// result가 MAX_VALUE일 경우, 두 선거구로 나눌 수 없음 => -1 출력
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	// 부분집합 생성
	private static void subset(int cnt) {
		
		if(cnt == N + 1) {
			// System.out.println(Arrays.toString(group)); // 부분집합 배열 출력 함수(디버깅)
			
			// true와 false를 기준으로 A선거구, B선거구로 나눔
			groupA = makeGroup(true); 
			groupB = makeGroup(false);
			
			// 각 선거구는 최소 하나의 구역을 포함해야 함
			if(groupA.size() == 0 || groupB.size() == 0) return;
			
			// 두 선거구의 연결 상태를 확인, 선거구 내 구역들이 연결되어 있으면 true 리턴
			boolean flagA = checkConn(groupA);
			boolean flagB = checkConn(groupB);
			
			// 두 선거구가 모두 연결되어 있는 상태일 경우
			if(flagA && flagB) {
				// 각 선거구의 인구합 계싼
				int countA = count(groupA);
				int countB = count(groupB);
				
				// 두 선거구의 인구 차이 계산 -> 최소값으로 갱신
				result = Math.min(result, Math.abs(countA - countB));
			}
			return;
		}
		
		powerset[cnt] = true;
		subset(cnt + 1);
		
		powerset[cnt] = false;
		subset(cnt + 1);
	}
	
	// 선거구의 인구합을 계산하는 함수
	private static int count(List<Integer> group) {
		int count = 0;
		for(int n : group)
			count += population[n];
		
		return count;
	}

	// 선거구 내 구역들의 연결 상태를 확인(bfs)
	private static boolean checkConn(List<Integer> list) {
		
		boolean[] visited = new boolean[N + 1];
		Queue<Integer> queue = new LinkedList<>();
		
		queue.offer(list.get(0));
		visited[list.get(0)] = true;
		
		int count = 1;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int i = 0; i < city[cur].size(); i++) {
				int next = city[cur].get(i);
				
				if(visited[next]) continue;
				
				if(list.contains(next)) {
					count++;
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
		if(count == list.size()) return true;
		
		return false;
	}

	// 부분집합을 기준으로 선거구 생성
	private static List<Integer> makeGroup(boolean flag) {
		List<Integer> list = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			if(powerset[i] == flag)
				list.add(i);
		}
		return list;
	}

}
