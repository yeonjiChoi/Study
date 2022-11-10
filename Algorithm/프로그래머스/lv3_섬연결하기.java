package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class lv3_섬연결하기 {

	public static void main(String[] args) {

		int n = 4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		
		int answer = solution(n, costs);
		System.out.println(answer);
	}
	
    public static int solution(int n, int[][] costs) {
        int answer = 0;
        
        int[][] map = new int[n][n];
        
        for(int i = 0; i < costs.length; i++) {
        	int start = costs[i][0];
        	int end = costs[i][1];
        	int cost = costs[i][2];
        	
        	// 양방향 간선
        	map[start][end] = cost;
        	map[end][start] = cost;
        }
        
        boolean[] visited = new boolean[n];
        PriorityQueue<Data> pq = new PriorityQueue<>();
        
        pq.offer(new Data(0, 0));
        
        int count = 0;
        while(!pq.isEmpty()) {
        	// 우선순위 큐이기 때문에 이동 비용이 가장 작은 정점부터 뽑힘
        	Data cur = pq.poll();
        	
        	// 이미 방문한 정점이면 무시
        	if(visited[cur.node]) continue;
        	
        	// 아직 방문하지 않은 정점이면 방문 체크
        	visited[cur.node] = true;
        	answer += cur.dist;
        	if(++count == n) break;
        	
        	// cur.node와 연결된 정점을 확인하기 위한 반복문
        	for(int i = 0; i < n; i++) {
        		
        		// 이미 방문한 정점은 무시
        		if(visited[i]) continue;
        	
        		// 연결되지 않은 정점은 무시
        		if(map[cur.node][i] == 0) continue;
        		
        		// 연결된 정점 번호와 비용을 큐에 삽입
        		pq.offer(new Data(i, map[cur.node][i]));
        	}
        }
        
        return answer;
    }
    
    public static class Data implements Comparable<Data> {
    	// 정점
    	int node;
    	// 정점까지의 거리
    	int dist;
		
    	public Data(int node, int dist) {
			super();
			this.node = node;
			this.dist = dist;
		}

		@Override
		public int compareTo(Data o) {
			return Integer.compare(dist, o.dist);
		}
    	
    }
}
