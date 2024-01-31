/**
 * @author Minji Lee
 * @date 20240131
 * @link https://www.acmicpc.net/problem/11659
 * @keyword_solution 동적 프로그래밍
 * @input 
 * 1 ≤ N ≤ 100,000
 * 1 ≤ M ≤ 100,000
 * 제한시간 1초,1 억번의 연산,그런데 NM = 100억...?  즉 O(NM)은 안됨.
 * @output 
 * @time_complex 상수 100,000번 + 100,000번 O(2N) -> O(N)
 * @perf  58568kb 700ms
 */
package src.Silver.S3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_구간합구하기4 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder result = new StringBuilder();
	static int[] list; 
	static int T;
	static int N;
	static int[] dp; 
	
	public static void main(String[] args) throws IOException {
		tokens = new StringTokenizer(br.readLine());
		T = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		
		tokens = new StringTokenizer(br.readLine());
		list = new int[T+1];
		dp = new int[T+1];
		for(int i = 1; i <= T; i++) {
			list[i] = Integer.parseInt(tokens.nextToken());
		}
	
		dp[0] = 0;
		dp[1] = list[1];
		
		for(int i =2; i<=T; i++) {
			dp[i] = dp[i-1] + list[i]; 
		}
		
		for(int i = 1; i <=N ; i++) {
			tokens = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(tokens.nextToken());
			int end = Integer.parseInt(tokens.nextToken());
			
			int sum = 0;
			sum = dp[end] - dp[start-1];
			result.append(sum).append("\n");
			
		}
		System.out.println(result);
	
	}
	

}
