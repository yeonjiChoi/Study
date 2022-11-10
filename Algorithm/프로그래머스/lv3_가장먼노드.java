package PROGRAMMERS;

import java.util.*;

public class lv3_가장먼노드 {

	public static void main(String[] args) {
		int n = 6;
		int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		
		int answer = solution(n, vertex);
		System.out.println(answer);
	}
	
    public static int solution(int n, int[][] edge) {
        int answer = 0;
        
        List[] list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) 
        	list[i] = new ArrayList<Integer>();
        
        for(int i = 0; i < edge.length; i++) {
        	int start = edge[i][0];
        	int end = edge[i][1];
        	
        	list[start].add(end);
        	list[end].add(start);
        }
        
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1, 0));
        visited[1] = true;
        
        while(!queue.isEmpty()) {
        	Point cur = queue.poll();

        	dist[cur.node] = cur.dist;
        	
        	for(int i = 0; i < list[cur.node].size(); i++) {
        		int nextNode = (int) list[cur.node].get(i);
        		
        		if(visited[nextNode]) continue;
        		
        		queue.add(new Point(nextNode, cur.dist + 1));
        		visited[nextNode] = true;
        	}
        }

        int maxDist = 0;
        for(int i = 1; i <= n; i++) 
        	maxDist = Math.max(maxDist, dist[i]);
        
        for(int i = 1; i <= n; i++) {
        	if(dist[i] == maxDist)
        		answer++;
        }
        
        return answer;
    }
    
    public static class Point {
    	int node;
    	int dist;

    	public Point(int node, int dist) {
			super();
			this.node = node;
			this.dist = dist;
		}
    }
}
