package src.SwExpertAcademy.d4;
/**
 * @author Minji Lee
 * @date 20240206
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AY0LFFoqrIIDFAXz&contestProbId=AV141176AIwCFAYD&probBoxId=AY13IwlKMEcDFAWX&type=PROBLEM&problemBoxTitle=0205%EC%A3%BC&problemBoxCnt=3
 * @keyword_solution 규칙찾고, 예외처리
 * @input 10개의 테스트 케이스, 노드의 갯수 < 200 , 완전 이진트리 형식
 * @output 10개의 테스트 케이스의 값
 * @time_complex O(N)
 * @perf  18992kb 121ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D4_사칙연산유효성검사 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		for(int i = 1; i <= 10; i++) {
			sb.append("#");
			sb.append(i);
			sb.append(" ");
			int N = Integer.parseInt(br.readLine());
			StringTokenizer tokens;
			boolean check = true;
			check : for(int j = 0; j < N; j++) {
				tokens = new StringTokenizer(br.readLine(), " ");
				if(tokens.countTokens() > 2) {
					int number = Integer.parseInt(tokens.nextToken());
					String tmp = tokens.nextToken();
					if(tmp.equals("-") ||tmp.equals("*") || tmp.equals("+") ||tmp.equals("/")) {
						continue;
					}else {
						check = false;
					}
				}else {
					int number = Integer.parseInt(tokens.nextToken());
					String tmp = tokens.nextToken();
					if(tmp.equals("-") ||tmp.equals("*") || tmp.equals("+") ||tmp.equals("/")) {
						check = false;
					}
				}
			}
			if(check) {
				sb.append("1\n");
			}else {
				sb.append("0\n");
			}
		}
		System.out.println(sb);

	}

}
