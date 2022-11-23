import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2002_추월_Main {
	
	static int N;
	static String[] input, output;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		input = new String[N];
		output = new String[N];
		
		for(int i = 0; i < N; i++) 
			input[i] = br.readLine();
		for(int i = 0; i < N; i++) 
			output[i] = br.readLine();
		
		int answer = 0;
		List<String> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			String car = output[i];
			
			for(int j = 0; j < N; j++) {
				if(input[j].equals(car)) break;
				
				if(list.contains(input[j])) continue;
				else {
					answer++;
					break;
				}
			}
			
			list.add(car);
			
		}
		System.out.println(answer);
	}
}
