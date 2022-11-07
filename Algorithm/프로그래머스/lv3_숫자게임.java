package PROGRAMMERS;

import java.util.*;

class Solution {
    
	// 초반 실수
	// A와 B 배열을 단순히 오름차순 정렬하여 0 ~ N까지의 인덱스 값들을 비교하였음
	// {1, 2, 3} {1, 2, 3} 배열이 나올 경우 답이 2가 아닌 0이 나오게 됨
	
	// 단순히 오름차순 정렬을 하는 것이 아님!
	// B 배열에서 가장 작은 숫자가 A 배열에서 가장 작은 숫자보다 같거나 작을 경우 필요가 없게 됨
	// B 배열을 우선순위 큐에 넣어 가장 작은 값을 꺼내가며 A의 가장 작은 값과 비교 -> 승리 점수 계산
	
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        // 배열 A 오름차순 정렬
        Arrays.sort(A);
        
        // 우선순위 큐 초기화, 오름차순 정렬
        PriorityQueue<Integer> queue = new PriorityQueue<>(new MyComparator());
        // 우선순위 큐에 배열 B 넣기    
        for(int i = 0; i < B.length; i++) 
            queue.add(B[i]);
        
        // 배열 A를 가리키는 인덱스
        int index = 0; 
        // 큐에 데이터가 없을 때까지 반복
        while(!queue.isEmpty()) {
            // 큐(B)에서 가장 작은 숫자 가져오기
            int peek = queue.poll();
            
            // B의 가장 작은 숫자가 A의 가장 작은 숫자보다 같거나 작다면 -> 필요없음, 무시
            if(peek <= A[index]) 
                continue;
            
            // B의 가장 작은 숫자가 A의 가장 작은 숫자보다 크다면
            else {
                // 승리, 1점 증가
                answer++;
                // 다음 인덱스를 가리키도록 1 증가
                index++;
            }
        }
        
        return answer;
    }

    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}