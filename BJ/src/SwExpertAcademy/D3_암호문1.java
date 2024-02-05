package src.SwExpertAcademy;
/**
 * @author Minji lee
 * @date 20240205
 * @link https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
 * @keyword_solution 링크드 리스트 활용 
 * @input 
 * @output   
 * @time_complex  
 * @perf 20,432 kb 117 ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class D3_암호문1 { 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens = null;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		for(int tc = 1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());
			tokens = new StringTokenizer(br.readLine(), " ");
			LinkedList<String> secPasswords = new LinkedList<String>();
			for(int i = 1; i <= N; i++) {
				secPasswords.add(tokens.nextToken());
			}
			
			int order = Integer.parseInt(br.readLine());
			// 명령어 라인 
			tokens = new StringTokenizer(br.readLine(), " ");
			for(int i = 0 ;i < order; i++) {
				String IOD = tokens.nextToken();
				if(IOD.equals("I")) {
					int idx = Integer.parseInt(tokens.nextToken());
					int howmany = Integer.parseInt(tokens.nextToken());
					for(int j = idx; j < idx+howmany ; j++) {
						String tmp = tokens.nextToken();
						secPasswords.add(j, tmp);
					}
				}
			
			}
			sb.append("#").append(tc).append(" ");
			int cnt = 0;
			for(String tmp : secPasswords) {
				if (cnt< 10) {
					sb.append(tmp).append(" ");
					cnt++;
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
