package BOJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class BOJ_7490_0만들기_Main {

	public static int T, N, ops[];
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt();
			ops = new int[N - 1];
			
			makeOps(0);
			System.out.println();
		}
	}

	
	private static void makeOps(int cnt) {
		
		if(cnt == N - 1) {
			calculate();
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			ops[cnt] = i;
			
			makeOps(cnt + 1);
			
		}
		
	}

	private static void calculate() {
		
		int n = 2;
		String num = "1";
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 0; i < ops.length; i++) {
			if(ops[i] == 1 || ops[i] == 2) {
				queue.offer(Integer.parseInt(num));
				num = n++ + "";
			}
			else {
				num += n++;
			}
		}
		queue.add(Integer.parseInt(num));
		
		int value = queue.poll();
		for(int i = 0; i < ops.length; i++) {
			if(ops[i] == 1) {
				value = value + queue.poll();
			}
			else if(ops[i] == 2) {
				value = value - queue.poll();
			}
		}
		
		if(value == 0) {
			StringBuilder sb = new StringBuilder();
			sb.append(1);
			int m = 2;

			for(int i = 0; i < ops.length; i++) {
				if(ops[i] == 0) {
					sb.append(" " + m++);
				}
				else if(ops[i] == 1) {
					sb.append("+" + m++);
				}
				else {
					sb.append("-" + m++);
				}
			}
			System.out.println(sb.toString());
		}
	}

}
