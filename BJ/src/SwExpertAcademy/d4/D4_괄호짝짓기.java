package src.SwExpertAcademy.d4;
/**
 * @author Minji Lee
 * @date 20240202
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?contestProbId=AV14eWb6AAkCFAYD&solveclubId=AY0LFFoqrIIDFAXz&problemBoxTitle=0129%EC%A3%BC&problemBoxCnt=8&probBoxId=AY0LFFoqrIMDFAXz+
 * @keyword_solution 스택, 가지치기
 * @input 
 * @output   
 * @time_complex O(N^2)
 * @perf  18412kb 112ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class D4_괄호짝짓기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static String start = "([{<";
	
	public static void main(String[] args) throws IOException {
	
		for(int i = 0; i < 10; i++) {
			
			tokens = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(br.readLine());
			String temp = tokens.nextToken();
			sb.append("#").append(i+1).append(" ");
			boolean check = checkingTrue(len, temp);
			
			if(check) {
				sb.append(1);
			}else {
				sb.append(0);
			}
			
			sb.append("\n");
		}
		System.out.println(sb);

	}
	
	public static boolean checkingTrue(int len, String list) {
		
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0; i < len; i++) {
			char temp = list.charAt(i);
			if(start.contains("" + temp)) {
				stack.add(list.charAt(i));
			}
			else {
				if(!stack.isEmpty()) {
					char popResult = stack.pop();
					switch (popResult) {
					case '(' :
						if(temp != ')') {
							return false;
						}
						break;
					case '[' :
						if(temp != ']') {
							return false;
						}
						break;
					case '{' :
						if(temp != '}') {
							return false;
						}
						break;
					case '<' :
						if(temp != '>') {
							return false;
						}
						break;
					}
				}
				
			}
			
		}
		
		return true;
	}

}








