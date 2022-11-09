package PROGRAMMERS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class lv3_보석쇼핑 {

	public static void main(String[] args) {
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		String[] gems2 = {"XYZ", "XYZ", "XYZ"};
		int[] answer = solution(gems);
		
		System.out.println(Arrays.toString(answer));
	}
	
    public static int[] solution(String[] gems) {
    	// 보석의 종류 개수를 확인하기 위한 HashSet
    	HashSet<String> set = new HashSet<>();
    	for(String gem : gems) 
    		set.add(gem);
        
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>();
        
        int point = 0; // 이동하는 시작 진열대 번호
        int start = 0; // 가장 짧은 구간의 시작 진열대 번호
        int length = Integer.MAX_VALUE; // 최소 구간 길이
        
        for(int i = 0; i < gems.length; i++) {
        	
        	// 큐에 보석 삽입
        	queue.offer(gems[i]);
        	// 맵에 보석 삽입 - 맵에 없으면 1, 있으면 기존 값 + 1
        	map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);

        	// 큐 안에 peek가 가리키는 보석의 개수가 2보다 많을 경우
        	while(map.get(queue.peek()) > 1) {
        		// 최소 구간을 위해 중복되는 보석을 큐에서 제거
        		String peek = queue.poll();
        		// 맵에서 value 값을 1 감소
        		map.put(peek, map.get(peek) - 1);
        		// 시작 진열대 한 칸 이동
        		point++;
        	}

        	// 맵의 보석의 개수가 전체 보석 종류의 개수와 동일하다면
        	if(map.size() == set.size()) {
        		// 큐의 크기가 최소 구간 길이보다 작다면
        		if(length > queue.size()) {
        			// length 갱신
        			length = queue.size();
            		// 시작 진열대 번호 갱신
        			start = point;
        		}
        	}
        }

        int[] answer = new int[] {start + 1, start + length};
        
        return answer;
    }

}
