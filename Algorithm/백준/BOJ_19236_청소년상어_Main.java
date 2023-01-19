package BOJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BOJ_19236_청소년상어_Main {
	
	static int maxSum = 0;
	
	// 상, 좌상, 좌, 좌하, 하, 우하, 우, 우상(반시계)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1}; 
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int[][] map = new int[4][4]; // 4x4 배열
		List<Fish> fishes = new ArrayList<>();

		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++) {
				int num = sc.nextInt(); // 물고기 번호
				int dir = sc.nextInt() - 1; // 방향
				
				Fish fish = new Fish(x, y, num, dir, true);
				fishes.add(fish);
				map[x][y] = num;
			}
		}
		
		Collections.sort(fishes, (o1, o2) -> o1.num - o2.num);
		
		Fish fish = fishes.get(map[0][0] - 1);
		Fish shark = new Fish(0, 0, fish.num, fish.dir);
		fish.isAlive = false; // 잡아먹힘
		map[0][0] = -1;
		
		dfs(map, shark, fishes);
		System.out.println(maxSum);
		
	}
	
	private static void dfs(int[][] map, Fish shark, List<Fish> fishes) {
		
		// 잡아먹은 양 최대값으로 갱신
		if(maxSum < shark.num)
			maxSum = shark.num;
		
		for(Fish fish : fishes)
			moveFish(fish, map, fishes);
		
		for(int dist = 1; dist < 4; dist++) {
			int nx = shark.x + dx[shark.dir] * dist;
			int ny = shark.y + dy[shark.dir] * dist;
			
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
			if(map[nx][ny] <= 0) continue;
		
			int[][] copiedMap = copyMap(map);
			List<Fish> copiedFishes = copyFishes(fishes);
			
			copiedMap[shark.x][shark.y] = 0;
			Fish fish = copiedFishes.get(map[nx][ny] - 1);
			Fish newShark = new Fish(fish.x, fish.y, shark.num + fish.num, fish.dir);
			
			fish.isAlive = false;
			copiedMap[fish.x][fish.y] = -1;
			
			dfs(copiedMap, newShark, copiedFishes);
		}
		
	}
	

	private static List<Fish> copyFishes(List<Fish> fishes) {
		List<Fish> temp = new ArrayList<>();
		for(Fish fish : fishes)
			temp.add(new Fish(fish.x, fish.y, fish.num, fish.dir, fish.isAlive));
		return temp;
	}

	private static int[][] copyMap(int[][] map) {
		int[][] temp = new int[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) 
				temp[i][j] = map[i][j];
		}
		return temp;
	}

	private static void moveFish(Fish fish, int[][] map, List<Fish> fishes) {
		// 죽은 생선이면 무시
		if(!fish.isAlive) return;
		
		for(int i = 0; i < 8; i++) {
			int dir = (fish.dir + i) % 8;
			int nx = fish.x + dx[dir];
			int ny = fish.y + dy[dir];
			
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
			if(map[nx][ny] < 0) continue;
			
			map[fish.x][fish.y] = 0;
			
			// 이동할 칸이 비어있지 않으면
			if(map[nx][ny] != 0) {
				Fish temp = fishes.get(map[nx][ny] - 1);
				
				temp.x = fish.x;
				temp.y = fish.y;
				
				map[fish.x][fish.y] = temp.num;
			}
			
			fish.x = nx;
			fish.y = ny;
			
			map[nx][ny] = fish.num;
			fish.dir = dir;
			return;
		}
	}




	public static class Fish {
		// 행, 열, 물고기 번호, 방향 
		int x, y, num, dir;
		boolean isAlive;

		public Fish(int x, int y, int num, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}

		public Fish(int x, int y, int num, int dir, boolean isAlive) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.isAlive = isAlive;
		}

		@Override
		public String toString() {
			return "Fish [x=" + x + ", y=" + y + ", num=" + num + ", dir=" + dir + ", isAlive=" + isAlive + "]";
		}
		
	}
}
