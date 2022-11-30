import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2251_물통_Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		// 물통 부피 배열
		int[] baskets = new int[3];
		// 가장 큰 물통 부피
		int max = 0;
		for(int i = 0; i < 3; i++) {
			baskets[i] = sc.nextInt();
			max = Math.max(max, baskets[i]);
		}
		
		Queue<int[]> queue = new LinkedList<>();
		// 세 물통의 물의 양 중복 체크 배열
		List<int[]> visited = new LinkedList<>();
		// 세번째 물통에 담길 수 있는 물의 양 체크 배열
		boolean[] answer = new boolean[max + 1];
		
		// 세번째 물통이 가득 찬 물통 배열을 큐에 삽입
		queue.offer(new int[] {0, 0, baskets[2]});
		// 방문 체크
		visited.add(new int[] {0, 0, baskets[2]});
		// 세번째 물통의 물의 양 체크
		answer[baskets[2]] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			// A, B, C 세 물통 탐색
			for(int i = 0; i < 3; i++) {
				// 물이 하나도 없을 경우 무시
				if(cur[i] == 0) continue;
				
				// 다른 물통으로 물을 옮기기 위한 탐색
				for(int j = 0; j < 3; j++) {
					// 같은 물통끼리는 물을 옮길 수 없으므로 무시
					if(i == j) continue;
					
					// 임시 배열 생성
					int[] temp = Arrays.copyOf(cur, cur.length);
					// 다른 물통으로 옮길 수 있는 물의 양 계산
					int move = temp[i] + temp[j] > baskets[j] ? baskets[j] - temp[j] : temp[i];
					// 기존 물통의 물의 양 감소
					temp[i] -= move;
					// 물을 옮긴 물통의 물의 양 증가
					temp[j] += move;
					
					// 중복 체크 
					boolean flag = false;
					for(int k = 0; k < visited.size(); k++) {
						int[] comp = visited.get(k);
						if(comp[0] == temp[0] && comp[1] == temp[1] && comp[2] == temp[2]) {
							flag = true;
							break;
						}
					}
					// 전에 나왔던 물통 배열일 경우 무시
					if(flag) continue;
					
					// 큐에 삽입
					queue.offer(temp);
					visited.add(temp);
					// 첫번째 물통의 물의 양이 0일 경우의 세번째 물통의 물의 양 체크
					if(temp[0] == 0)
						answer[temp[2]] = true;
				}
			}

		}
		// 세번째 물통에 담길 수 있는 물의 양 출력
		for(int i = 0; i < answer.length; i++) {
			if(answer[i]) System.out.print(i + " ");
		}
		System.out.println();
	}

}
