package PROGRAMMERS;

public class lv3_기지국설치 {
	public int solution(int n, int[] stations, int w) {
        int answer = 0;
      
        if(stations.length == 0) {
            answer += n / (2 * w + 1);
            answer += (n % (2 * w + 1)) > 0 ? 1 : 0;
            return answer;
        }
        
        for(int i = 0; i < stations.length; i++) {
            if(i == 0) {
                int start = 1;
                int end = stations[i] - w - 1;
                                
                answer += count(start, end, w);
            }
            
            if(i >= 1 && i <= stations.length - 1) {
                int start = stations[i - 1] + w + 1;
                int end = stations[i] - w - 1;
                
                answer += count(start, end, w);
            }
            
            if(i == stations.length - 1) {
                int start = stations[i] + w + 1;
                int end = n;
                
                answer += count(start, end, w);
            }
        }
        
        return answer;
    }
    
    public static int count(int start, int end, int w) {
        
        if(start > end) 
            return 0;
        else if(start == end) 
            return 1;
        else {
            int dist = end - start + 1;
            int quotient = dist / (2 * w + 1);
            int remainder= (dist % (2 * w + 1)) > 0 ? 1 : 0;
            return quotient + remainder;    
        }
        
    }
}
