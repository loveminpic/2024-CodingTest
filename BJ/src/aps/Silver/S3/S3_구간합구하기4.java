/**
 * @author Minji Lee
 * @date 20240131
 * @link https://www.acmicpc.net/problem/11659
 * @keyword_solution �룞�쟻 �봽濡쒓렇�옒諛�
 * @input 
 * 1 �돞 N �돞 100,000
 * 1 �돞 M �돞 100,000
 * �젣�븳�떆媛� 1珥�,1 �뼲踰덉쓽 �뿰�궛,洹몃윴�뜲 NM = 100�뼲...?  利� O(NM)�� �븞�맖.
 * @output 
 * @time_complex �긽�닔 100,000踰� + 100,000踰� O(2N) -> O(N)
 * @perf  58568kb 700ms
 */
package aps.Silver.S3;

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
