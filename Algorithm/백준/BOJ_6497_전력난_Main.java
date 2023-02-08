package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소신장트리, Prim 알고리즘 + 인접 리스트
public class BOJ_6497_전력난_Main {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 집의 수
			int N = Integer.parseInt(st.nextToken()); // 길의 수
			int count = 0, totalCost = 0, selectedCost = 0;
			
			if(M == 0 && N == 0) break;
			
			boolean[] visited = new boolean[M]; // 방문체크 배열
			List<Vertex> arrList[] = new ArrayList[M]; // 인접리스트
			
			// 인접리스트 초기화
			for(int i = 0; i < M; i++)
				arrList[i] = new ArrayList<>();

			// 도로 경로
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int start = Integer.parseInt(st.nextToken()); // 시작점
				int end = Integer.parseInt(st.nextToken()); // 도착점
				int cost = Integer.parseInt(st.nextToken()); // 비용
				
				// 양방향 간선
				arrList[start].add(new Vertex(end, cost));
				arrList[end].add(new Vertex(start, cost));

				totalCost += cost; // 전체 비용 게산
			}
			
			PriorityQueue<Vertex> pqueue = new PriorityQueue<>(); // 비용을 기준으로 오름차순 정렬
			pqueue.add(new Vertex(0, 0)); // 임의의 시작점 0
			
			while(true) {
				Vertex cur = pqueue.poll();
				
				// 이미 방문한 정점은 무시
				if(visited[cur.vertex]) continue;
				
				count++; // 방문한 정점 수 증가
				visited[cur.vertex] = true; // 정점 방문 처리
				selectedCost += cur.cost; // 최소비용 합 계산
				
				if(count == M) break; // N개의 집을 모두 방문할 경우 반복문 종료
				
				// 정점과 연결된 다른 정점 탐색
				for(int i = 0; i < arrList[cur.vertex].size(); i++) {
					Vertex next = arrList[cur.vertex].get(i); // cur.vertex와 연결된 다른 정점
					
					if(visited[next.vertex]) continue; // 이미 방문한 정점은 무시
					pqueue.offer(next); // 큐에 삽입
				}
			}
			
			// 절약할 수 있는 최대 비용 출력
			System.out.println(totalCost - selectedCost);
		}
	}

	
	public static class Vertex implements Comparable<Vertex> {
		int vertex, cost;
		
		public Vertex(int vertex, int cost) {
			super();
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.cost - o.cost;
		}
	}
}
