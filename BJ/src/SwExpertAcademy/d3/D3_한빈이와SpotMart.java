package SwExpertAcademy.d3;
/**
 * @author Minji Lee
 * @date 20240206
 * @link https://swexpertacademy.com/main/talk/solvingClub/problemView.do?contestProbId=AW8Wj7cqbY0DFAXN&solveclubId=AY0LFFoqrIIDFAXz&problemBoxTitle=0205%EC%A3%BC&problemBoxCnt=3&probBoxId=AY13IwlKMEcDFAWX
 * @keyword_solutio 투포인
 * @input (2 ≤ N ≤ 1000 , 2 ≤ M ≤ 2000000)
 * @output M을 넘지 않는 두개의 봉지의 최대의  
 * @time_complex nlogn 
 * @perf 26,208 kb 메모리 135 ms

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_한빈이와SpotMart { 
	static BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int TC;
	static int N,M;

	//output 
	static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		TC = Integer.parseInt(br.readLine());
		for(int i = 1; i <= TC; i++) {
			tokens = new StringTokenizer(br.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			
			// 과자봉지 무게들 
			
			tokens = new StringTokenizer(br.readLine());
			int[] snacks = new int[N];
			for(int j = 0; j < N; j++) {
				snacks[j] = Integer.parseInt(tokens.nextToken());
			}
			Arrays.sort(snacks);
			int max = -1;
			int first = 0;
			int last = snacks.length -1;
			
			while(first < last) {
				if(snacks[first] + snacks[last] <= M) {
					max = Math.max(max, snacks[first] + snacks[last]);
					first++;
				}else {
					last--;
				}
			}

			sb.append("#").append(i).append(" ");
			sb.append(max).append("\n");
			
			
		}
		
		System.out.println(sb);
		
	}


}
