package PROGRAMMERS;
import java.util.*;
import java.util.regex.Pattern; // 정규표현식 사용 시 반드시 추가!

public class lv3_불량사용자 {
	
    public static String[] output;
	public static boolean[] visited;
	public static HashSet<HashSet<String>> set;
	
	public static int solution(String[] user_id, String[] banned_id) {
		
		output = new String[banned_id.length]; // banned_id 크기의 순열 배열
		visited = new boolean[user_id.length]; // 순열 생성 시 방문 체크 배열
		set = new HashSet<>(); // 당첨에서 제외될 아이디 목록
		
		for(int i = 0; i < banned_id.length; i++) 
            // 정규표현식 사용을 위해 * -> . 으로 변경 
            // .은 임의의 한 문자로 문자의 종류를 가리지 않고 비교 가능(\ 제외)
			banned_id[i] = banned_id[i].replace("*", "."); 
		
        // 순열 생성 함수 호출
		perm(0, user_id, banned_id);
		
		return set.size();
		
	}

	public static void perm(int cnt, String[] user_id, String[] banned_id) {
        // 순열 함수가 banned_id 크기만큼 호출되었다면
		if(cnt == banned_id.length) { 
            // 생성된 순열 배열을 반복하며 banned_id 배열과 일치하는지 확인
			for(int i = 0; i < banned_id.length; i++) {
                // Pattern.matches("fr.d.", "frodo") == true
				if(!Pattern.matches(banned_id[i], output[i])) return;
			}
			
            // 생성된 순열 배열이 banned_id와 일치한다면 
            // 생성된 순열 배열을 해시셋으로 넣어줌, 해시셋은 저장 순서가 없음
			HashSet<String> hs = new HashSet<>();
			for(int i = 0; i < output.length; i++) {
				hs.add(output[i]);
			}

            // 만들어진 해시셋을 최종 해시셋에 넣어줌 -> 중복되는 아이디 제재 목록을 제거
			set.add(hs);
			return;
		}
		
		for(int i = 0; i < user_id.length; i++) {
			// 이미 선택한 user_id는 무시
			if(visited[i]) continue;
		
            // 방문 체크 및 순열 배열에 추가
			visited[i] = true;
			output[cnt] = user_id[i];
			
            // 다음 순열 고르러 Go!
			perm(cnt + 1, user_id, banned_id);
            // 백트래킹 시 방문체크 해제
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) {

		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};

		int answer = solution(user_id, banned_id);
		System.out.println(answer);
	}
}
